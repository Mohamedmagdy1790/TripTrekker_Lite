package hotelsearchservice.integration;

import hotelsearchservice.model.HotelSearchCriteria;
import hotelsearchservice.model.HotelSearchData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class HotelExternalApiIntegration {
    private final RestTemplate restTemplate;

    @Value("${hotelApi.host}")
    private String hotelApiHost;

    @Value("${hotelApi.key}")
    private String hotelApiKey;

    @Value("${hotelApi.url}")
    private String hotelApiUrl;

    public HotelExternalApiIntegration(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public HotelSearchData searchHotels(HotelSearchCriteria criteria) {
        // should be async with completeable Future in case of multiple API Calls in the
        // Flight service

        log.info("criteria : {} ", criteria.toString());
        // Set query parameters using UriComponentsBuilder
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(hotelApiUrl)
                .queryParam("checkInDate", criteria.getCheckInDate()).queryParam("checkOutDate", criteria.getCheckOutDate())
                .queryParam("location", criteria.getLocation())
                .queryParam("priceRange", criteria.getPriceRange())
                .queryParam("starRating", criteria.getStarRating())
                .queryParam("numberOfRooms", criteria.getNumberOfRooms())
                .queryParam("roomType", criteria.getRoomType())
                .queryParam("entertainment", criteria.getEntertainment());

        // Build URL with query parameters
        String fullUrl = builder.toUriString();

        log.info("fullUrl : {}", fullUrl);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", hotelApiKey);
        headers.set("X-RapidAPI-Host", hotelApiHost);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make API call
        ResponseEntity<HotelSearchData> response = restTemplate.exchange(fullUrl, HttpMethod.GET, entity,
                HotelSearchData.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            log.info("API Call status code: {}", response.getStatusCode());
            throw new RuntimeException("Failed to retrieve flight data. Status code: " + response.getStatusCodeValue());
        }
    }


}
