package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(long id, User user) {
        User editUser = findUserById(id);
        editUser.setName(user.getName());
        editUser.setLastName(user.getLastName());
        editUser.setEmail(user.getEmail());
        entityManager.merge(editUser);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findUserById(id));
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("select users from User users", User.class).getResultList();
    }

    @Override
    public User findUserById(long id) {
        return entityManager.createQuery("select users from User users where users.id = :id", User.class).
                setParameter("id", id).getSingleResult();
    }

}
