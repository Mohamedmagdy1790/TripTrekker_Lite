package hotelsearchservice.services;

import hotelsearchservice.integration.HotelExternalApiIntegration;
import hotelsearchservice.model.HotelSearchCriteria;
import hotelsearchservice.model.HotelSearchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

public class HotelService {

    @Autowired
    private HotelExternalApiIntegration hotelExternalApiIntegration;

    @Cacheable(value = "hotelSearchData", key = "#guestUserId + '-' + #searchCriteria.hashCode()")
    public HotelSearchData searchFlightsForGuestUser(String guestUserId, HotelSearchCriteria searchCriteria) {
        // Perform the actual search using the provided criteria
//		FlightSearchData flightData = flightExternalApiIntegration.searchFlights(searchCriteria); // API issue
        HotelSearchData flightData = hotelExternalApiIntegration.searchHotels(searchCriteria); // static
        // data

        // Simulate some delay or complex processing
        simulateDelay();

        return flightData;
    }

    @Cacheable(value = "flightSearchCriteria", key = "#loggedInUser + '-' + #searchCriteria.hashCode()")
    public HotelSearchData searchFlightsForLoggedInUser(String loggedInUser, HotelSearchCriteria searchCriteria) {
        // Perform the actual search using the provided criteria
        HotelSearchData flightData = hotelExternalApiIntegration.searchHotels(searchCriteria);

        return flightData;
    }

    // Simulate a delay or complex processing
    private void simulateDelay() {
        try {
            Thread.sleep(2000); // 2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
