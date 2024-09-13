package giorgiaipsarop.GestioneViaggiAziendali.entities;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "bookings")
@NoArgsConstructor
public class Booking {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    private LocalDate requestDate;

    private String notes;

    private String preferences;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Booking(LocalDate requestDate, String notes, String preferences, Trip trip, Employee employee) {
        this.requestDate = requestDate;
        this.notes = notes;
        this.preferences = preferences;
        this.trip = trip;
        this.employee = employee;
    }
}
