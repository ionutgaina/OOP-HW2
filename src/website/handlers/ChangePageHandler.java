package website.handlers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Action;
import utilities.Output;
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
     * @param page         the page to be changed to
     * @param action       the action to be executed
     * @param outputObject the output object
     */
    public static void handle(final String page, final Action action, final Output outputObject) {
        boolean noError = switch (page) {
            case "register" -> Register.changePage();
            case "login" -> Login.changePage();
            case "logout" -> Login.logout();
            case "movies" -> Movies.changePage();
            case "see details" -> SeeDetails.changePage(action.getMovie());
            case "upgrades" -> Upgrades.changePage();
            default -> false;
        };

        // Error handling
        if (!noError || page.equals("movies") || page.equals("see details")) {
            ArrayNode output = outputObject.getOutput();
            output.addPOJO(new ErrorHandler(!noError));
        }
    }
}
