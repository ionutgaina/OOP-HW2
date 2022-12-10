package website.pages.unlogged.Login;

import database.Database;
import database.User;
import website.CurrentPage;
import website.CurrentUser;
import website.pages.unlogged.AuthController;

public class Login {
    private Login() {
    }

    public static boolean changePage() {
        CurrentPage currentPage = CurrentPage.getInstance();
        User currentUser = CurrentUser.getInstance().getUser();
        if (currentPage.getCurrentPage().equals("home") && currentUser == null) {
            currentPage.setCurrentPage("login");
            return true;
        } else {
            return false;
        }
    }

    public static boolean login(String name, String password, Database database,
                                String currentPage) {
        if (!currentPage.equals("login")) {
            return false;
        }

        AuthController authController = AuthController.getInstance();

        User loggedUser = authController.login(name, password, database);
        if (loggedUser == null) {
            CurrentPage.getInstance().setCurrentPage("home");
            return false;
        }
        CurrentUser currentUser = CurrentUser.getInstance();
        currentUser.setUser(loggedUser);
        return true;
    }

    public static boolean logout() {
        CurrentPage currentPage = CurrentPage.getInstance();
        String currentPageName = currentPage.getCurrentPage();
        CurrentUser currentUser = CurrentUser.getInstance();
        User user = currentUser.getUser();
        if (currentPageName.equals("login") || currentPageName.equals("register")) {
            return false;
        }
        if (currentPageName.equals("home") && user == null) {
            return false;
        }

        currentUser.setUser(null);
        currentPage.setCurrentPage("home");
        return true;
    }
}
