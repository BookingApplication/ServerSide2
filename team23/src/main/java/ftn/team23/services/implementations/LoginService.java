package ftn.team23.services.implementations;

import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import ftn.team23.entities.UserData;
import ftn.team23.repositories.IGuestRepository;
import ftn.team23.repositories.IHostRepository;
import ftn.team23.services.interfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {
    @Autowired
    private IGuestRepository guestRepository;

    @Autowired
    private IHostRepository hostRepository;

    @Override
    public boolean isValidLogin(String email, String password) {
        Guest guest = guestRepository.findByEmailAndPassword(email, password);
        if (guest != null) {
            return true;
        }

        Host host = hostRepository.findByEmailAndPassword(email, password);
        return host != null;
    }

    @Override
    public UserData getUserByEmail(String email) {
        Guest guest = guestRepository.findByEmail(email);
        if (guest != null) {
            return guest;
        }

        Host host = hostRepository.findByEmail(email);
        return host;
    }
}
