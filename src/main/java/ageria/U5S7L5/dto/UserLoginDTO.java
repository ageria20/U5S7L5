package ageria.U5S7L5.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserLoginDTO(@NotNull(message = "Email is required")
                           String email,
                           @NotNull(message = "Password is required")
                           @Size(min = 8)
                           String password) {
}
