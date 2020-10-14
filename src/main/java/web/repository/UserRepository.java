package web.repository;


import web.model.User;

import java.util.List;

public interface UserRepository {
    User addUser(User user);
    User deleteUser(User user);
    User editUser(User user);
    User getById(long id);
    List<User> getAllUsers();
}
