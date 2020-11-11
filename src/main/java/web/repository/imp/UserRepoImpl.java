package web.repository.imp;

import web.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import web.repository.UserRepo;


@Repository
public class UserRepoImpl implements UserRepo {
    //    private final Map<String, User> userMap = Collections.singletonMap("test",
//            new User(1L, "test", "test", Collections.singleton(new Role(1L, "ROLE_USER")))); // name - уникальное значение, выступает в качестве ключа Map
//
//    @Override
//    public User getUserByName(String name) {
//        if (!userMap.containsKey(name)) {
//            return null;
//        }
//
//        return userMap.get(name);
//    }
//}
    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public void addUser(User user) {
        getEntityManager().persist(user);
    }

    @Override
    public void deleteUser(Long id) {
//        getEntityManager()
//                .createQuery("delete from User where id=: id", User.class)
//                .setParameter("id", id)
//                .executeUpdate();
        try {
            User user = getEntityManager().find(User.class, id);
            if (user != null) {
                getEntityManager().remove(user);
            }
        } catch (NullPointerException e) {
            System.out.println("User с указанным вами id не существует!");
        }
    }

    @Override
    public void editUser(User user) {
        getEntityManager().merge(user);
    }

    @Override
    public User getUserById(Long id) {
        return getEntityManager().find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return getEntityManager()
                .createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public User getUserByUsername(String username) {
        return getEntityManager()
                .createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();

    }
}

