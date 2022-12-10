package website.handlers;

import database.Action;
import utilities.Output;
import website.pages.logged.Movies;
import website.pages.unlogged.Login;
import website.pages.unlogged.Register;

public class OnPageHandler {
    private OnPageHandler() {
    }

    public static void handle(Action action, Output outputObject) {
        String feature = action.getFeature();
        boolean noError = true;

        switch (feature) {
            case "login" -> noError = Login.login(action.getCredentials().getName(),
                    action.getCredentials().getPassword());

            case "register" -> noError = Register.register(action.getCredentials());

            case "search" -> noError = Movies.search(action.getStartsWith());
            default -> noError = false;
        }

        // Error handling
        outputObject.getOutput().addPOJO(new ErrorHandler(!noError));
    }
}
