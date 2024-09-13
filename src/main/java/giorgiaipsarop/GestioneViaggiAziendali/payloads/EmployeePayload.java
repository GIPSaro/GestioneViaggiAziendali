package giorgiaipsarop.GestioneViaggiAziendali.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record EmployeePayload(

    @NotEmpty(message = "L'username è obbligatorio")
   String username,

    @NotEmpty(message = "Il nome è obbligatorio")
    @Size(min = 2, message = "Il titolo deve essere di almeno 2 caratteri")
    String firstName,

    @NotEmpty(message = "Il cognome è obbligatorio")
    @Size(min = 2, message = "Il cognome deve essere di almeno 2 caratteri")
     String lastName,

    @Email
    @NotEmpty(message = "L'email è obbligatoria")
     String email,

    String profileImage
    )

        {
}
