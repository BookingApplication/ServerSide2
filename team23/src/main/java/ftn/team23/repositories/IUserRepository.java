package ftn.team23.repositories;

import ftn.team23.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@NoRepositoryBean
public interface IUserRepository<T extends User> extends JpaRepository<T, Long> {

    T findByEmailAndPassword(String email, String password);

    T findByEmail(String email);

    T findByUsername(String username);
}

