package org.example.phonebook.shared.model;

import javax.persistence.*;

@Entity
public class User implements IUser {

    @Id
    @GeneratedValue
    private int id;

    private String login;

    private String phone;

    public User() {
    }

    public User(String login, String phone) {
        this.login = login;
        this.phone = phone;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getLogin() {
        return this.login;
    }

    @Override
    public String getPhone() {
        return this.phone;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
