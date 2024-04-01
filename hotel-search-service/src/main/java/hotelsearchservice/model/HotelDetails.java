package hotelsearchservice.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Data
@Slf4j
public class HotelDetails {
        private String hotelName;
        private String hotelLocation;
        private String address;
        private String hotelRating;
        private PriceDetails hotelPrice;
        private String hotelCurrency;
        private List<String> hotelEntertainment;
        private HotelContact hotelContact;
        private String hotelDescription;
        private List<String> hotelImage;
        private List<String> hotelBookingServices;
}
