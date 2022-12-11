package website.pages.unlogged;

import database.Database;
import database.User;
import website.CurrentDatabase;
import website.CurrentPage;
import website.CurrentUser;

import java.util.ArrayList;

public class Login {
    private Login() {
    }

    public static boolean changePage() {
        CurrentPage currentPage = CurrentPage.getInstance();
        User currentUser = CurrentUser.getInstance().getUser();
        if (currentPage.getPage().equals("home") && currentUser == null) {
            currentPage.setPage("login");
            return true;
        } else {
            return false;
        }
    }

    public static boolean login(String name, String password) {
        String currentPage = CurrentPage.getInstance().getPage();
        CurrentPage.getInstance().setPage("home");
        if (!currentPage.equals("login")) {
            return false;
        }

        AuthController authController = AuthController.getInstance();
        Database database = CurrentDatabase.getInstance().getDatabase();

        User loggedUser = authController.login(name, password, database);
        if (loggedUser == null) {
            return false;
        }
        CurrentUser currentUser = CurrentUser.getInstance();
        currentUser.setUser(loggedUser);
        return true;
    }

    public static boolean logout() {
        CurrentPage currentPage = CurrentPage.getInstance();
        String currentPageName = currentPage.getPage();
        CurrentUser currentUser = CurrentUser.getInstance();
        User user = currentUser.getUser();
        if (currentPageName.equals("login") || currentPageName.equals("register")) {
            return false;
        }
        if (currentPageName.equals("home") && user == null) {
            return false;
        }

        currentPage.init();
        currentUser.init();
        return true;
    }
}
