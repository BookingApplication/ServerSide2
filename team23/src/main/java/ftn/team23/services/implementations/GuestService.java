package ftn.team23.services.implementations;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.entities.Guest;
import ftn.team23.repositories.IGuestRepository;
import ftn.team23.services.interfaces.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService implements IGuestService {
    @Autowired
    IGuestRepository allGuests;

    @Override
    public void register(AccountDataDTO guestData) {
        Guest g = new Guest(guestData.getEmail(), guestData.getPassword(), false, guestData.getName(), guestData.getSurname(), guestData.getLivingAddress(), guestData.getTelephoneNumber());
        allGuests.insertGuest(g);
    }

    @Override
    public void deleteGuestByEmail(String email) {
        allGuests.deleteByEmail(email);
    }
}
//    @Override
//    public void updateGuestAccount( AccountDataDTO updatedGuestData) {
//        allGuests.updateGuestByEmail(updatedGuestData.getEmail(), updatedGuestData.getName(), updatedGuestData.getSurname(), updatedGuestData.getLivingAddress(), updatedGuestData.getTelephoneNumber());
//    }}