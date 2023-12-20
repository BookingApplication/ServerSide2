package ftn.team23.repositories;

import ftn.team23.entities.Guest;

public interface IGuestRepository extends IUserDataRepository<Guest> {


   public void insertGuest(Guest guest);
   public void deleteByEmail(String email);
   //public void updateGuestByEmail(String email, String name, String surname, String livingAddress, String telephoneNumber);

}

