package ageria.U5S7L5.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "events")
@JsonIgnoreProperties({"userManager"})
public class Event {

    @Id
    @GeneratedValue
    private long id;

    private String title;
    private String description;
    @Column(name = "event_date")
    private LocalDate eventDate;
    @Column(name = "event_place")
    private String eventPlace;
    private int seats;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User userManager;

    public Event(String title, String description, LocalDate eventDate, String eventPlace, int seats, User userManager) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.eventPlace = eventPlace;
        this.seats = seats;
        this.userManager = userManager;
    }
}
