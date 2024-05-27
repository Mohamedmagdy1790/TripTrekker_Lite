package com.mentor.triptrekker.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class FlightSearchController {

    @GetMapping("/hotel/search")
    @PreAuthorize("hasRole('client_admin')")
    String getHotels(){
        // call using rest template  flight search module  or delegate to it responsibility to return data via its controller
        return "dump flight data";
    }

}
