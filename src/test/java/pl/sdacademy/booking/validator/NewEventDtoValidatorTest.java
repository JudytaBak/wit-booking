package pl.sdacademy.booking.validator;

import lombok.Builder;
import org.junit.jupiter.api.Test;
import pl.sdacademy.booking.model.NewEventDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NewEventDtoValidatorTest {
    @Test
    void shouldCheckThatFromIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .fromTime(null)
                .toTime(LocalDateTime.of(2023, 9, 19, 19, 56))
                .build();
        LocalDateTime referenceDate = LocalDateTime.of(2023, 9, 21, 12, 0);
        List<String> result = NewEventDtoValidator.validate(input, referenceDate);
        assertThat(result).hasSize(1).contains("From is null");
    }

    @Test
    void shouldCheckThatToIsNull() {
        NewEventDto input = NewEventDto.builder().itemName("przyklad")
                .toTime(null)
                .fromTime(LocalDateTime.of(2023, 9, 19, 19, 56))
                .build();
        LocalDateTime referenceDate = LocalDateTime.of(2023, 9, 21, 12, 0);
        List<String> result = NewEventDtoValidator.validate(input, referenceDate);
        assertThat(result).hasSize(1).contains("To is null");
    }

    @Test
    void shouldCheckThatToAndFromIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .toTime(null)
                .fromTime(null)
                .build();
        LocalDateTime referenceDate = LocalDateTime.of(2023, 9, 21, 12, 0);
        List<String> result = NewEventDtoValidator.validate(input, referenceDate);
        assertThat(result).hasSize(2).contains("To is null");
    }
    @Test
    void shouldCheckThatDurationIsNegative() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .fromTime(LocalDateTime.of(2023, 9, 21, 14, 0))
                .toTime(LocalDateTime.of(2023, 9, 21, 12, 0))
                .build();
        LocalDateTime referenceDate = LocalDateTime.of(2023, 9, 21, 12, 0);
        List<String> result = NewEventDtoValidator.validate(input, referenceDate);
        assertThat(result).hasSize(1).contains("Sorry, wrong time");
    }
    @Test
    void shouldCheckThatDurationIsTooLong() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .fromTime(LocalDateTime.of(2023, 9, 21, 10, 0))
                .toTime(LocalDateTime.of(2023, 9, 21, 11, 0))
                .build();
        LocalDateTime referenceDate = LocalDateTime.of(2023, 9, 21, 12, 0);
        List<String> result = NewEventDtoValidator.validate(input, referenceDate);
        assertThat(result).hasSize(1).contains("Sorry, wrong time");
    }
    @Test
    void shouldCheckThatToAndFromIsCorrectAsCompareToReferenceDate() {
        //możemy jawnie utworzyć obiekt LocalDateTime przed wywołaniem (ale trzeba wprowadzic date)
        LocalDateTime referenceDate = LocalDateTime.of(2023, 9, 21, 12, 0);
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .fromTime(LocalDateTime.of(2023, 9, 21, 10, 0))
                .toTime(LocalDateTime.of(2023, 9, 21, 14, 0))
                .build();
        List<String> result = NewEventDtoValidator.validate(input, referenceDate);
        //daty sa zgodne
        assertThat(result).isEmpty();

        //Możemy sprawić, że usługa będzie rozpoznawać dynamikę, LocalDateTime
        // używając jawnego zegara zamiast LocalDateTime.now()


    }
}

