package ftn.team23.service.interfaces;

import ftn.team23.dto.UserRequest;
import ftn.team23.entities.User;

import java.util.List;

public interface IUserService {

    User findById(Long userId);
    List<User> findAll();

    User findByUsername(String name);

    User findByEmail(String email);


    User save(UserRequest userRequest);
}
