package website.handlers;

import database.Action;
import database.Database;
import utilities.Output;
import website.CurrentPage;
import website.pages.unlogged.Login.Login;
import website.pages.unlogged.Register.Register;

public class OnPageHandler {
    private OnPageHandler() {
    }

    public static void handle(Action action, Database database, Output outputObject) {
        String currentPage = CurrentPage.getInstance().getCurrentPage();
        String feature = action.getFeature();
        boolean noError = true;

        switch (feature) {
            case "login" -> {
                noError = Login.login(action.getCredentials().getName(),
                        action.getCredentials().getPassword(), database, currentPage);
                CurrentPage.getInstance().setCurrentPage("home");
            }
            case "register" -> {
                noError = Register.register(action.getCredentials(), currentPage, database);
                CurrentPage.getInstance().setCurrentPage("home");
            }
            default -> noError = false;
        }

        // Error handling
        outputObject.getOutput().addPOJO(new ErrorHandler(!noError));
    }
}
