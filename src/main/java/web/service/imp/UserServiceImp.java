package web.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.repository.UserRepository;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Service("userServiceImp")
public class UserServiceImp implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User addUser(User user) {
        return userRepository.addUser(user);
    }
    @Transactional
    @Override
    public User deleteUser(User user) {
        return userRepository.deleteUser(user);
    }
    @Transactional
    @Override
    public User editUser(User user) {
        return userRepository.editUser(user);
    }
    @Transactional
    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }
    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
