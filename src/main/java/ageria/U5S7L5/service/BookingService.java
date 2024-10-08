package ageria.U5S7L5.service;


import ageria.U5S7L5.dto.BookingDTO;
import ageria.U5S7L5.entities.Booking;
import ageria.U5S7L5.entities.Event;
import ageria.U5S7L5.entities.User;
import ageria.U5S7L5.exceptions.BadRequestException;
import ageria.U5S7L5.exceptions.NotFoundException;
import ageria.U5S7L5.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    public Page<Booking> getAllBookings(int pages, int size, String sortBy) {
        if (pages > 50) pages = 50;
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy));
        return this.bookingRepository.findAll(pageable);
    }

    public List<Booking> getBookings(Long id) {
        return this.bookingRepository.findByUserId(id);
    }

    public Booking findById(Long id) {
        return this.bookingRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Booking saveBooking(BookingDTO body) {
        Event eventFormDB = this.eventService.findById(body.event_id());
        User userFormDB = this.userService.findById(body.user_id());
        if (this.bookingRepository.existsByUserIdAndEventId(userFormDB.getId(), eventFormDB.getId())) {
            throw new BadRequestException("YOU HAVE ALREADY A RESERVATION");
        } else {
            if (eventFormDB.getSeats() <= 0) {
                throw new BadRequestException("THERE ARE NO AVAILABLE SEATS FOR THIS EVENT");
            }
            // avrei dovuto mettere una colonna in piu' che mi dicesse quanti posti ho liberi che mi li aggiornasse
            // e una colonna con un numero di posti fisso
            eventFormDB.setSeatsFree(eventFormDB.getSeatsFree() - 1);
            Booking newBooking = new Booking(userFormDB, eventFormDB);
            return this.bookingRepository.save(newBooking);
        }
    }

}
