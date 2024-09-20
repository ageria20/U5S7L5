package ageria.U5S7L5.repositories;


import ageria.U5S7L5.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsByEventPlace(String eventPlace);

//    Long findByUserManager(Long managerId);


}
