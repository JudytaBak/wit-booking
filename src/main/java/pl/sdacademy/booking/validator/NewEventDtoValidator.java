package pl.sdacademy.booking.validator;

import pl.sdacademy.booking.model.NewEventDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewEventDtoValidator {
    public static List<String> validate(NewEventDto newEventDto, LocalDateTime referenceDate) {
        List<String> result = new ArrayList<>();
        //time between 8-16
        if (newEventDto.getFromTime() == null) {
            result.add("From is null"); //fromTime is null
        }
        if (newEventDto.getToTime() == null) {
            result.add("To is null"); //toTime is null
        }
        //czy itemName nie jest null
        if (newEventDto.getItemName() == null) {
            result.add("Wrong item name"); //item name is null
        }
        //fromTime is later than toTime && the event last longer than 30 minutes && date in the past/future
        if (newEventDto.getFromTime() != null && newEventDto.getToTime() != null) {
            Duration duration = Duration.between(newEventDto.getFromTime(), newEventDto.getToTime());
            if (duration.isNegative()) {
                result.add("Sorry, wrong time");
            }
            if (duration.toMinutes() > 30) {
                result.add("Sorry, wrong time");
            }
            if (newEventDto.getToTime().isAfter(referenceDate) || newEventDto.getFromTime().isBefore(referenceDate)) {
                    result.add("Wrong date");
                // lub tak
                //if(newEventDto.getToTime().isAfter(LocalDateTime.now() || newEventDto.getFromTime().isBefore(LocalDateTime.now())
                //czy oba prawidlowe?
            }
        }
        return result;
    }
}
