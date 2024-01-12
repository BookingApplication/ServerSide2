package ftn.team23.repositories;

import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IHostRepository extends JpaRepository<Host, Long>{

    @Override
    void deleteById(Long aLong);

    Optional<Host> findByEmail(String email);
    Optional<Host> findByEmailAndPassword(String email, String password);
    Optional<Host> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE Host h SET h.activated = :activated WHERE h.email = :email")
    void updateActivationStatusByEmail(String email, Boolean activated);
}
