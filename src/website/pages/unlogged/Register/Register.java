package website.pages.unlogged.Register;

import database.Credentials;
import database.Database;
import database.User;
import website.CurrentPage;
import website.CurrentUser;
import website.pages.unlogged.AuthController;

public class Register {
    private Register() {
    }

    public static boolean changePage() {
        CurrentPage currentPage = CurrentPage.getInstance();
        User currentUser = CurrentUser.getInstance().getUser();

        if (currentPage.getCurrentPage().equals("home") && currentUser == null) {
            currentPage.setCurrentPage("register");
            return true;
        } else {
            return false;
        }
    }

    public static boolean register(Credentials credentials, String currentPage, Database database) {
        if (!currentPage.equals("register")) {
            return false;
        }

        AuthController authController = AuthController.getInstance();

        User loggedUser = authController.register(credentials, database);
        System.out.println(loggedUser);
        if (loggedUser == null) {
            return false;
        }
        CurrentUser currentUser = CurrentUser.getInstance();
        currentUser.setUser(loggedUser);
        return true;
    }
}

