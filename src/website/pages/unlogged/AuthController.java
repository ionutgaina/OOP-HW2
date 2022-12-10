package website.pages.unlogged;

import database.Credentials;
import database.Database;
import database.User;

import java.util.ArrayList;

public class AuthController {
    static AuthController instance = null;

    private AuthController() {
    }

    public static AuthController getInstance() {
        if (instance == null) {
            instance = new AuthController();
        }
        return instance;
    }

    public User login(String name, String password, Database database) {
        ArrayList<User> users = database.getUsers();
        return users
                .stream()
                .filter(u -> u.getCredentials().getName().equals(name)
                             && u.getCredentials().getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public User register(Credentials credentials, Database database) {
        ArrayList<User> users = database.getUsers();

        String name = credentials.getName();
        User loggedUser = users
                .stream()
                .filter(u -> u.getCredentials().getName().equals(name))
                .findFirst()
                .orElse(null);

        if (loggedUser != null) {
            return null;
        }

        User newUser = new User();
        newUser.setCredentials(credentials);
        database.getUsers().add(newUser);
        return newUser;

    }
}
