package org.example.phonebook.server.dao;

import org.example.phonebook.shared.model.IUser;
import org.example.phonebook.shared.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("userDAO")
public class UserDAO implements IUserAction {

    @PersistenceContext(unitName = "MyPU")
    EntityManager entityManager;

    @Override
    public int createUser(String login, String phone) {
        User user = new User(login, phone);
        entityManager.persist(user);
        return user.getId();
    }

    @Override
    public void updateUser(int id, String login, String phone) {
        entityManager.merge(getUserById(id));
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public List<IUser> getAllUsers() {
        TypedQuery<IUser> query = entityManager.createNamedQuery(User.QUERY_FIND_ALL, IUser.class);
        return query.getResultList();
    }

    @Override
    public IUser getUserByLogin(String login) {
        TypedQuery<IUser> query = entityManager.createNamedQuery(User.QUERY_FIND_BY_LOGIN, IUser.class);
        query.setParameter(User.PARAMETER_LOGIN, login);
        return query.getSingleResult();
    }

    @Override
    public IUser getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<IUser> findByKey(String key) {
        TypedQuery<IUser> query = entityManager.createNamedQuery(User.QUERY_FIND_ALL_BY_KEY, IUser.class);
        query.setParameter(User.PARAMETER_KEY, "%" + key + "%");
        return query.getResultList();
    }
}
