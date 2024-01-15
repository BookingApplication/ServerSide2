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
    Optional<Host> findByCodeActivation(String codeActivation);

    @Transactional
    @Modifying
    @Query("UPDATE Host h SET h.activated = :activated WHERE h.codeActivation = :codeActivation")
    void updateActivationStatusByCodeActivation(String codeActivation, Boolean activated);

    @Transactional
    @Modifying
    @Query("update Host h SET h.profilePicture = ?2 where h.id = ?1")
    void updateProfilePicture(Long id, String fileName);
}
