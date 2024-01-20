package ftn.team23.service.interfaces;

import ftn.team23.dto.UserRequest;
import ftn.team23.entities.Administrator;
import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;
//import jakarta.mail.MessagingException;
//import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IUserService {

    boolean IsEmailUniqueAcrossAllTables(String email);
    UserRequest getAccountData();
    UserRequest updateAccount(UserRequest userRequest);

    UserRequest signupAsGuest(UserRequest guestData, String siteURL)throws UnsupportedEncodingException, MessagingException;
    UserRequest updateGuest(UserRequest userRequest);
    void verifyGuest(String code);
    String deleteGuest(Long id);
    Guest findGuestByEmail(String email);
    Guest findGuestById(Long userId);
    List<UserRequest> findAllGuests();

    UserRequest signupAsHost(UserRequest hostData,  String requestUrl) throws UnsupportedEncodingException, MessagingException;
    UserRequest updateHost(UserRequest userRequest);
    void verifyHost(String code);
    String deleteHost(Long id);
    Host findHostByEmail(String email);
    Host findHostById(Long userId);
    List<UserRequest> findAllHosts();

    UserRequest updateAdmin(UserRequest userRequest);
    void verifyAdmin(String code);
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
