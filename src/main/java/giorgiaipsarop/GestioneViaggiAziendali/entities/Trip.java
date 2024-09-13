package giorgiaipsarop.GestioneViaggiAziendali.entities;

import giorgiaipsarop.GestioneViaggiAziendali.TripStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="trips")
@ToString
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String destination;
    private Date date;
    @Enumerated(EnumType.STRING)
    private TripStatus status;

    public Trip(String destination, Date date, TripStatus status) {
        this.destination = destination;
        this.date = date;
        this.status = status;
    }
}
