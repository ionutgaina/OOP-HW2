package website.handlers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import utilities.Output;
import website.pages.unlogged.Login.Login;
import website.pages.unlogged.Register.Register;

public class ChangePageHandler {
    private ChangePageHandler() {
    }

    public static void handle(String page, Output outputObject) {
        boolean noError = true;

        switch (page) {
            case "register" -> noError = Register.changePage();
            case "login" -> noError = Login.changePage();
            case "logout" -> noError = Login.logout();
            default -> noError = false;
        }

        // Error handling
        if (!noError || page.equals("movies") || page.equals("see details")) {
            ArrayNode output = outputObject.getOutput();
            output.addPOJO(new ErrorHandler(!noError));
        }
    }
}
