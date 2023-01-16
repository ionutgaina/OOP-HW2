package website.pages.logged;

import database.User;
import website.CurrentPage;
import website.CurrentUser;

public final class Logout {
    private static Logout instance = null;

    private Logout() {
    }

    /**
     * @return the instance of the class Logout
     */
    public static Logout getInstance() {
        if (instance == null) {
            instance = new Logout();
        }
        return instance;
    }

    /**
     * handle the logout
     *
     * @return if the logout was successful
     */
    public static boolean changePage() {
        CurrentPage currentPage     = CurrentPage.getInstance();
        String      currentPageName = currentPage.getPage();
        CurrentUser currentUser     = CurrentUser.getInstance();
        User user            = currentUser.getUser();
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
