package database;

public final class Action {
    private String type;
    private String page;
    private String movie;
    private String feature;
    private Credentials credentials;
    private String startsWith;
    private Filters filters;
    private String count;
    private int rate;
    private String subscribedGenre;
    private Movie addedMovie;
    private String deletedMovie;

    private String currentPage;

    public Action() {
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(final String movie) {
        this.movie = movie;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(final Filters filters) {
        this.filters = filters;
    }

    public String getCount() {
        return count;
    }

    public void setCount(final String count) {
        this.count = count;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(final int rate) {
        this.rate = rate;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public Movie getAddedMovie() {
        return addedMovie;
    }

    public void setAddedMovie(final Movie addedMovie) {
        this.addedMovie = addedMovie;
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public void setDeletedMovie(final String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final String currentPage) {
        this.currentPage = currentPage;
    }
}
