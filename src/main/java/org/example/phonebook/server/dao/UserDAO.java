package org.example.phonebook.server.dao;

import org.example.phonebook.shared.model.IUser;
import org.example.phonebook.shared.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        IUser user = getUserById(id);
        user.setLogin(login);
        user.setPhone(phone);
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUserById(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<IUser> getAllUsers() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    @Override
    public IUser getUserByLogin(String login) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE LOWER(u.login) = :login");
        query.setParameter("login", login);
        return (IUser) query.getSingleResult();
    }

    @Override
    public IUser getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<IUser> findByKey(String key) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE LOWER(u.login) LIKE :key"
                                        + " OR LOWER(u.phone) LIKE :key");
        query.setParameter("key", "%" + key + "%");
        return query.getResultList();
    }
}
