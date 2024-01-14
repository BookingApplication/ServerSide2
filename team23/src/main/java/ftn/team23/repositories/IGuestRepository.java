package ftn.team23.repositories;

import ftn.team23.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IGuestRepository extends JpaRepository<Guest, Long>{

   @Override
   void deleteById(Long aLong);

   Optional<Guest> findByEmail(String email);
   Optional<Guest> findByEmailAndPassword(String email, String password);
   Optional<Guest> findByUsername(String username);
   Optional<Guest> findByCodeActivation(String codeActivation);

   @Transactional
   @Modifying
   @Query("UPDATE Guest g SET g.activated = :activated WHERE g.codeActivation = :codeActivation")
   void updateActivationStatusByCodeActivation(String codeActivation, Boolean activated);

   @Transactional
   @Modifying
   @Query("update Guest g SET g.profilePicture = ?2 where g.id = ?1")
   void updateProfilePicture(Long id, String fileName);
}