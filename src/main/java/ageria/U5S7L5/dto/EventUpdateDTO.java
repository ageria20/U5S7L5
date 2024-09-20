package ageria.U5S7L5.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventUpdateDTO(@NotNull(message = "Title is required")
                             String title,
                             @NotNull(message = "Description event is required")
                             String description,
                             @NotNull(message = "Event Date is required")
                             LocalDate eventDate,
                             @NotNull(message = "The Event Place is required")
                             String eventPlace,
                             @NotNull(message = "Number of seats is required")
                             int seats) {
}
