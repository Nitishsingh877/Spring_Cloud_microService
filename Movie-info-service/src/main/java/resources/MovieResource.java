package resources;

import model.Movie;
import model.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Value("${omdb.api.url}")
    private String omdbApiUrl;

    @Autowired
    private  RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public MovieSummary getMovieInfo(@PathVariable("movieId") String movieId) {
        String url = omdbApiUrl + "?i=" + movieId + "&apikey=" + apiKey;
        return  restTemplate.getForObject(url, MovieSummary.class);

        //return new Movie(movieId, movieSummary.getTitle(),movieSummary.getYear());
    }

}
