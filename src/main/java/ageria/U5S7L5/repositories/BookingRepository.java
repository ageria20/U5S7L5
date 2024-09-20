package ageria.U5S7L5.repositories;


import ageria.U5S7L5.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {


    boolean existsByUserIdAndEventId(Long userId, Long eventId);

    List<Booking> findByUserId(Long id);
}
