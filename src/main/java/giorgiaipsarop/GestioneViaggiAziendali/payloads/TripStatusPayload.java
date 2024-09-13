package giorgiaipsarop.GestioneViaggiAziendali.payloads;

import jakarta.validation.constraints.NotNull;

public class TripStatusPayload {

    @NotNull
    private String status;

    // Getter e Setter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}