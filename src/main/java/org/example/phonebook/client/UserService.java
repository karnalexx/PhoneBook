package org.example.phonebook.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.example.phonebook.shared.model.IUser;

import java.util.List;

@RemoteServiceRelativePath("springGwtServices/userService")
public interface UserService extends RemoteService {

    public IUser create(String login, String phone) throws Exception;

    public void update(int id, String login, String phone) throws Exception;

    public void delete(int id) throws Exception;

    public IUser find(int id);

    public List<IUser> findAll();

    public IUser findByLogin(String login);

    public List<IUser> findByKey(String key);
}
