package ftn.team23.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ftn.team23.entities.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByName(String name);
}
