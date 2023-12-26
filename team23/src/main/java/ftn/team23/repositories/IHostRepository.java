package ftn.team23.repositories;

import ftn.team23.entities.Host;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface IHostRepository extends IUserRepository<Host> {
public interface IHostRepository extends JpaRepository<Host, Long>{

    Host save(Host guest);

    @Override
    void deleteById(Long aLong);

    Host findHostByEmail(String email);


    //    @Override
//    void deleteById(Long aLong);
//
//    @Override
//    Host findByEmail(String email);
//
//    @Override
//    Host findByEmailAndPassword(String email, String password);
}
