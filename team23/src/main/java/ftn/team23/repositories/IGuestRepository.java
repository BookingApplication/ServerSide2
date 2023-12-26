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


//   Guest findGuestByEmail(String email);

   Optional<Guest> findByEmail(String email);

   //
//   Optional<List<Guest>> findByEmailContaining(String email); //proseldi "@" znak

   Guest findGuestByEmailAndPassword(String email, String password);


// getall
//   @Override
//   List<Guest> findAll(Sort sort);
//
//   @Override
//   <S extends Guest> List<S> findAll(Example<S> example, Sort sort);
//---------------------------------------------


   //   @Override
//   void deleteById(Long aLong);
//
//   @Override
//   Guest findByEmail(String email);
//
//
//   @Override
//   Guest findByEmailAndPassword(String email, String password);

}

