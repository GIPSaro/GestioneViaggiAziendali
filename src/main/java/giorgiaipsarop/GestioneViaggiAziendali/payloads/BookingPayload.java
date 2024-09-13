package giorgiaipsarop.GestioneViaggiAziendali.payloads;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record BookingPayload(
        @NotNull(message = "La data è obbligatoria")
        LocalDate requestDate,
        String notes,
        String preferences,
        @NotEmpty(message = "L'Id del viaggio è obbligatorio")
        String tripId,
        @NotEmpty(message = "L'Id del dipendente è obbligatorio")
        String employeeId
) {}
