package website.handlers;

import database.Action;
import utilities.Output;
import website.pages.logged.Movies;
import website.pages.logged.SeeDetails;
import website.pages.logged.Upgrades;
import website.pages.unlogged.Login;
import website.pages.unlogged.Register;

import java.util.Arrays;
import java.util.List;

public final class OnPageHandler {
    private OnPageHandler() {
    }

    /**
     * handle the on page action
     *
     * @param action action to be handled
     */
    public static void handle(final Action action, final Output outputObject) {
        String feature = action.getFeature();

        boolean noError = switch (feature) {
            case "login" -> Login.login(action.getCredentials()
                                              .getName(), action.getCredentials()
                                                                .getPassword());
            case "register" -> Register.register(action.getCredentials());
            case "search" -> Movies.search(action.getStartsWith());
            case "filter" -> Movies.filter(action.getFilters());
            case "purchase" -> SeeDetails.purchase();
            case "watch" -> SeeDetails.watch();
            case "like" -> SeeDetails.like();
            case "rate" -> SeeDetails.rate(action.getRate());
            case "buy premium account" -> Upgrades.buyPremium();
            case "buy tokens" -> Upgrades.buyTokens(Integer.parseInt(action.getCount()));
            default -> false;
        };

        // Error handling
        List<String> showErrorList = Arrays.asList("login", "register", "search", "filter",
                                                   "purchase",
                                                   "watch", "like", "rate");
        if (!noError || showErrorList.contains(feature)) {
            outputObject.getOutput()
                        .addPOJO(new ErrorHandler(!noError));
        }
    }
}
