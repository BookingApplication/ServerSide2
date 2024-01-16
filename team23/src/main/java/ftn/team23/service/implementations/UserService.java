package ftn.team23.service.implementations;

import ftn.team23.dto.UserRequest;
import ftn.team23.entities.*;
import ftn.team23.enums.Status;
import ftn.team23.repositories.*;
import ftn.team23.service.interfaces.ISendGridService;
import ftn.team23.service.interfaces.IUserService;
import ftn.team23.service.interfaces.RoleService;
//import jakarta.mail.internet.MimeMessage;
import ftn.team23.util.ImageUploadUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

//import jakarta.mail.MessagingException;
//import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.*;

@Service
public class UserService implements IUserService {

    @Value("${profile-picture-path}")
    String imagesDirPath;

    @Autowired
    IGuestRepository guestRepository;
    @Autowired
    IHostRepository hostRepository;
    @Autowired
    IAdminRepository adminRepository;

    @Autowired
    ISendGridService sendGridService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;

    @Autowired
    IReservationRepository reservationRepository;


    public boolean IsEmailUniqueAcrossAllTables(String email) {
        Optional<Guest> guest = guestRepository.findByEmail(email);
        Optional<Host> host = hostRepository.findByEmail(email);
        Optional<Administrator> admin = adminRepository.findByEmail(email);

        if (guest.isPresent() || host.isPresent() || admin.isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public UserRequest getAccountData() {
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email = u.getEmail();
        Guest g = findGuestByEmail(email);
        if (g != null) return new UserRequest(g);
        Host h = findHostByEmail(email);
        if (h != null) return new UserRequest(h);
        Administrator a = findAdminByEmail(email);
        if (a != null) return new UserRequest(a);
        return null;
    }


    @Override
    public UserRequest updateAccount(UserRequest userRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Guest g = findGuestByEmail(email);
        if (g != null) {
            UserRequest result = updateGuest(userRequest);
            return result;
        }

        Host h = findHostByEmail(email);
        if (h != null) {
            UserRequest result = updateHost(userRequest);
            return result;
        }

        Administrator a = findAdminByEmail(email);
        if (a != null) {
            UserRequest result = updateAdmin(userRequest);
            return result;
        }
        return null;
    }

    //guest//
    @Override
    public UserRequest signupAsGuest(UserRequest userRequest) {
        Optional<Guest> found = guestRepository.findByEmail(userRequest.getEmail());

        String verificationCode = RandomString.make(64);
        String address = "http://localhost:8080/guest/verify?code=";
        String verificationLink = String.format("<a href=\"%s%s\">", address, verificationCode);
        //http://localhost:8080/guest/verify?code=unique-code

        try {
            sendGridService.sendVerificationEmail(userRequest.getEmail(), verificationLink);
            found.get().setAccountVerificationRequestDate(new Timestamp(System.currentTimeMillis()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
        }

        String encryptedPassword = passwordEncoder.encode(userRequest.getPassword());
        Guest guestToRegister = new Guest(userRequest.getEmail(), encryptedPassword, userRequest.getName(), userRequest.getSurname(), userRequest.getLivingAddress(), userRequest.getTelephoneNumber());
        List<Role> roles = roleService.findByName("ROLE_GUEST");
        guestToRegister.setRoles(roles);

        try {
            Guest result = guestRepository.save(guestToRegister);
            guestRepository.flush();
            return new UserRequest(result);
        } catch (RuntimeException ex) {
            Throwable e = ex;
            Throwable c = null;
            while ((e != null) && !((c = ex.getCause()) instanceof ConstraintViolationException)) {
                e = (RuntimeException) c;
            }
            if ((c != null) && (c instanceof ConstraintViolationException)) {
                ConstraintViolationException c2 = (ConstraintViolationException) c;
                Set<ConstraintViolation<?>> errors = c2.getConstraintViolations();
                StringBuilder sb = new StringBuilder(1000);
                for (ConstraintViolation<?> error : errors) {
                    sb.append(error.getMessage() + "\n");
                }
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, sb.toString());
            }
            throw ex;
        }
    }

    @Override
    public void verifyGuest(String code) {
        Optional<Guest> found = guestRepository.findByCodeActivation(code);
        if (found.isPresent()) {
            long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
            long requestSentTime = found.get().getAccountVerificationRequestDate().getTime();
            double hoursPassed = (double) (currentTime - requestSentTime) / 1000 * 60 * 60;
            if (found.get().getCodeActivation().equals(code) && hoursPassed <= 24) {
                Boolean activated = true;
                guestRepository.updateActivationStatusByCodeActivation(code, activated);
            }
        }
    }

    @Override
    public UserRequest updateGuest(UserRequest userRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Guest> found = guestRepository.findByEmail(email);
        if (found.isEmpty()) {
            return null;
        }

        String encodedPassword = userRequest.getPassword().isEmpty() ? found.get().getPassword() : passwordEncoder.encode(userRequest.getPassword());
        Guest guestToUpdate = new Guest(userRequest.getEmail(), encodedPassword, userRequest.getName(), userRequest.getSurname(), userRequest.getLivingAddress(), userRequest.getTelephoneNumber());
        guestToUpdate.setRoles(found.get().getRoles());
        guestToUpdate.setId(found.get().getId());
        guestToUpdate.setLastPasswordResetDate(found.get().getLastPasswordResetDate());
        guestToUpdate.setActivated(found.get().isActivated());

        if (!encodedPassword.equals(found.get().getPassword()))
            guestToUpdate.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));

        try {
            Guest result = guestRepository.save(guestToUpdate);
            guestRepository.flush();
            if (!guestToUpdate.getEmail().equals(email))
                SecurityContextHolder.clearContext();
            return new UserRequest(result);
        } catch (RuntimeException ex) {
            Throwable e = ex;
            Throwable c = null;
            while ((e != null) && !((c = ex.getCause()) instanceof ConstraintViolationException)) {
                e = (RuntimeException) c;
            }
            if ((c != null) && (c instanceof ConstraintViolationException)) {
                ConstraintViolationException c2 = (ConstraintViolationException) c;
                Set<ConstraintViolation<?>> errors = c2.getConstraintViolations();
                StringBuilder sb = new StringBuilder(1000);
                for (ConstraintViolation<?> error : errors) {
                    sb.append(error.getMessage() + "\n");
                }
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, sb.toString());
            }
            throw ex;
        }
    }

