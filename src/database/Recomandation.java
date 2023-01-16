package database;

import website.CurrentDatabase;
import website.CurrentUser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public final class Recomandation {
    private final String error = null;
    private final ArrayList<Movie> currentMoviesList = null;

    private final User currentUser = CurrentUser.getInstance().getUser();

    public Recomandation() {
        Notification notification = new Notification(getRecommendationMovie(), "Recommendation");
        currentUser.getNotifications().add(notification);
    }

    private String getRecommendationMovie() {
        ArrayList<Movie> movies = CurrentDatabase.getInstance().getDatabase().getMovies();
        ArrayList<String> genres = currentUser.getSubscribedGenres();

        if (movies.isEmpty()) {
            return "No recommendation";
        }

        HashMap<String, Integer> genresLikes = getGenresLikes(movies, genres);

        if (genresLikes.isEmpty()) {
            return "No recommendation";
        }

        String recommendationGenre = getRecommendationGenre(genresLikes);

        if (recommendationGenre == null) {
            return "No recommendation";
        }
        // get the movie with the most likes from the recomandation genre
        Movie recommendationMovie;

        // filter watched movies

        recommendationMovie = movies.stream()
                                    .filter(movie -> movie.getGenres().contains(recommendationGenre))
                                    .filter(movie -> !currentUser.getWatchedMovies().contains(movie))
                                    .max(Comparator.comparingInt(Movie::getNumLikes))
                                    .orElse(null);

        if (recommendationMovie == null) {
            return "No recommendation";
        }

        return recommendationMovie.getName();
    }


    private HashMap<String, Integer> getGenresLikes(ArrayList<Movie> movies,
                                                    ArrayList<String> genres) {
        // hasmap with genres and numbers of likes
        HashMap<String, Integer> genresLikes = new HashMap<>();
        if (genres.isEmpty()) {
            // put all genres in the hashmap with streams
            movies.stream()
                  .flatMap(movie -> movie.getGenres().stream())
                  .distinct()
                  .forEach(genre -> genresLikes.put(genre, 0));

            if (genresLikes.isEmpty()) {
                return genresLikes;
            }
        }
        else {
            for (String genre : genres) {
                genresLikes.put(genre, 0);
            }
        }

        // add the number of likes for each genre with streams
        movies.stream()
              .filter(movie -> movie.getNumLikes() > 0)
              .forEach(movie -> {
                  for (String genre : movie.getGenres()) {
                      if (genresLikes.containsKey(genre)) {
                          genresLikes.put(genre, genresLikes.get(genre) + movie.getNumLikes());
                      }
                  }
              });

        // filter genres with 0 likes
        genresLikes.entrySet().removeIf(entry -> entry.getValue() == 0);

        return genresLikes;
    }

    private String getRecommendationGenre(HashMap<String, Integer> genresLikes) {

        String recommendationGenre = null;

        for (String genre : genresLikes.keySet()) {
            if (recommendationGenre == null) {
                recommendationGenre = genre;
            } else if (genresLikes.get(genre) > genresLikes.get(recommendationGenre)) {
                recommendationGenre = genre;
            }
        }

        return recommendationGenre;
    }

    public String getError() {
        return error;
    }

    public ArrayList<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
