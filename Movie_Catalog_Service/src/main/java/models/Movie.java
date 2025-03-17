package models;

public class Movie {
    private String movieId;
    private String title;

    public Movie(String movieId, String title) {
        this.movieId = movieId;
        this.title = title;
    }
    public Movie() {};

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