    public List<UserRequest> findAllGuests() {
        List<Guest> guests = guestRepository.findAll();
        if (guests.isEmpty()) {
            return null;
        }
        List<UserRequest> allAccounts = new ArrayList<UserRequest>();
        for (Guest g : guests) {
            UserRequest accountData = new UserRequest(g);
            allAccounts.add(accountData);
//            System.out.println(g.toString()); //ftn.team23.entities.Guest@6af12e6c
        }
        return allAccounts;
    }

    @Override
    public String deleteGuest(Long id) {
        Optional<Guest> guest = guestRepository.getGuestWithReservation(id);
        if(guest.isEmpty())
            return "Guest does not exist.";

        boolean canDelete = true;
        for(Reservation r : guest.get().getReservations()){
            if(r.getStatus() == Status.APPROVED){
                canDelete = false;
            }
        }

        if(canDelete)
        {
            guestRepository.deleteById(id);
            return "Guest deleted.";
        }

        return "Unable to delete guest. There are still active reservations.";
    }

    @Override
    public Guest findGuestByEmail(String email) {
        Optional<Guest> g = guestRepository.findByEmail(email);
        if (g.isPresent()) {
            return g.get();
        }
        return null;
    }

    @Override
    public Guest findGuestById(Long userId) {
        Optional<Guest> found = guestRepository.findById(userId);
        if (found.isPresent()) {
            return found.get();
        }
        return null;
    }

    //host//

