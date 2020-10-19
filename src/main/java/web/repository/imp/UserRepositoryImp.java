package web.repository.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;
import web.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImp implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public void addUser(User user) {
        getEntityManager().persist(user);
    }

    @Override
    public void deleteUser(Long id) {
//        if (entityManager.contains(id)) {
//            entityManager.remove(id);
//        } else {
//            entityManager.remove(entityManager.merge(id));
//        }
//        return user;

        getEntityManager()
                .createQuery("delete from User user where user.id=: id")
                .setParameter("id", id)
                .executeUpdate();

    }

    @Override
    public void editUser(User user) {
//        entityManager.merge(user);
//        return user;
        getEntityManager().merge(user);
    }

    @Override
    public User getById(Long id) {
//        Query query = entityManager.createQuery("from User user where user.id = " + id);
//        return (User) query.getSingleResult();
        //return entityManager.find(User.class,id);
        return getEntityManager().find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
//        Query query = entityManager.createQuery("select users from User users");
//        return query.getResultList();
        return getEntityManager().createQuery("From User").getResultList();
    }
}

