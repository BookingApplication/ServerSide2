package ftn.team23.repositories;

import ftn.team23.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//public interface IGuestRepository extends IUserRepository<Guest> {
public interface IGuestRepository extends JpaRepository<Guest, Long>{

   Guest save(Guest guest);

   @Override
   void deleteById(Long aLong);

   Optional<Guest> findByEmail(String email);
   Optional<Guest> findByEmailAndPassword(String email, String password);
   Optional<Guest> findByUsername(String username);
}

