package com.mentor.triptrekker.externalapi.response;

import lombok.Data;

import java.util.List;

@Data
public class HotelOfferResponse {
    private List<HotelOfferData> data;
}

@Data
class HotelOfferData {
    private String type;
    private Hotel hotel;
    private boolean available;
    private List<Offer> offers;
    private String self;
}

@Data
class Hotel {
    private String type;
    private String hotelId;
    private String chainCode;
    private String dupeId;
    private String name;
    private String cityCode;
    private double latitude;
    private double longitude;
}

@Data
class Offer {
    private String id;
    private String checkInDate;
    private String checkOutDate;
    private String rateCode;
    private RateFamilyEstimated rateFamilyEstimated;
    private Room room;
    private Guests guests;
    private Price price;
    private Policies policies;
    private String self;
}

@Data
class RateFamilyEstimated {
    private String code;
    private String type;
}

@Data
class Room {
    private String type;
    private TypeEstimated typeEstimated;
    private Description description;
}

@Data
class TypeEstimated {
    private String category;
    private int beds;
    private String bedType;
}

@Data
class Description {
    private String text;
    private String lang;
}

@Data
class Guests {
    private int adults;
}

@Data
class Price {
    private String currency;
    private String base;
    private String total;
    private Variations variations;
}

@Data
class Variations {
    private Average average;
    private List<Change> changes;
}

@Data
class Average {
    private String base;
}

@Data
class Change {
    private String startDate;
    private String endDate;
    private String total;
}

@Data
class Policies {
    private String paymentType;
    private Cancellation cancellation;
}

@Data
class Cancellation {
    private Description description;
    private String type;
}
