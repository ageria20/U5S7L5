package ageria.U5S7L5.repositories;


import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository {

    boolean existsByEventPlace(String eventPlace);

}
