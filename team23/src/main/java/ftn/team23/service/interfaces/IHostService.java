package ftn.team23.service.interfaces;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;

public interface IHostService {
     AccountDataDTO register(AccountDataDTO guestData);
     void deleteHostByEmail(String email);
     Host findHostByEmail(String email);
}
