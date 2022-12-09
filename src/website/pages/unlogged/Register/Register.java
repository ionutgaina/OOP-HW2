package website.pages.unlogged.Register;

import database.Credentials;

public class Register {
    private Register() {
    }

    public static boolean changePage(String currentPage) {
        if (currentPage.equals("home")) {
            return true;
        }
        return false;
    }

    public static void register(Credentials credentials) {
        // verify if in it's valid username and password
        // if it's valid, change page to logged
        // else, error
    }
}
