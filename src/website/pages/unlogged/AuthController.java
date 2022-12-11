package website.pages.unlogged;

import database.Credentials;
import database.Database;
import database.User;

import java.util.ArrayList;

public final class AuthController {
    private static AuthController instance = null;

    private AuthController() {
    }

    /**
     * get the instance of the singleton class
     *
     * @return the instance of the class
     */
    public static AuthController getInstance() {
        if (instance == null) {
            instance = new AuthController();
        }
        return instance;
    }

    /**
     * handle the login
     *
     * @param name     the name of the user
     * @param password the password of the user
     * @return if the login was successful
     */
    public User login(final String name, final String password, final Database database) {
        ArrayList<User> users = database.getUsers();
        return users.stream()
                    .filter(u -> u.getCredentials().getName().equals(name) && u.getCredentials()
                                                                               .getPassword()
                                                                               .equals(password))
                    .findFirst().orElse(null);
    }

    /**
     * handle the register
     *
     * @param credentials the data about the user
     * @param database    the database of the website
     * @return if the register was successful
     */
    public User register(final Credentials credentials, final Database database) {
        ArrayList<User> users = database.getUsers();

        String name = credentials.getName();
        User loggedUser = users.stream().filter(u -> u.getCredentials().getName().equals(name))
                               .findFirst().orElse(null);

        if (loggedUser != null) {
            return null;
        }

        User newUser = new User();
        newUser.setCredentials(credentials);
        database.getUsers().add(newUser);
        return newUser;

    }
}
