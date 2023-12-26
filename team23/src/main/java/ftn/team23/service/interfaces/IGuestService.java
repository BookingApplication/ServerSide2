package ftn.team23.service.interfaces;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.entities.Guest;

public interface IGuestService {

     AccountDataDTO register(AccountDataDTO guestData);
     void deleteGuestByEmail(String email);
     Guest findGuestByEmail(String email);
     boolean findByEmailAndPassword(String email, String password);

}
