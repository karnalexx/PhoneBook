package org.example.phonebook.server.dao;

import org.example.phonebook.shared.model.IUser;

import java.util.List;

public interface IUserAction {
    public int createUser(String login, String phone);
    public void updateUser(int id, String login, String phone);
    public void deleteUser(int id);
    public List<IUser> getAllUsers();
    public IUser getUserByLogin(String login);
    public IUser getUserById(int id);

    /**
     * This method returns list of ${@link org.example.phonebook.shared.model.IUser} by appropriate search key.
     * The method should search by login, phone and partial matches.
     * @param key search key
     * @return list of ${@link org.example.phonebook.shared.model.IUser}
     */
    public List<IUser> findByKey(String key);
}
