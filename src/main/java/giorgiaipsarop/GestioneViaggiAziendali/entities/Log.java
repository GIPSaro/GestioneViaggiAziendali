package giorgiaipsarop.GestioneViaggiAziendali.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "logs")
@NoArgsConstructor
public class Log {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    private LocalDateTime timestamp;
    private String message;
    private String level;

    public Log(LocalDateTime timestamp, String message, String level) {
        this.timestamp = timestamp;
        this.message = message;
        this.level = level;
    }
}
