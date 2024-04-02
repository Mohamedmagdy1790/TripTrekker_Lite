package hotelsearchservice.controller;

import hotelsearchservice.model.HotelSearchCriteria;
import hotelsearchservice.model.HotelSearchData;
import hotelsearchservice.services.HotelService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/hotels")
public class HotelController {
    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/search")
    @ResponseStatus(code = HttpStatus.OK)
    public HotelSearchData searchHotels(HttpServletRequest request, HttpServletResponse response, @RequestParam String to,
                                         @RequestParam String checkInDate, @RequestParam String checkOutDate, @RequestParam String location,
                                         @RequestParam Double priceRange, @RequestParam Integer numberOfRooms,@RequestParam String roomType,@RequestParam Integer starRating,@RequestParam String entertainment, @RequestParam(required = false) String userId) {

        HotelSearchCriteria searchCriteria = HotelSearchCriteria.createHotelSearchCriteria(checkInDate, checkOutDate, location, priceRange, starRating, numberOfRooms, roomType, entertainment);

        if (userId != null && !userId.isEmpty()) {
            // User is logged in, perform logged-in user logic
            return hotelService.searchFlightsForLoggedInUser(userId, searchCriteria);
        } else {
            // Guest user, perform guest user logic, Get guestUserID
            String guestUserId = GuestUserIdGenerator.generateGuestUserId(request, response);
            log.info("guestUserId : {} ", guestUserId);
            return hotelService.searchFlightsForGuestUser(guestUserId, searchCriteria);
        }
    }

}