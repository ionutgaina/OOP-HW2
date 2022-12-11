package database;

import java.util.ArrayList;

public final class User {

    private Credentials credentials;
    private int tokensCount = 0;
    private int numFreePremiumMovies = 15;
    private ArrayList<Movie> purchasedMovies = new ArrayList<>();
    private ArrayList<Movie> watchedMovies = new ArrayList<>();
    private ArrayList<Movie> likedMovies = new ArrayList<>();
    private ArrayList<Movie> ratedMovies = new ArrayList<>();

    public User() {
    }

    public User(User user) {
        if (user == null) {
            return;
        }

        this.credentials = new Credentials();
        this.credentials.setName(user.getCredentials().getName());
        this.credentials.setPassword(user.getCredentials().getPassword());
        this.credentials.setAccountType(user.getCredentials().getAccountType());
        this.credentials.setCountry(user.getCredentials().getCountry());
        this.credentials.setBalance(user.getCredentials().getBalance());

        this.tokensCount = user.tokensCount;
        this.numFreePremiumMovies = user.numFreePremiumMovies;
        this.purchasedMovies = new ArrayList<>();
        user.getPurchasedMovies().forEach((movie) -> {
            this.purchasedMovies.add(new Movie(movie));
        });
        this.watchedMovies = new ArrayList<>();
        user.getWatchedMovies().forEach((movie) -> {
            this.watchedMovies.add(new Movie(movie));
        });
        this.likedMovies = new ArrayList<>();
        user.getLikedMovies().forEach((movie) -> {
            this.likedMovies.add(new Movie(movie));
        });
        this.ratedMovies = new ArrayList<>();
        user.getRatedMovies().forEach((movie) -> {
            this.ratedMovies.add(new Movie(movie));
        });
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    @Override
    public String toString() {
        return "User{" +
               "credentials=" + credentials +
               '}';
    }
}