    @Override
    public UserRequest signupAsHost(UserRequest userRequest) {
        Optional<Host> found = hostRepository.findByEmail(userRequest.getEmail());

        String verificationCode = RandomString.make(64);
        String address = "http://localhost:8080/host/verify?code=";
        String verificationLink = String.format("<a href=\"%s%s\">", address, verificationCode);

        try {
            sendGridService.sendVerificationEmail(userRequest.getEmail(), verificationLink);
            found.get().setAccountVerificationRequestDate(new Timestamp(System.currentTimeMillis()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
        }

        String encryptedPassword = passwordEncoder.encode(userRequest.getPassword());
        Host hostToRegister = new Host(userRequest.getEmail(), encryptedPassword, userRequest.getName(), userRequest.getSurname(), userRequest.getLivingAddress(), userRequest.getTelephoneNumber());
        List<Role> roles = roleService.findByName("ROLE_HOST");
        hostToRegister.setRoles(roles);

        try {
            Host result = hostRepository.save(hostToRegister);
            hostRepository.flush();
            return new UserRequest(result);
        } catch (RuntimeException ex) {
            Throwable e = ex;
            Throwable c = null;
            while ((e != null) && !((c = ex.getCause()) instanceof ConstraintViolationException)) {
                e = (RuntimeException) c;
            }
            if ((c != null) && (c instanceof ConstraintViolationException)) {
                ConstraintViolationException c2 = (ConstraintViolationException) c;
                Set<ConstraintViolation<?>> errors = c2.getConstraintViolations();
                StringBuilder sb = new StringBuilder(1000);
                for (ConstraintViolation<?> error : errors) {
                    sb.append(error.getMessage() + "\n");
                }
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, sb.toString());
            }
            throw ex;
        }
    }

    @Override
    public UserRequest updateHost(UserRequest userRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Host> found = hostRepository.findByEmail(email);
        if (found.isEmpty()) {
            return null;
        }

        String encodedPassword = userRequest.getPassword().isEmpty() ? found.get().getPassword() : passwordEncoder.encode(userRequest.getPassword());
        Host hostToUpdate = new Host(userRequest.getEmail(), encodedPassword, userRequest.getName(), userRequest.getSurname(), userRequest.getLivingAddress(), userRequest.getTelephoneNumber());
        hostToUpdate.setRoles(found.get().getRoles());
        hostToUpdate.setId(found.get().getId());
        hostToUpdate.setLastPasswordResetDate(found.get().getLastPasswordResetDate());
        hostToUpdate.setActivated(found.get().isActivated());

        if (!encodedPassword.equals(found.get().getPassword()))
            hostToUpdate.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
        try {
            Host result = hostRepository.save(hostToUpdate);
            hostRepository.flush();
            if (!hostToUpdate.getEmail().equals(email))
                SecurityContextHolder.clearContext();
            return new UserRequest(result);
        } catch (RuntimeException ex) {
            Throwable e = ex;
            Throwable c = null;
            while ((e != null) && !((c = ex.getCause()) instanceof ConstraintViolationException)) {
                e = (RuntimeException) c;
            }
            if ((c != null) && (c instanceof ConstraintViolationException)) {
                ConstraintViolationException c2 = (ConstraintViolationException) c;
                Set<ConstraintViolation<?>> errors = c2.getConstraintViolations();
                StringBuilder sb = new StringBuilder(1000);
                for (ConstraintViolation<?> error : errors) {
                    sb.append(error.getMessage() + "\n");
                }
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, sb.toString());
            }
            throw ex;
        }

    }

    @Override
    public void verifyHost(String code) {
        Optional<Host> found = hostRepository.findByCodeActivation(code);
        if (found.isPresent()) {
            long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
            long requestSentTime = found.get().getAccountVerificationRequestDate().getTime();
            double hoursPassed = (double) (currentTime - requestSentTime) / 1000 * 60 * 60;
            if (found.get().getCodeActivation().equals(code) && hoursPassed <= 24) {
                Boolean activated = true;
                hostRepository.updateActivationStatusByCodeActivation(code, activated);
            }
        }
    }


    @Override
    public String deleteHost(Long id) {
        Optional<Host> host = hostRepository.getHostWithAccommodations(id);
        List<Reservation> reservations = reservationRepository.findByStatus(Status.APPROVED);

        if(host.isEmpty())
            return "Host does not exist.";

        for(Accommodation a : host.get().getAccommodations()) {
            for (Reservation r : reservations) {
                if(r.getId().equals(a.getId()))
                    return "Unable to delete account. There are still active reservations.";
            }
        }
        hostRepository.deleteById(id);
        return "Host deleted.";
    }

    public List<UserRequest> findAllHosts() {
        List<Host> hosts = hostRepository.findAll();
        if (hosts.isEmpty()) {
            return null;
        }
        List<UserRequest> allAccounts = new ArrayList<UserRequest>();
        for (Host h : hosts) {
            UserRequest accountData = new UserRequest(h);
            allAccounts.add(accountData);
        }
        return allAccounts;
    }

    @Override
    public Host findHostByEmail(String email) {
        Optional<Host> h = hostRepository.findByEmail(email);
        if (h.isPresent()) {
            return h.get();
        }
        return null;
    }

    @Override
    public Host findHostById(Long userId) {
        Optional<Host> found = hostRepository.findById(userId);
        if (found.isPresent()) {
            return found.get();
        }
        return null;
    }

    //admin

