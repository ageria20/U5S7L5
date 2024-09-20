package ageria.U5S7L5.service;


import ageria.U5S7L5.dto.EventDTO;
import ageria.U5S7L5.dto.EventUpdateDTO;
import ageria.U5S7L5.entities.Event;
import ageria.U5S7L5.entities.User;
import ageria.U5S7L5.exceptions.BadRequestException;
import ageria.U5S7L5.exceptions.NotFoundException;
import ageria.U5S7L5.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserService userService;


    public Page<Event> getAllEvents(int pages, int size, String sortBy) {
        if (pages > 50) pages = 50;
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy));
        return this.eventRepository.findAll(pageable);
    }


    public Event findById(Long id) {
        return this.eventRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Event saveEvent(EventDTO body) {
        if (this.eventRepository.existsByEventPlace(body.eventPlace())) {
            throw new BadRequestException("THIS EVENT IS ALREADY CREATED");
        }
        User user = this.userService.findById(body.manager_id());
        Event event = new Event(
                body.title(),
                body.description(),
                body.eventDate(),
                body.eventPlace(),
                body.seats(),
                user
        );

        return this.eventRepository.save(event);
    }

    public Event findByIdAndUpdate(Long id, EventUpdateDTO body) {
        Event event = this.findById(id);
        event.setTitle(body.title());
        event.setDescription(body.description());
        event.setEventDate(body.eventDate());
        event.setEventPlace(body.eventPlace());
        event.setSeats(body.seats());
        return this.eventRepository.save(event);
    }

    // PATCH to update seats event
    public Event findByIdAndUpdateSeats(Long id, EventUpdateDTO body) {
        Event eventFromDB = this.findById(id);
        eventFromDB.setSeats(body.seats());
        return this.eventRepository.save(eventFromDB);
    }

    // DELETE
    public void deleteEvent(Long id) {
        Event event = this.findById(id);
        this.eventRepository.delete(event);
    }
}
