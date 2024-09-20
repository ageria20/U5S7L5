package ageria.U5S7L5.dto;

import jakarta.validation.constraints.NotNull;

public record BookingDTO(@NotNull
                         Long id,
                         @NotNull
                         Long user_id,
                         @NotNull
                         Long event_id
) {
}
