package website.handlers;

import database.Action;
import utilities.Output;
import website.pages.logged.Logout;
import website.pages.logged.Movies;
import website.pages.logged.SeeDetails;
import website.pages.logged.Upgrades;
import website.pages.unlogged.Login;
import website.pages.unlogged.Register;

public final class ChangePageHandler {
    private ChangePageHandler() {
    }

    /**
     * handle the changePage action
     *
     * @param action       the action to be executed
     */
    public static void handle(final Action action) {
        String page = action.getPage();
        boolean noError = switch (page) {
            case "register" -> Register.getInstance().changePage();
            case "login" -> Login.getInstance().changePage();
            case "logout" -> Logout.getInstance().changePage();
            case "movies" -> Movies.getInstance().changePage();
            case "see details" -> SeeDetails.getInstance().changePage(action.getMovie());
            case "upgrades" -> Upgrades.getInstance().changePage();
            default -> false;
        };

        // Error handling
        if (!noError || page.equals("movies") || page.equals("see details")) {
            Output.getInstance().getOutput()
                  .addPOJO(new ErrorHandler(!noError));
        }
    }
}
