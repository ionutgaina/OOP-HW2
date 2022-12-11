package website.pages.unlogged;

import database.Credentials;
import database.Database;
import database.User;
import website.CurrentDatabase;
import website.CurrentPage;
import website.CurrentUser;

public final class Register {
    private Register() {
    }

    /**
     * handle the change page to register
     *
     * @return if the page was changed
     */
    public static boolean changePage() {
        CurrentPage currentPage = CurrentPage.getInstance();
        User currentUser = CurrentUser.getInstance()
                                      .getUser();

        if (currentPage.getPage()
                       .equals("home") && currentUser == null) {
            currentPage.setPage("register");
            return true;
        } else {
            return false;
        }
    }

    /**
     * handle the register
     *
     * @param credentials the credentials of the user
     * @return if the register was successful
     */
    public static boolean register(final Credentials credentials) {
        String currentPage = CurrentPage.getInstance()
                                        .getPage();
        CurrentPage.getInstance()
                   .setPage("home");
        if (!currentPage.equals("register")) {
            return false;
        }

        AuthController authController = AuthController.getInstance();
        Database database = CurrentDatabase.getInstance()
                                           .getDatabase();

        User loggedUser = authController.register(credentials, database);
        if (loggedUser == null) {
            return false;
        }
        CurrentUser currentUser = CurrentUser.getInstance();
        currentUser.setUser(loggedUser);
        return true;
    }
}

