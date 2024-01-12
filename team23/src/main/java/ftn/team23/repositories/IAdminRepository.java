package ftn.team23.repositories;

import ftn.team23.entities.Administrator;
import ftn.team23.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IAdminRepository extends JpaRepository<Administrator, Long> {
    @Override
    void deleteById(Long aLong);

    Optional<Administrator> findByEmail(String email);
    Optional<Administrator> findByEmailAndPassword(String email, String password);
    Optional<Administrator> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE Administrator a SET a.activated = :activated WHERE a.email = :email")
    void updateActivationStatusByEmail(String email, Boolean activated);
}
