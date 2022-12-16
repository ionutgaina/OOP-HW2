package website.pages.logged;

import database.User;
import website.CurrentPage;
import website.CurrentUser;

import static utilities.Constants.PREMIUM_PRICE;


public final class Upgrades {
    private static Upgrades instance = null;

    private Upgrades() {
    }

    /**
     * @return the instance of the class Upgrades
     */
    public static Upgrades getInstance() {
        if (instance == null) {
            instance = new Upgrades();
        }
        return instance;
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
                    currentPage.setPage("upgrades");
                    return true;
                }
            }
            case "see details" -> {
                currentPage.setPage("upgrades");
                return true;
            }
            default -> {
                return false;
            }
        }
        return false;
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
