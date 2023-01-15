package website.pages.unlogged;

import database.Database;
import database.User;
import website.CurrentDatabase;
import website.CurrentPage;
import website.CurrentUser;

public final class Login {
    private static Login instance = null;

    private Login() {
    }

    /**
     * @return the instance of the class Login
     */
    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }

    /**
     * handle the change page to login
     *
     * @return if the page was changed
     */
    public static boolean changePage() {
        CurrentPage currentPage = CurrentPage.getInstance();
        User currentUser = CurrentUser.getInstance()
                                      .getUser();
        if (currentPage.getPage()
                       .equals("home") && currentUser == null) {
            currentPage.setPage("login");
            return true;
        } else {
            return false;
        }
    }

    /**
     * handle the login
     *
     * @param name the username
     * @param password the password
     * @return if the login was successful
     */
    public static boolean login(final String name, final String password) {
        String currentPage = CurrentPage.getInstance()
                                        .getPage();
        CurrentPage.getInstance()
                   .setPage("home");
        if (!currentPage.equals("login")) {
            return false;
        }

        AuthController authController = AuthController.getInstance();
        Database database = CurrentDatabase.getInstance()
                                           .getDatabase();

        User loggedUser = authController.login(name, password, database);
        if (loggedUser == null) {
            return false;
        }
        CurrentUser currentUser = CurrentUser.getInstance();
        currentUser.setUser(loggedUser);
        return true;
    }
}
