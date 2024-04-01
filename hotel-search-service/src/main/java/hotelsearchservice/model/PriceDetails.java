package hotelsearchservice.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class PriceDetails {
    private String date;
    private String price;
}
