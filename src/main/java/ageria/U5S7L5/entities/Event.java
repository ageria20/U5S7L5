package ageria.U5S7L5.entities;


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

    @OneToOne
    @JoinColumn(name = "manager_id")
    private User userManager;
}
