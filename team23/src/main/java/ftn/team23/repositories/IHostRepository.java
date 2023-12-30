package ftn.team23.repositories;

import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//public interface IHostRepository extends IUserRepository<Host> {
public interface IHostRepository extends JpaRepository<Host, Long>{

    Host save(Host guest);

    @Override
    void deleteById(Long aLong);

    Optional<Host> findByEmail(String email);
    Optional<Host> findByEmailAndPassword(String email, String password);
}
