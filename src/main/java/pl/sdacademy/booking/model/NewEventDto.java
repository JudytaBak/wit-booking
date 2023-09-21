package pl.sdacademy.booking.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Slf4j
@Builder
//przyjmowanie danych od użytkownika do tworzenia nowych "events"
public class NewEventDto {

    private String itemName;
    private BigDecimal itemPrice;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;

}
