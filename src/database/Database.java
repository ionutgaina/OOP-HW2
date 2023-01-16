package database;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class Database {
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    private ArrayList<Action> actions;

    public Database() {
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(final ArrayList<Action> actions) {
        this.actions = actions;
    }

    /**
     * add a movie to the database
     *
     * @param movie movie to be added
     * @return true if the movie was added, false otherwise
     */
    public boolean add(final Movie movie) {

        String movieName = movie.getName();

        if (movies.stream().anyMatch(m -> m.getName().equals(movieName))) {
            return false;
        }

        movies.add(movie);
        notifyUsers(movie);
        return true;
    }

    /**
     * notify users that a movie was added
     *
     * @param movie movie that was added
     */

    public void notifyUsers(final Movie movie) {
        ArrayList<String> bannedCountry = movie.getCountriesBanned();

        Notification notification =
                new Notification.NotificationBuilder()
                .movieName(movie.getName())
                .message("ADD")
                .build();

        ArrayList<String> movieGenres = movie.getGenres();

        // deep copy users
        ArrayList<User> usersCopy = new ArrayList<>();
        usersCopy.addAll(users);

        // filter users by subscribed genres
        usersCopy = usersCopy.stream().filter(user -> user.getSubscribedGenres().stream()
                                                          .anyMatch(movieGenres::contains))
                             .collect(Collectors.toCollection(ArrayList::new));

        // filter users by banned countries
        usersCopy.stream()
                 .filter(user -> !bannedCountry.contains(user.getCredentials().getCountry()))
                 .forEach(user -> user.getNotifications().add(notification));
    }

    /**
     * delete a movie from the database
     *
     * @param movieName name of the movie to be deleted
     * @return true if the movie was deleted, false otherwise
     */
    public boolean delete(final String movieName) {
        Movie movieToDelete = movies.stream().filter(movie -> movie.getName().equals(movieName))
                                    .findFirst().orElse(null);

        if (movieToDelete == null) {
            return false;
        }

        // remove from all users the movie
        users.forEach(user -> {
            user.getRatedMovies().remove(movieToDelete);
            user.getLikedMovies().remove(movieToDelete);
            user.getWatchedMovies().remove(movieToDelete);

            if (user.getPurchasedMovies().remove(movieToDelete)) {
                if (user.getCredentials().getAccountType().equals("PREMIUM")) {
                    user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() + 1);
                } else {
                    user.setTokensCount(user.getTokensCount() + 2);
                }

                Notification notification = new Notification.NotificationBuilder()
                        .movieName(movieToDelete.getName())
                        .message("DELETE")
                        .build();
                user.getNotifications().add(notification);
            }
        });


        movies.remove(movieToDelete);
        return true;
    }
}
