package database.filters;

public final class Sort {
    private String rating;
    private String duration;

    public Sort() { }

    public Sort(String rating, String duration) {
        this.rating = rating;
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
