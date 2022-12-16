package website.handlers;

import database.Movie;
import database.User;
import lombok.Data;
import website.CurrentPage;
import website.CurrentUser;

import java.util.ArrayList;

@Data
public final class ErrorHandler {
    private ArrayList<Movie> currentMoviesList;
    private String error;
    private User currentUser;

    public ErrorHandler() {
    }

    public ErrorHandler(final boolean isError) {
        if (isError) {
            error();
        } else {
            success();
        }
    }

    /**
     * Sets the error message.
     */
    private void error() {
        this.error = "Error";
        this.currentMoviesList = new ArrayList<>();
    }

    /**
     * Sets the success message.
     */
    private void success() {
        this.currentMoviesList = new ArrayList<>();
        ArrayList<Movie> moviesList = CurrentPage.getInstance()
                                                 .getCurrentMoviesList();
        moviesList.forEach((movie) -> {
            this.currentMoviesList.add(new Movie(movie));
        });

        User user = CurrentUser.getInstance()
                               .getUser();
        this.currentUser = new User(user);
    }
}
