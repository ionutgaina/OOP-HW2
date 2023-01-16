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
    public static void handle(final Action action) {
        String feature = action.getFeature();

        boolean noError = switch (feature) {
            case "login" -> Login.getInstance().login(action.getCredentials()
                                              .getName(), action.getCredentials()
                                                                .getPassword());
            case "register" -> Register.getInstance().register(action.getCredentials());
            case "search" -> Movies.getInstance().search(action.getStartsWith());
            case "filter" -> Movies.getInstance().filter(action.getFilters());
            case "purchase" -> SeeDetails.getInstance().purchase();
            case "watch" -> SeeDetails.getInstance().watch();
            case "like" -> SeeDetails.getInstance().like();
            case "rate" -> SeeDetails.getInstance().rate(action.getRate());
            case "buy premium account" -> Upgrades.getInstance().buyPremium();
            case "buy tokens" -> Upgrades.getInstance()
                                         .buyTokens(Integer.parseInt(action.getCount()));
            case "subscribe" -> SeeDetails.getInstance().subscribe(action.getSubscribedGenre());
            default -> false;
        };

        // Error handling
        List<String> showErrorList = Arrays.asList("login", "register", "search", "filter",
                                                   "purchase",
                                                   "watch", "like", "rate");
        if (!noError || showErrorList.contains(feature)) {
//            Output.getInstance().getOutput()
//                  .add("onpage " + feature);
            Output.getInstance().getOutput()
                        .addPOJO(new ErrorHandler(!noError));
        }
    }
}
