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
        @Future(message = "La data deve essere una data futura")
        LocalDate date,
        @NotEmpty(message = "Lo stato non può essere vuoto")
        String status
){

}
