package ftn.team23.service.implementations;

import ftn.team23.entities.Administrator;
import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import ftn.team23.entities.User;
import ftn.team23.service.interfaces.IGuestService;
import ftn.team23.service.interfaces.IHostService;
import ftn.team23.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	IUserService userService;

	// Funkcija koja na osnovu username-a iz baze vraca objekat User-a
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user;
		Guest g = userService.findGuestByEmail(username);
		if(g!= null && g.isActivated()) {
			user = new Guest(g.getEmail(), g.getPassword(), g.getName(), g.getSurname(), g.getLivingAddress(), g.getTelephoneNumber());
			user.setId(g.getId());
			user.setRoles(g.getRoles());
			return user;
		}
		Host h = userService.findHostByEmail(username);
		if(h!=null && h.isActivated()) {
			user = new Host(h.getEmail(), h.getPassword(), h.getName(), h.getSurname(), h.getLivingAddress(), h.getTelephoneNumber());
			user.setId(h.getId());
			user.setRoles(h.getRoles());
			return user;
		}
		Administrator a = userService.findAdminByEmail(username);
		if(a!=null && a.isActivated()) {
			user = new Host(a.getEmail(), a.getPassword(), a.getName(), a.getSurname(), a.getLivingAddress(), a.getTelephoneNumber());
			user.setId(a.getId());
			user.setRoles(a.getRoles());
			return user;
		}
		else{
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}
	}



}
