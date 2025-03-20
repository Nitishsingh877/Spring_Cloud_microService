package model;

public class Movie {
    private String movieId;
    private String title;
    private String year;

    public Movie(String movieId, String title, String year) {
        this.movieId = movieId;
        this.title = title;
        this.year = year;

    }

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
