package ftn.team23.service.implementations;

import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import ftn.team23.entities.User;
import ftn.team23.service.interfaces.IGuestService;
import ftn.team23.service.interfaces.IHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	IGuestService guestService;
	@Autowired
	IHostService hostService;

	// Funkcija koja na osnovu username-a iz baze vraca objekat User-a
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user;
		Guest g = guestService.findGuestByEmail(username);
		if(g!= null) {
			user = new Guest(g.getEmail(), g.getPassword(), g.getName(), g.getSurname(), g.getLivingAddress(), g.getTelephoneNumber());
			user.setRoles(g.getRoles());
			return user;
		}
		Host h = hostService.findHostByEmail(username);
		if(h!=null) {
			user = new Host(h.getEmail(), h.getPassword(), h.getName(), h.getSurname(), h.getLivingAddress(), h.getTelephoneNumber());
			user.setRoles(g.getRoles());
			return user;
		}
		else{
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}
	}

}
