package ftn.team23.service.implementations;

import ftn.team23.dto.UserRequest;
import ftn.team23.entities.*;
import ftn.team23.repositories.IAdminRepository;
import ftn.team23.repositories.IGuestRepository;
import ftn.team23.repositories.IHostRepository;
import ftn.team23.service.interfaces.ISendGridService;
import ftn.team23.service.interfaces.IUserService;
import ftn.team23.service.interfaces.RoleService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {

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
        if (found.isPresent()) {
            return null;
        } else {
            String verificationCode = String.valueOf(Math.floor(100000 + Math.random() * 900000));
            String address = "http://localhost:8080/guest/verify";
            String verificationLink = String.format("<a href=\"%s/%s/%s\">", address, userRequest.getEmail(), verificationCode);
            //http://localhost:8080/guest/verify/somemail@mail/123322
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
                UserRequest a = new UserRequest(result);
                return a;
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
    }

    @Override
    public void verifyGuest(String email, String code) {
        Optional<Guest> found = guestRepository.findByEmail(email);
        if (found.isPresent()) {
            long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
            long requestSentTime = found.get().getAccountVerificationRequestDate().getTime();
            double hoursPassed = (double) (currentTime - requestSentTime) / 1000 * 60 * 60;
            if (found.get().getCodeActivation().equals(code) && hoursPassed <= 24) {
                Boolean activated = true;
                guestRepository.updateActivationStatusByEmail(email, activated);
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
    public boolean deleteGuest() {
        Guest g = (Guest)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            SecurityContextHolder.clearContext();
            //todo: check if there are no active reservations in guest, soft delete
            guestRepository.deleteById(g.getId());
            guestRepository.flush();
            return true;
        }
        catch (RuntimeException ex)
        {
            return false;
        }
    }

    @Override
    public void deleteGuestByEmail(String email) {
        Optional<Guest> g = guestRepository.findByEmail(email);
        if (g.isPresent()) {
            guestRepository.deleteById(g.get().getId());
        }
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
        if (found.isPresent()) {
            return null;
        } else {
            String verificationCode = String.valueOf(Math.floor(100000 + Math.random() * 900000));
            String address = "http://localhost:8080/host/verify";
            String verificationLink = String.format("<a href=\"%s/%s/%s\">", address, userRequest.getEmail(), verificationCode);
            //http://localhost:8080/host/verify/somemail@mail/333555
            try {
                sendGridService.sendVerificationEmail(userRequest.getEmail(), verificationLink);
                found.get().setAccountVerificationRequestDate(new Timestamp(System.currentTimeMillis()));
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
            }
            String encryptedPassword = passwordEncoder.encode(userRequest.getPassword());
            Host hostToRegister = new Host(userRequest.getEmail(), encryptedPassword, userRequest.getName(), userRequest.getSurname(), userRequest.getLivingAddress(), userRequest.getTelephoneNumber());
            List<Role> roles = roleService.findByName("ROLE_GUEST");
            hostToRegister.setRoles(roles);
            try {
                Host result = hostRepository.save(hostToRegister);
                hostRepository.flush();
                UserRequest a = new UserRequest(result);
                return a;
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
    public void verifyHost(String email, String code) {
        Optional<Host> found = hostRepository.findByEmail(email);
        if (found.isPresent()) {
            long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
            long requestSentTime = found.get().getAccountVerificationRequestDate().getTime();
            double hoursPassed = (double) (currentTime - requestSentTime) / 1000 * 60 * 60;
            if (found.get().getCodeActivation().equals(code) && hoursPassed <= 24) {
                Boolean activated = true;
                hostRepository.updateActivationStatusByEmail(email, activated);
            }
        }
    }

    @Override
    public boolean deleteHost() {
        return false;
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
    public void deleteHostByEmail(String email) {
        Optional<Host> h = hostRepository.findByEmail(email);
        if (h.isPresent()) {
            hostRepository.deleteById(h.get().getId());
        }
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
    public void verifyAdmin(String email, String code) {
        Optional<Administrator> found = adminRepository.findByEmail(email);
        if (found.isPresent()) {
            long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
            long requestSentTime = found.get().getAccountVerificationRequestDate().getTime();
            double hoursPassed = (double) (currentTime - requestSentTime) / 1000 * 60 * 60;
            if (found.get().getCodeActivation().equals(code) && hoursPassed <= 24) {
                Boolean activated = true;
                adminRepository.updateActivationStatusByEmail(email, activated);
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
    public void deleteAdminByEmail(String email) {
        Optional<Administrator> a = adminRepository.findByEmail(email);
        if (a.isPresent()) {
            adminRepository.deleteById(a.get().getId());
        }
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

}
