package giorgiaipsarop.GestioneViaggiAziendali.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class ErrorsPayloadWithList extends ErrorsPayload {
    private List<String> errors;

    public ErrorsPayloadWithList(String message, LocalDateTime timestamp, List<String> errors) {
        super(message, timestamp);
        this.errors = errors;
    }


    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}