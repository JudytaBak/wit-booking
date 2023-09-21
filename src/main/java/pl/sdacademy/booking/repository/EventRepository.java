package pl.sdacademy.booking.repository;

import pl.sdacademy.booking.data.EventEntity;

import java.time.LocalDateTime;
import java.util.List;

/*
definniuje zestaw metod dostepu do danych, nie zawiera ich implementacji
umozliwia łatwiejsze tworzenie testów
definiuje "kontrakt" dostępu do danych
 */
public interface EventRepository {

    List<EventEntity> findEvents();
    void addEvent(EventEntity event);
    Long findEventByDate(LocalDateTime date);

}
