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

   @Transactional
   @Modifying
   @Query("UPDATE Guest g SET g.activated = :activated WHERE g.email = :email")
   void updateActivationStatusByEmail(String email, Boolean activated);
}

