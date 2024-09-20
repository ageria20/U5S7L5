package ageria.U5S7L5.controllers;


import ageria.U5S7L5.dto.BookingDTO;
import ageria.U5S7L5.entities.Booking;
import ageria.U5S7L5.exceptions.BadRequestException;
import ageria.U5S7L5.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Booking> findAll(@RequestParam(defaultValue = "0") int pages,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {
        return this.bookingService.getAllBookings(pages, size, sortBy);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking saveBooking(@RequestBody BookingDTO body, BindingResult resultValidation) {
        if (resultValidation.hasErrors()) {
            String msg = resultValidation.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining());
            throw new BadRequestException(msg);
        } else {
            return this.bookingService.saveBooking(body);
        }
    }
}
