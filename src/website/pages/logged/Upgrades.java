package website.pages.logged;

import database.Database;
import database.Movie;
import database.User;
import utilities.filters.FilterByCountry;
import utilities.filters.IFilter;
import website.CurrentDatabase;
import website.CurrentPage;
import website.CurrentUser;

import java.util.ArrayList;

import static utilities.Constants.PREMIUM_PRICE;


public final class Upgrades {
    private Upgrades() {
    }

    /**
     * handle the change page to upgrades
     *
     * @return if the page was changed
     */
    public static boolean changePage() {
        CurrentPage currentPage = CurrentPage.getInstance();
        User currentUser = CurrentUser.getInstance().getUser();
        switch (currentPage.getPage()) {
            case "home" -> {
                if (currentUser != null) {
                    initPage(currentUser, currentPage, CurrentDatabase.getInstance().getDatabase());
                    return true;
                }
            }
            case "see details" -> {
                initPage(currentUser, currentPage, CurrentDatabase.getInstance().getDatabase());
                return true;
            }
            default -> {
                return false;
            }
        }
        return false;
    }

    /**
     * initialize the page
     *
     * @param currentUser the current user
     * @param currentPage the current page
     * @param database    the database
     */
    private static void initPage(final User currentUser, final CurrentPage currentPage,
                                 final Database database) {
        currentPage.setPage("upgrades");
        ArrayList<String> countryFilter = new ArrayList<>();
        countryFilter.add(currentUser.getCredentials().getCountry());
        IFilter filter = new FilterByCountry();
        ArrayList<Movie> movies = filter.doFilter(database.getMovies(), countryFilter);
        currentPage.setCurrentMoviesList(movies);
    }

    /**
     * handle the upgrade to premium
     *
     * @return if the upgrade was successful
     */
    public static boolean buyPremium() {
        User currentUser = CurrentUser.getInstance().getUser();

        // verify if the user has premium account
        if (currentUser.getCredentials().getAccountType().equals("premium")) {
            return false;
        }

        // verify if the user has enough tokens
        if (currentUser.getTokensCount() < PREMIUM_PRICE) {
            return false;
        }
        currentUser.setTokensCount(currentUser.getTokensCount() - PREMIUM_PRICE);
        currentUser.getCredentials().setAccountType("premium");
        return true;
    }

    /**
     * handle the payment for tokens
     *
     * @param tokensCount the number of tokens to be bought
     * @return if the payment was successful
     */
    public static boolean buyTokens(final int tokensCount) {
        User currentUser = CurrentUser.getInstance().getUser();

        // verify if the user has enough balance
        if (currentUser.getCredentials().getBalance() < tokensCount) {
            return false;
        }

        currentUser.setTokensCount(currentUser.getTokensCount() + tokensCount);
        currentUser.getCredentials()
                   .setBalance(currentUser.getCredentials().getBalance() - tokensCount);

        return true;
    }
}
