package ftn.team23.service.interfaces;

import ftn.team23.dto.UserRequest;
import ftn.team23.entities.Administrator;
import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import org.springframework.web.multipart.MultipartFile;
//import jakarta.mail.MessagingException;
//import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.util.List;

public interface IUserService {

    boolean IsEmailUniqueAcrossAllTables(String email);
    UserRequest getAccountData();
    UserRequest updateAccount(UserRequest userRequest);

    UserRequest signupAsGuest(UserRequest guestData);
    UserRequest updateGuest(UserRequest userRequest);
    void verifyGuest(String code);
    boolean deleteGuest();
    void deleteGuestByEmail(String email);
    Guest findGuestByEmail(String email);
    Guest findGuestById(Long userId);
    List<UserRequest> findAllGuests();

    UserRequest signupAsHost(UserRequest hostData);
    UserRequest updateHost(UserRequest userRequest);
    void verifyHost(String code);
    boolean deleteHost();
    void deleteHostByEmail(String email);
    Host findHostByEmail(String email);
    Host findHostById(Long userId);
    List<UserRequest> findAllHosts();

    UserRequest updateAdmin(UserRequest userRequest);
    void verifyAdmin(String code);
    void deleteAdminByEmail(String email);
    Administrator findAdminByEmail(String email);
    Administrator findAdminById(Long userId);
    List<UserRequest> findAllAdmins();

    void uploadProfileImage(Long userId, MultipartFile image) throws IOException;
    byte[] getProfileImage(Long userId) throws IOException;

//    void registerAsGuest(UserRequest userRequest, String siteURL) throws UnsupportedEncodingException, MessagingException;
//    void registerAsHost(UserRequest userRequest, String siteURL) throws UnsupportedEncodingException, MessagingException;
//    void registerAsAdmin(UserRequest userRequest, String siteURL) throws UnsupportedEncodingException, MessagingException;


//    User findById(Long userId);
//    List<User> findAll();
//    User findByUsername(String name);
//    User findByEmail(String email);
//    User save(UserRequest userRequest);
}
