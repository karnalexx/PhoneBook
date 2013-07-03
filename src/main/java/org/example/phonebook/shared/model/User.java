package org.example.phonebook.shared.model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = User.QUERY_FIND_ALL_BY_KEY,
                query = "SELECT u FROM User u WHERE LOWER(u.login) LIKE :" + User.PARAMETER_KEY
                        + " OR LOWER(u.phone) LIKE :" + User.PARAMETER_KEY),
        @NamedQuery(name = User.QUERY_FIND_BY_LOGIN,
                query = "SELECT u FROM User u WHERE u.login = :" + User.PARAMETER_LOGIN),
        @NamedQuery(name = User.QUERY_FIND_ALL, query = "SELECT u FROM User u")
})
public class User implements IUser {

    @Id
    @GeneratedValue
    private int id;
    private String login;

    private String phone;

    public static final String QUERY_FIND_ALL = "User.findAll";
    public static final String QUERY_FIND_ALL_BY_KEY = "User.findAllByKey";
    public static final String QUERY_FIND_BY_LOGIN = "User.findByLogin";

    public static final String PARAMETER_KEY = "User.key";
    public static final String PARAMETER_LOGIN = "User.login";

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
