package org.example.phonebook.server.dao;

import org.example.phonebook.client.UserService;
import org.example.phonebook.shared.model.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public IUser create(String login, String phone) throws Exception {
        return userDAO.getUserById(userDAO.createUser(login, phone));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(int id, String login, String phone) throws Exception {
        userDAO.updateUser(id, login, phone);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(int id) throws Exception {
        userDAO.deleteUser(id);
    }

    @Override
    public IUser find(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List<IUser> findAll() {
        return userDAO.getAllUsers();
    }

    @Override
    public IUser findByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public List<IUser> findByKey(String key) {
        return userDAO.findByKey(key);
    }
}
