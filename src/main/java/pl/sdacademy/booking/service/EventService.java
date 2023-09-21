package pl.sdacademy.booking.service;

import lombok.extern.slf4j.Slf4j;
import pl.sdacademy.booking.data.EventEntity;
import pl.sdacademy.booking.data.ItemEntity;
import pl.sdacademy.booking.model.EventDto;
import pl.sdacademy.booking.model.NewEventDto;
import pl.sdacademy.booking.repository.EventRepository;
import pl.sdacademy.booking.repository.ItemRepository;
import pl.sdacademy.booking.validator.NewEventDtoValidator;

import java.util.ArrayList;
import java.util.List;


// todo zaimplementowac serwis EntityService z metoda findAll - zwraca EventDto
//  w serwisie, w metoddzie findAll zmienic postac EventEntity na EventDto
@Slf4j
public class EventService {
    private final EventRepository eventRepository;
    private final ItemRepository itemRepository;

    public EventService(EventRepository eventRepository, ItemRepository itemRepository) {
        this.eventRepository = eventRepository;
        this.itemRepository = itemRepository;
    }

    public List<EventDto> findAll() {
        log.info("findAll");
        List<EventDto> result = new ArrayList<>();

        List<EventEntity> eventEntities = eventRepository.findAll();
        for (EventEntity entity : eventEntities) {
            result.add(EventDto.builder()
                    .name(entity.getItem().getName())
                    .price(entity.getItem().getPrice())
                    .fromTime(entity.getFrom())
                    .toTime(entity.getTo())
                    .build());
        }
        return result;
    }


    public String addEvent(NewEventDto newEvent) {
        List<String> validate = NewEventDtoValidator.validate(newEvent);
        if (validate.size() != 0) {
            String message = String.join(",", validate);
            return message;
        }
//        Long eventsByName = eventRepository.findEventsByDate(newEvent.getFromTime());
//        if (eventsByName != null) {
//            return "Sesja już istnieje.";
//        }
        EventEntity eventEntity = new EventEntity();
        Long itemByName = itemRepository.findItemByName(newEvent.getItemName());

        if(itemByName==null ||itemByName==-1L){
            return "Nie znaleziono tego obiektu";
        }

        ItemEntity itemEntity=new ItemEntity();
        itemEntity.setId(itemByName);

        eventEntity.setItem(itemEntity);
        eventEntity.setFrom(newEvent.getFromTime());
        eventEntity.setTo(newEvent.getToTime());

        //tutaj bedzie wyszukiwanie id_itemu po jego nazwie -
        //być może można wykorzystać metode repostitory Item findbyName
        //eventEntity.setItem(itemId)
        eventEntity.setFrom(newEvent.getFromTime());
        eventEntity.setTo(newEvent.getToTime());
        eventRepository.addEvent(eventEntity);
        return "Sesja została zapisana";
    }
}

