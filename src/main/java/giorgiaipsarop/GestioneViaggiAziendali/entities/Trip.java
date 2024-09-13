package giorgiaipsarop.GestioneViaggiAziendali.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import giorgiaipsarop.GestioneViaggiAziendali.TripStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDate;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private TripStatus status;



    public Trip(String destination, LocalDate date, TripStatus status) {
        this.destination = destination;
        this.date = date;
        this.status = status;
    }


}
