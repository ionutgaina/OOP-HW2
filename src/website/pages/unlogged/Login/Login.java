package website.pages.unlogged.Login;

public class Login {
    private Login() {
    }

    public static boolean changePage(String currentPage) {
        if (currentPage.equals("home")) {
            return true;
        }
        return false;
    }

    public static void login(String username, String password) {
        // verify if in it's valid username and password
        // if it's valid, change page to logged
        // else, error
    }
}
