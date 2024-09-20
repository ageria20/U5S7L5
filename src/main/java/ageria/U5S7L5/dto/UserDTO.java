package ageria.U5S7L5.dto;

import ageria.U5S7L5.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotNull
        @Size(min = 3, max = 20)
        String name,
        @NotNull
        @Size(min = 3, max = 30)
        String surname,
        @NotNull
        String email,
        @NotNull
        @Size(min = 8)
        String password,
        @NotNull
        Role role
) {
}
