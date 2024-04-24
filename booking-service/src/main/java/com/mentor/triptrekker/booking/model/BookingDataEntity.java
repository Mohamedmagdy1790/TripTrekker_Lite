package com.mentor.triptrekker.booking.model;

import com.mentor.triptrekker.booking.enums.BookingStatus;
import com.mentor.triptrekker.booking.request.FlightBookingData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import java.util.List;

@Entity
@Table(name = "booking_data")
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private OfferDataEntity data;
    private DictionariesEntity dictionaries;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TravelerDataEntity> travelersData;
    private BookingStatus status;
}

@Entity
@Table(name = "traveler_data")
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
class TravelerDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String gender;
    private String type;
}

@Entity
@Table(name = "flight_offer")
@Data
class DictionariesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private Map<String, LocationEntity> locations;
    @ElementCollection
    private Map<String, String> aircraft;
    @ElementCollection
    private Map<String, String> currencies;
    @ElementCollection
    private Map<String, String> carriers;
}


@Embeddable
@Data
class LocationEntity {
    private String cityCode;
    private String countryCode;
}

@Entity
@Table(name = "flight_offer")
class OfferDataEntity {
    private String type;
    private String id;
    private String source;
    private boolean instantTicketingRequired;
    private boolean nonHomogeneous;
    private boolean oneWay;
    private String lastTicketingDate;
    private int numberOfBookableSeats;
    @OneToMany(cascade = CascadeType.ALL)
    private List<FlightBookingData.Itinerary> itineraries;
    private FlightBookingData.Price price;
    private FlightBookingData.PricingOptions pricingOptions;
    private List<String> validatingAirlineCodes;
    private List<FlightBookingData.TravelerPricing> travelerPricings;
}

@Entity
@Table(name = "itinerary")
@Data
class ItineraryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String duration;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SegmentEntity> segments;
}

@Entity
@Table(name = "segment")
@Data
class SegmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private DepartureEntity departure;

    @Embedded
    private ArrivalEntity arrival;

    private String carrierCode;
    private String number;

    @Embedded
    private AircraftEntity aircraft;

    @Embedded
    private OperatingEntity operating;

    private String duration;
    private int numberOfStops;
    private boolean blacklistedInEU;
}

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
class DepartureEntity {
    private String iataCode;
    private String terminal;
    private String at;
}

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
class ArrivalEntity {
    private String iataCode;
    private String terminal;
    private String at;
}

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
class AircraftEntity {
    private String code;
}

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
class OperatingEntity {
    private String carrierCode;
}