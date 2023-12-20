package ftn.team23.repositories;

import ftn.team23.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IUserDataRepository<T extends UserData> extends JpaRepository<T, Long> {

    T findByEmailAndPassword(String email, String password);

    T findByEmail(String email);

}

