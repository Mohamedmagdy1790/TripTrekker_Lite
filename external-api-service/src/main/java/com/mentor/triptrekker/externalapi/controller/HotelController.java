package com.mentor.triptrekker.externalapi.controller;


import com.mentor.triptrekker.externalapi.request.FlightRequest;
import com.mentor.triptrekker.externalapi.response.FlightOfferResponse;
import com.mentor.triptrekker.externalapi.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final ExternalApiService externalApiService;

//    @PostMapping("/search")
//    public Mono<ResponseEntity<HotelOfferResponse>> searchFlights(@RequestBody HotelRequest request) {
//        return externalApiService.searchHotel(request)
//                .map(response -> ResponseEntity.ok().body(response))
//                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
//    }
}
