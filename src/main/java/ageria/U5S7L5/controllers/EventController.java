package ageria.U5S7L5.controllers;


import ageria.U5S7L5.dto.EventDTO;
import ageria.U5S7L5.entities.Event;
import ageria.U5S7L5.exceptions.BadRequestException;
import ageria.U5S7L5.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;

    //GET ALL EVENTs

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Page<Event> findAll(@RequestParam(defaultValue = "0") int pages,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return this.eventService.getAllEvents(pages, size, sortBy);
    }


    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Event saveEvent(@RequestBody @Validated EventDTO body, BindingResult resultValidation) {
        if (resultValidation.hasErrors()) {
            String msg = resultValidation.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining());
            throw new BadRequestException(msg);
        } else {
            return this.eventService.saveEvent(body);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthorities('ADMIN, MANAGER)")
    public Event updatEvent(@RequestBody @Validated EventDTO body, BindingResult resultValidation){
        if(resultValidation.hasErrors()){
            String msg = resultValidation.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining());
            throw new BadRequestException(msg)
        }

        return this.eventService.findByIdAndUpdate()
    }
}
