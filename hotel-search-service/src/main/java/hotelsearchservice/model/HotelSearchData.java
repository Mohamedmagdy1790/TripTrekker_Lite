package hotelsearchservice.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Data
public class HotelSearchData {
    private Map<String, HotelDetails> data;

}
