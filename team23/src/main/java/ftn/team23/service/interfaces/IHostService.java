package ftn.team23.service.interfaces;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;

import java.util.List;

public interface IHostService {
     AccountDataDTO signup(AccountDataDTO guestData);
     void deleteHostByEmail(String email);
     Host findHostByEmail(String email);
     Host findById(Long userId);
     List<Host> findAll();
     Host findByUsername(String username);
}
