package website.handlers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Action;
import utilities.Output;
import website.pages.logged.Movies;
import website.pages.logged.SeeDetails;
import website.pages.logged.Upgrades;
import website.pages.unlogged.Login;
import website.pages.unlogged.Register;

public class ChangePageHandler {
    private ChangePageHandler() {
    }

    public static void handle(String page, Action action, Output outputObject) {
        boolean noError = true;

        switch (page) {
            case "register" -> noError = Register.changePage();
            case "login" -> noError = Login.changePage();
            case "logout" -> noError = Login.logout();
            case "movies" -> noError = Movies.changePage();
            case "see details" -> noError = SeeDetails.changePage(action.getMovie());
            case "upgrades" -> noError = Upgrades.changePage();
            default -> noError = false;
        }

        // Error handling
        if (!noError || page.equals("movies") || page.equals("see details")) {
            ArrayNode output = outputObject.getOutput();
//            output.add(action.getType() + " " + page + " " + action.getMovie());
            output.addPOJO(new ErrorHandler(!noError));
        }
    }
}
