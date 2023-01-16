package database;

import lombok.Data;

@Data
public class Notification {
    private String movieName;
    private String message;

    public static class NotificationBuilder {
        private String movieName;
        private String message;

        public NotificationBuilder() {
        }

        public NotificationBuilder movieName(final String movieName) {
            this.movieName = movieName;
            return this;
        }

        public NotificationBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public NotificationBuilder notification(final Notification notification) {
            this.movieName = notification.movieName;
            this.message = notification.message;
            return this;
        }

        public Notification build() {
            return new Notification(this);
        }
    }

    private Notification(final NotificationBuilder notificationBuilder) {
        this.movieName = notificationBuilder.movieName;
        this.message = notificationBuilder.message;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMessage() {
        return message;
    }
}
