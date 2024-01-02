package ftn.team23.service.implementations;

import ftn.team23.dto.AccountDataDTO;
import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import ftn.team23.service.interfaces.IAccountService;
import ftn.team23.service.interfaces.IGuestService;
import ftn.team23.service.interfaces.IHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AccountService implements IAccountService {

    @Autowired
    IGuestService guestService;
    @Autowired
    IHostService hostService;

    @Override
    public AccountDataDTO getAccountData(String email) {
        Guest g = guestService.findGuestByEmail(email);
        if (g!=null) return new AccountDataDTO(g);
        Host h = hostService.findHostByEmail(email);
        if(h!=null) return new AccountDataDTO(h);
        return null;
    }

    @Override
    public AccountDataDTO updateAccountData(AccountDataDTO a) {
        Guest g = guestService.findGuestByEmail(a.getEmail());
        if (g!=null) {
            AccountDataDTO result = guestService.signup(a);
            return result;
        }

        Host h = hostService.findHostByEmail(a.getEmail());
        if(h!=null) {
            AccountDataDTO result = hostService.signup(a);
            return result;
        }
        return null;
    }
}
