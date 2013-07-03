package org.example.phonebook.shared.model;

import java.io.Serializable;

public interface IUser extends Serializable {
    public int getId();
    public String getLogin();
    public String getPhone();
    public void setId(int id);
    public void setLogin(String login);
    public void setPhone(String phone);
}
