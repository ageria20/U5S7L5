package ageria.U5S7L5.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotNull(message = "Name is required")
        @Size(min = 3, max = 20)
        String name,
        @NotNull(message = "Surname is required")
        @Size(min = 3, max = 30)
        String surname,
        @NotNull(message = "Email is required")
        String email,
        @NotNull(message = "Password is required")
        @Size(min = 4, max = 8)
        String password
) {
}
