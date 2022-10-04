package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    void update(long id, User user);

    void delete(long id);

    List<User> listUsers();

    User findUserById(long id);
}
