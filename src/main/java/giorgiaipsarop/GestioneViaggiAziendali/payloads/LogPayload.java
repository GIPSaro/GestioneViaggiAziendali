package giorgiaipsarop.GestioneViaggiAziendali.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record LogPayload(
        @NotNull(message = "Timestamp cannot be null")
        LocalDateTime timestamp,
        @NotEmpty(message = "Message cannot be empty")
        String message,
        @NotEmpty(message = "Level cannot be empty")
        String level
) {}
