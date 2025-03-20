package resource;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import models.CatalogItem;
import models.Movie;
import models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
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
    @CircuitBreaker(name = "MovieCatalogService", fallbackMethod = "getFallBackCatalog")
    @Retry(name = "MovieCatalogService")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
       UserRating ratings = restTemplate.getForObject("http://Rating-Data-Service/ratingdata/users/"+userId, UserRating.class);

        return ratings.getRatings().stream().map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
                    return  new CatalogItem(movie.getTitle(), "description", rating.getRating());
                })
                .collect(Collectors.toList());
    }
    public List<CatalogItem> getFallBackCatalog(@PathVariable("userId") String userId, Throwable throwable) {
        System.out.println("Fallback triggered due to: " + throwable.getMessage());
        return Arrays.asList(new CatalogItem("No Movie Found", "No Movie Found", 0));
    }

}


//web client
//                  Movie movie =  webClientBuilder.build()
//                            .get()
//                            .uri("http://localhost:8081/movies/"+rating.getMovieId())
//                            .retrieve()
//                            .bodyToMono(Movie.class)
//                            .block();


