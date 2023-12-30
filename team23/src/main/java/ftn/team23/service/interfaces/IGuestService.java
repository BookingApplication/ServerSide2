package ftn.team23.service.interfaces;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.dto.UserRequest;
import ftn.team23.entities.Guest;
import ftn.team23.entities.User;

import java.util.List;

public interface IGuestService {

     AccountDataDTO register(AccountDataDTO guestData);
     void deleteGuestByEmail(String email);
     Guest findGuestByEmail(String email);

}
