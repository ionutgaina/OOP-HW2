package database;

import java.util.ArrayList;

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
        if (movies.contains(movie))
            return false;

        movies.add(movie);
        return true;
    }


    /**
     * delete a movie from the database
     *
     * @param movieName name of the movie to be deleted
     * @return true if the movie was deleted, false otherwise
     */
    public boolean delete(final String movieName) {
        Movie movieToDelete = movies.stream()
                .filter(movie -> movie.getName().equals(movieName))
                .findFirst()
                .orElse(null);

        if (movieToDelete == null) {
            return false;
        }

        // remove from all users the movie with streams
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
                          }

                            Notification notification = new Notification();
                            notification.setMovieName(movieToDelete.getName());
                            notification.setMessage("DELETE");
                            user.getNotifications().add(notification);
                      });


        movies.remove(movieToDelete);
        return true;
    }
}
