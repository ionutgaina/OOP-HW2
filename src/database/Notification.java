package database;

import lombok.Data;

@Data
public final class Notification {
    private String movieName;
    private String message;

    public static final class NotificationBuilder {
        private String movieName;
        private String message;

        public NotificationBuilder() {
        }

        /**
         * set movie name to notification
         *
         * @param movieNameSet the movie name to set
         * @return the builder
         */
        public NotificationBuilder movieName(final String movieNameSet) {
            this.movieName = movieNameSet;
            return this;
        }

        /**
         * set message to notification
         *
         * @param messageSet the message to set
         * @return the builder
         */
        public NotificationBuilder message(final String messageSet) {
            this.message = messageSet;
            return this;
        }


        /**
         * set the notification
         *
         * @param notification is the notification to set
         * @return the notification
         */
        public NotificationBuilder notification(final Notification notification) {
            this.movieName = notification.movieName;
            this.message = notification.message;
            return this;
        }


        /**
         * build the notification
         *
         * @return the notification
         */
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
