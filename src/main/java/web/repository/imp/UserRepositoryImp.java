package web.repository.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;
import web.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Repository
public class UserRepositoryImp implements UserRepository {
    private EntityManager entityManager;

    @PersistenceContext
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User deleteUser(User user) {
        if (entityManager.contains(user)) {
            entityManager.remove(user);
        } else {
            entityManager.remove(entityManager.merge(user));
        }
        return user;
    }

    @Override
    public User editUser(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public User getById(long id) {
//        Query query = entityManager.createQuery("from User user where user.id = " + id);
//        return (User) query.getSingleResult();
        return entityManager.find(User.class,id);
    }

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("select users from User users");
        return query.getResultList();
    }
}

