package ageria.U5S7L5.service;


import ageria.U5S7L5.dto.EventDTO;
import ageria.U5S7L5.entities.Event;
import ageria.U5S7L5.entities.User;
import ageria.U5S7L5.exceptions.BadRequestException;
import ageria.U5S7L5.exceptions.NotFoundException;
import ageria.U5S7L5.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserService userService;


    public Event findById(Long id) {
        return this.eventRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Event saveEvent(EventDTO body) {
        if (this.eventRepository.existsByEventPlace(body.eventPlace())) {
            throw new BadRequestException("THIS EVENT IS ALREADY CREATED");
        }
        User user = this.userService.findById(body.manager_id());
        Event event = new Event(
                body.Title(),
                body.descrioption(),
                body.eventDate(),
                body.eventPlace(),
                body.seats(),
                user
        );

        return this.eventRepository.save(event);
    }
}
