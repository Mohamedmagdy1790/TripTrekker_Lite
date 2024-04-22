package com.mentor.triptrekker.flightsearchservice.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Map;

@Data
@Slf4j
public class FlightSearchData implements Serializable{
	
	
	private static final long serialVersionUID = -3334957280558373425L;
	private String airline;
    private Map<String, FlightResponse> data;
    private String version;

}