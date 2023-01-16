package database;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

import static utilities.Constants.NUM_FREE_PREMIUM_MOVIES;

public final class User {

    private Credentials credentials;
    private int tokensCount = 0;
    private int numFreePremiumMovies = NUM_FREE_PREMIUM_MOVIES;
    private ArrayList<Movie> purchasedMovies = new ArrayList<>();
    private ArrayList<Movie> watchedMovies = new ArrayList<>();
    private ArrayList<Movie> likedMovies = new ArrayList<>();
    private ArrayList<Movie> ratedMovies = new ArrayList<>();

    private ArrayList<Notification> notifications = new ArrayList<>();

    @JsonIgnore
    private ArrayList<String> subscribedGenres = new ArrayList<>();

    @JsonIgnore
    private ArrayList<Integer> ratings = new ArrayList<>();

    public User() {
    }

    public User(final User user) {
        if (user == null) {
            return;
        }

        this.credentials = new Credentials();
        this.credentials.setName(user.getCredentials()
                                     .getName());
        this.credentials.setPassword(user.getCredentials()
                                         .getPassword());
        this.credentials.setAccountType(user.getCredentials()
                                            .getAccountType());
        this.credentials.setCountry(user.getCredentials()
                                        .getCountry());
        this.credentials.setBalance(user.getCredentials()
                                        .getBalance());

        this.tokensCount = user.tokensCount;
        this.numFreePremiumMovies = user.numFreePremiumMovies;
        this.purchasedMovies = new ArrayList<>();
        user.getPurchasedMovies()
            .forEach((movie) -> {
                this.purchasedMovies.add(new Movie(movie));
            });
        this.watchedMovies = new ArrayList<>();
        user.getWatchedMovies()
            .forEach((movie) -> {
                this.watchedMovies.add(new Movie(movie));
            });
        this.likedMovies = new ArrayList<>();
        user.getLikedMovies()
            .forEach((movie) -> {
                this.likedMovies.add(new Movie(movie));
            });
        this.ratedMovies = new ArrayList<>();
        user.getRatedMovies()
            .forEach((movie) -> {
                this.ratedMovies.add(new Movie(movie));
            });

        this.notifications = new ArrayList<>();
        user.getNotifications()
            .forEach((notification) -> {
                this.notifications.add(new Notification.NotificationBuilder()
                                               .notification(notification)
                                               .build());
            });
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(final ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<String> getSubscribedGenres() {
        return subscribedGenres;
    }

    public void setSubscribedGenres(final ArrayList<String> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(final ArrayList<Integer> ratings) {
        this.ratings = ratings;
    }
}
