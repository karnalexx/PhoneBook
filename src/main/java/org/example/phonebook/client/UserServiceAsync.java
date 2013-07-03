package org.example.phonebook.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.example.phonebook.shared.model.IUser;

import java.util.List;

public interface UserServiceAsync {
    void create(String login, String phone, AsyncCallback<IUser> async);

    void update(int id, String login, String phone, AsyncCallback<Void> async);

    void delete(int id, AsyncCallback<Void> async);

    void find(int id, AsyncCallback<IUser> async);

    void findAll(AsyncCallback<List<IUser>> async);

    void findByLogin(String login, AsyncCallback<IUser> async);

    void findByKey(String key, AsyncCallback<List<IUser>> async);
}
