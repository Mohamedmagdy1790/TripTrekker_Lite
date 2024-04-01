package hotelsearchservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class HotelSearchCriteria {
    private String checkInDate;
    private String checkOutDate;
    private String location;
    private Double priceRange;
    private Integer starRating;
    private Integer numberOfRooms;
    private String roomType;
    private String entertainment;

    public static HotelSearchCriteria createHotelSearchCriteria(String checkInDate, String checkOutDate, String location,
                                                                Double priceRange, Integer starRating, Integer numberOfRooms, String roomType,
                                                                String entertainment) {
        return HotelSearchCriteria.builder().checkInDate(checkInDate).checkOutDate(checkOutDate).location(location)
                .priceRange(priceRange).starRating(starRating).numberOfRooms(numberOfRooms).roomType(roomType)
                .entertainment(entertainment).build();
    }

}
