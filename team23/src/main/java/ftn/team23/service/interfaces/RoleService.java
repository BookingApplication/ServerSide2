package ftn.team23.service.interfaces;

import ftn.team23.entities.Role;

import java.util.List;

public interface RoleService {
	Role findById(Long id);
	List<Role> findByName(String name);
}
