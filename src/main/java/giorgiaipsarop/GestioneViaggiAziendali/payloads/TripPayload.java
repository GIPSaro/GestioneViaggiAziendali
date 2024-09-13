package giorgiaipsarop.GestioneViaggiAziendali.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record TripPayload (
        @NotEmpty(message = "La destinazione è obbligatoria")
        String destination,
        @NotNull(message = "La data è obbligatoria")
        @NotNull(message = "La data è obbligatoria")
        LocalDate date,
        @NotEmpty(message = "Lo stato non può essere vuoto")
        String status
){

}
