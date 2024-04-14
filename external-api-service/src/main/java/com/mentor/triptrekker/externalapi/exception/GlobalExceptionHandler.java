package com.mentor.triptrekker.externalapi.exception;
import com.mentor.triptrekker.externalapi.exception.FlightSearchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    @ExceptionHandler(FlightSearchException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<String> handleFlightSearchException(FlightSearchException ex) {
//        log.error("Error fetching flight data: {}" , ex.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching flight data: " + ex.getMessage());
//    }


//        @ExceptionHandler(HotelSearchException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<String> handleFlightSearchException(HotelSearchException ex) {
//        log.error("Error fetching flight data: {}" , ex.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching Hotel data: " + ex.getMessage());
//    }

}
