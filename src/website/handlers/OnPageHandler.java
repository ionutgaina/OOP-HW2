package website.handlers;

import database.Action;
import utilities.Output;
import website.pages.logged.Movies;
import website.pages.logged.SeeDetails;
import website.pages.logged.Upgrades;
import website.pages.unlogged.Login;
import website.pages.unlogged.Register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnPageHandler {
    private OnPageHandler() {
    }

    public static void handle(Action action, Output outputObject) {
        String feature = action.getFeature();
        boolean noError = true;

        switch (feature) {
            case "login" ->
                    noError = Login.login(action.getCredentials().getName(),
                            action.getCredentials().getPassword());

            case "register" -> noError = Register.register(action.getCredentials());

            case "search" -> noError = Movies.search(action.getStartsWith());

            case "filter" -> noError = Movies.filter(action.getFilters());

            case "purchase" -> noError = SeeDetails.purchase();

            case "watch" -> noError = SeeDetails.watch();

            case "like" -> noError = SeeDetails.like();

            case "rate" -> noError = SeeDetails.rate(action.getRate());

            case "buy premium account" -> noError = Upgrades.buyPremium();

            case "buy tokens" -> noError = Upgrades.buyTokens(Integer.parseInt(action.getCount()));

            default -> noError = false;
        }

        // Error handling
        List showErrorList = Arrays.asList("login", "register", "search", "filter", "purchase",
                "watch", "like", "rate");
        if (!noError || showErrorList.contains(feature)) {
//        outputObject.getOutput().add(action.getType() + " " + feature + " " + action
//        .getStartsWith());
            outputObject.getOutput().addPOJO(new ErrorHandler(!noError));
        }
    }
}
