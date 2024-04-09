package com.mentor.triptrekker.externalapi.service;


import com.mentor.triptrekker.externalapi.exception.FlightSearchException;
import com.mentor.triptrekker.externalapi.request.FlightRequest;
import com.mentor.triptrekker.externalapi.response.AccessTokenResponse;
import com.mentor.triptrekker.externalapi.response.FlightOfferResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalApiService {

    private final WebClient webClient;
    private String accessToken;
    // Method to fetch access token from the authentication endpoint
    private Mono<String> fetchAccessToken() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", "WaH23cjdMnErK0IqovM19CxPrSXduMsP");
        formData.add("client_secret", "ptxT5PaUgBxTb0lG");
        formData.add("grant_type", "client_credentials");

        return webClient.post()
                .uri("https://test.api.amadeus.com/v1/security/oauth2/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(AccessTokenResponse.class)
                .map(AccessTokenResponse::getAccess_token);
    }

    // Method to ensure that token is available and valid
    private Mono<String> getAccessToken() {
        if (accessToken == null) {
            return fetchAccessToken().doOnNext(token -> this.accessToken = token);
        } else {
            return Mono.just(accessToken);
        }
    }

    public Mono<FlightOfferResponse> searchFlights(FlightRequest request) {
        return getAccessToken().flatMap(token -> {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("shopping/flight-offers")
                            .queryParam("originLocationCode", request.getOriginLocationCode())
                            .queryParam("destinationLocationCode", request.getDestinationLocationCode())
                            .queryParam("departureDate", request.getDepartureDate())
                            .queryParam("returnDate", request.getReturnDate())
                            .queryParam("adults", request.getAdults())
                            .queryParam("children", request.getChildren())
                            .queryParam("infants", request.getInfants())
                            .queryParam("travelClass", request.getTravelClass())
                            .queryParam("nonStop", request.getNonStop())
                            .queryParam("currencyCode", request.getCurrencyCode())
                            .queryParam("maxPrice", request.getMaxPrice())
                            .queryParam("max", request.getMax())
                            .build())


                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(FlightOfferResponse.class)
                    .onErrorResume(e -> {
//                        log.error("Error fetching flight data: {} \n {}", e.getMessage(), e.getStackTrace());
                        return Mono.error(new FlightSearchException("Error fetching flight data. : " + e.getMessage()));
                    });
        });
    }

}