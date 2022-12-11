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


public class Upgrades {
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

    private static void initPage(User currentUser, CurrentPage currentPage, Database database) {
        currentPage.setPage("upgrades");
        ArrayList<String> countryFilter = new ArrayList<>();
        countryFilter.add(currentUser.getCredentials().getCountry());
        IFilter filter = new FilterByCountry();
        ArrayList<Movie> movies = filter.doFilter(database.getMovies(), countryFilter);
        currentPage.setCurrentMoviesList(movies);
    }

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

    public static boolean buyTokens(int tokensCount) {
        User currentUser = CurrentUser.getInstance().getUser();

        // verify if the user has enough balance
        if (currentUser.getCredentials().getBalance() < tokensCount) {
            return false;
        }

        currentUser.setTokensCount(currentUser.getTokensCount() + tokensCount);
        currentUser.getCredentials().setBalance(currentUser.getCredentials().getBalance() - tokensCount);

        return true;
    }
}
