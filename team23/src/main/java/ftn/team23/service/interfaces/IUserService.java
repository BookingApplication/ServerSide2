package ftn.team23.service.interfaces;

import ftn.team23.dto.UserRequest;
import ftn.team23.entities.Administrator;
import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;

import java.util.List;

public interface IUserService {

    boolean IsEmailUniqueAcrossAllTables(String email);
    UserRequest getAccountData();
    UserRequest updateAccount(UserRequest userRequest);

    UserRequest signupAsGuest(UserRequest guestData);
    UserRequest updateGuest(UserRequest userRequest);
    void verifyGuest(String email, String code);
    boolean deleteGuest();
    void deleteGuestByEmail(String email);
    Guest findGuestByEmail(String email);
    Guest findGuestById(Long userId);
    List<UserRequest> findAllGuests();

    UserRequest signupAsHost(UserRequest hostData);
    UserRequest updateHost(UserRequest userRequest);
    void verifyHost(String email, String code);
    boolean deleteHost();
    void deleteHostByEmail(String email);
    Host findHostByEmail(String email);
    Host findHostById(Long userId);
    List<UserRequest> findAllHosts();

    UserRequest updateAdmin(UserRequest userRequest);
    void verifyAdmin(String email, String code);
    void deleteAdminByEmail(String email);
    Administrator findAdminByEmail(String email);
    Administrator findAdminById(Long userId);
    List<UserRequest> findAllAdmins();

//    User findById(Long userId);
//    List<User> findAll();
//    User findByUsername(String name);
//    User findByEmail(String email);
//    User save(UserRequest userRequest);
}
