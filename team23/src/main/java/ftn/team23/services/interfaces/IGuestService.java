package ftn.team23.services.interfaces;

import ftn.team23.dto.AccountDataDTO;


public interface IGuestService {

    public void register(AccountDataDTO guestData);
    public void deleteGuestByEmail(String email);
//    public void updateGuestAccount(AccountDataDTO updatedGuestData);


}
