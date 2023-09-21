package pl.sdacademy.booking.controller;

import pl.sdacademy.booking.repository.EventRepository;
import pl.sdacademy.booking.repository.EventRepositoryImpl;
import pl.sdacademy.booking.service.EventService;

public class EventController {
    private final EventService eventService;

    public EventController() {
        this.eventService = new EventService(new EventRepositoryImpl());
    }

    public void presentEventSchedule() {
        System.out.println("\n---------Terminy sesji--------\n");
        eventService.findEvents().forEach(System.out::println);
        System.out.println("\n----------Koniec-------------\n");
    }
}
