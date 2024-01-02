package ftn.team23.service.interfaces;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.dto.UserRequest;
import ftn.team23.entities.Guest;

import java.util.List;

public interface IGuestService {

     AccountDataDTO signup(AccountDataDTO guestData);
     void deleteGuestByEmail(String email);
     Guest findGuestByEmail(String email);
     Guest findById(Long userId);
     List<Guest> findAll();
     Guest findByUsername(String username);
}
