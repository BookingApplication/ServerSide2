package ftn.team23.repositories;

import ftn.team23.entities.Guest;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IGuestRepository extends IUserDataRepository<Guest> {


   Guest save(Guest guest);

   @Override
   void deleteById(Long aLong);

   @Override
   Guest findByEmail(String email);

   @Override
   Guest findByEmailAndPassword(String email, String password);

}

