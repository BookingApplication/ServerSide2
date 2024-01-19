package ftn.team23.repositories;

import ftn.team23.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IGuestRepository extends JpaRepository<Guest, Long>{

   @Override
   void deleteById(Long aLong);

   Optional<Guest> findByEmail(String email);
   Optional<Guest> findByEmailAndPassword(String email, String password);
   Optional<Guest> findByCodeActivation(String codeActivation);

   @Transactional
   @Modifying
   @Query("UPDATE Guest g SET g.activated = :activated WHERE g.codeActivation = :codeActivation")
   void updateActivationStatusByCodeActivation(@Param("codeActivation") String codeActivation, @Param("activated") Boolean activated);

   @Transactional
   @Modifying
   @Query("update Guest g SET g.profilePicture = ?2 where g.id = ?1")
   void updateProfilePicture(Long id, String fileName);

   @Query("select g from Guest g join fetch g.reservations reservations where g.id = ?1")
   Optional<Guest> getGuestWithReservation(Long id);
}