package hotelsearchservice.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class HotelContact {
    private String email;
    private String phone;
    private String facebook;
    private String twitter;
    private String instagram;
}
