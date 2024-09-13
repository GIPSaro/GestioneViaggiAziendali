package giorgiaipsarop.GestioneViaggiAziendali.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class ErrorsPayload {
    // Getters e Setters
    private String message;
    private LocalDateTime timestamp;

    public ErrorsPayload(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
