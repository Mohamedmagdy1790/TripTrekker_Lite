package com.mentor.triptrekker.externalapi.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class HotelRequest {

    @Pattern(regexp = "[0-9A-Z]{8}(,[0-9A-Z]{8})*")
    private String[] hotelIds;

    @Min(value = 1)
    @Max(value = 9)
    private Integer adults;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private String checkInDate;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private String checkOutDate;
    @Min(value = 1)
    @Max(value = 9)
    private int roomQuantity;

    @Pattern(regexp = "[A-Z]{2}")
    private String countryOfResidence;

    private String priceRange;

    @Pattern(regexp = "[A-Z]{3}")
    private String currency;

    private String paymentType = "NONE";

    private String boardType = "ROOM_ONLY";
}
