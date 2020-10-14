package web.service;

import web.model.User;
import java.util.List;

public interface UserService {
    User addUser(User user);
    User deleteUser(User user);
    User editUser(User user);
    User getById(long id);
    List<User> getAllUsers();
}
