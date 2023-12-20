package ftn.team23.repositories;

import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;

public interface IHostRepository extends IUserDataRepository<Host> {

    Host save(Host guest);

    @Override
    void deleteById(Long aLong);

    @Override
    Host findByEmail(String email);

    @Override
    Host findByEmailAndPassword(String email, String password);
}
