package ageria.U5S7L5.controllers;


import ageria.U5S7L5.entities.Event;
import ageria.U5S7L5.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;


    public Page<Event> findAll(@RequestParam(defaultValue = "0") int pages,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return this.bookingService.getAllBookings(pages, size, sortBy);
    }
}
