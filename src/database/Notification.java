package database;

import lombok.Data;

@Data
public class Notification {
    private String movieName;
    private String message;

    public Notification(final String movieName, final String message) {
        this.movieName = movieName;
        this.message = message;
    }

    public Notification(final Notification notification) {
        this.movieName = notification.getMovieName();
        this.message = notification.getMessage();
    }
}
