package resource;


import models.CatalogItem;
import models.Movie;
import models.Rating;
import models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

    //    WebClient.Builder builder = WebClient.builder();

       // RestTemplate restTemplate = new RestTemplate();
        // Movie movie = restTemplate.getForObject("http://localhost:8081/movies/foo", Movie.class);

//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("2345", 3),
//                new Rating("1258", 5)
//        );

       UserRating ratings = restTemplate.getForObject("http://Rating-Data-Service/ratingdata/users/"+userId, UserRating.class);

        return ratings.getRatings().stream().map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

//                  Movie movie =  webClientBuilder.build()
//                            .get()
//                            .uri("http://localhost:8081/movies/"+rating.getMovieId())
//                            .retrieve()
//                            .bodyToMono(Movie.class)
//                            .block();
                    return  new CatalogItem(movie.getTitle(), "description", rating.getRating());
                })
                .collect(Collectors.toList());


//        return Collections.singletonList(
//                new CatalogItem("ye dil mange more","Romance",4)
//        );
    }
}
