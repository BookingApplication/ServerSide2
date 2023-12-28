package ftn.team23.service.implementations;

import ftn.team23.entities.User;
import ftn.team23.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Override
    public User findById(Long userId) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByUsername(String name) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    /*@Override
    public User save(UserRequest userRequest) {
        return null;
    }*/
}