    @Override
    public UserRequest updateAdmin(UserRequest userRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Administrator> found = adminRepository.findByEmail(email);
        if (found.isEmpty()) {
            return null;
        }

        String encodedPassword = userRequest.getPassword().isEmpty() ? found.get().getPassword() : passwordEncoder.encode(userRequest.getPassword());
        Administrator adminToUpdate = new Administrator(userRequest.getEmail(), encodedPassword, userRequest.getName(), userRequest.getSurname(), userRequest.getLivingAddress(), userRequest.getTelephoneNumber());
        adminToUpdate.setRoles(found.get().getRoles());
        adminToUpdate.setId(found.get().getId());
        adminToUpdate.setLastPasswordResetDate(found.get().getLastPasswordResetDate());
        adminToUpdate.setActivated(found.get().isActivated());

        if (!encodedPassword.equals(found.get().getPassword()))
            adminToUpdate.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
        try {
            Administrator result = adminRepository.save(adminToUpdate);
            adminRepository.flush();
            if (!adminToUpdate.getEmail().equals(email))
                SecurityContextHolder.clearContext();
            return new UserRequest(result);
        } catch (RuntimeException ex) {
            Throwable e = ex;
            Throwable c = null;
            while ((e != null) && !((c = ex.getCause()) instanceof ConstraintViolationException)) {
                e = (RuntimeException) c;
            }
            if ((c != null) && (c instanceof ConstraintViolationException)) {
                ConstraintViolationException c2 = (ConstraintViolationException) c;
                Set<ConstraintViolation<?>> errors = c2.getConstraintViolations();
                StringBuilder sb = new StringBuilder(1000);
                for (ConstraintViolation<?> error : errors) {
                    sb.append(error.getMessage() + "\n");
                }
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, sb.toString());
            }
            throw ex;
        }
    }

    @Override
    public void verifyAdmin(String code) {
        Optional<Administrator> found = adminRepository.findByCodeActivation(code);
        if (found.isPresent()) {
            long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
            long requestSentTime = found.get().getAccountVerificationRequestDate().getTime();
            double hoursPassed = (double) (currentTime - requestSentTime) / 1000 * 60 * 60;
            if (found.get().getCodeActivation().equals(code) && hoursPassed <= 24) {
                Boolean activated = true;
                adminRepository.updateActivationStatusByCodeActivation(code, activated);
            }
        }
    }

    public List<UserRequest> findAllAdmins() {
        List<Administrator> admins = adminRepository.findAll();
        if (admins.isEmpty()) {
            return null;
        }
        List<UserRequest> allAccounts = new ArrayList<UserRequest>();
        for (Administrator a : admins) {
            UserRequest accountData = new UserRequest(a);
            allAccounts.add(accountData);
        }
        return allAccounts;
    }

    @Override
    public Administrator findAdminByEmail(String email) {
        Optional<Administrator> a = adminRepository.findByEmail(email);
        if (a.isPresent()) {
            return a.get();
        }
        return null;
    }

    @Override
    public Administrator findAdminById(Long userId) {
        Optional<Administrator> found = adminRepository.findById(userId);
        if (found.isPresent()) {
            return found.get();
        }
        return null;
    }

    @Override
    @Transactional
    public void uploadProfileImage(Long userId, MultipartFile image) throws IOException {

        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String uploadDir = StringUtils.cleanPath(imagesDirPath + userId);
        System.out.println(uploadDir);
        ImageUploadUtil.saveImage(uploadDir, fileName, image);


        Optional<Guest> guest = guestRepository.findById(userId);
        if(guest.isPresent()) {
            guestRepository.updateProfilePicture(userId, fileName);
            return;
        }
        Optional<Host> host = hostRepository.findById(userId);
        if(host.isPresent()) {
            hostRepository.updateProfilePicture(userId, fileName);
            return;
        }
        Optional<Administrator> admin = adminRepository.findById(userId);
        if(admin.isPresent())
            adminRepository.updateProfilePicture(userId, fileName);

    }

    @Override
    public byte[] getProfileImage(Long userId) throws IOException {

        String basePath = imagesDirPath + userId;
        String imagePath="";

        Optional<Guest> guest = guestRepository.findById(userId);
        if(guest.isPresent())
            imagePath = StringUtils.cleanPath(basePath + "/" + guest.get().getProfilePicture());

        Optional<Host> host = hostRepository.findById(userId);
        if(host.isPresent())
            imagePath = StringUtils.cleanPath(basePath + "/" + host.get().getProfilePicture());


        Optional<Administrator> admin = adminRepository.findById(userId);
        if(admin.isPresent())
            imagePath = StringUtils.cleanPath(basePath + "/" + admin.get().getProfilePicture());

        File file = new File(imagePath);

        return Files.readAllBytes(file.toPath());
    }
}
