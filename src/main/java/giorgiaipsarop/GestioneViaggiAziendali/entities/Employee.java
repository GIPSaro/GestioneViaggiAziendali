package giorgiaipsarop.GestioneViaggiAziendali.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="employees")
@ToString
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public Employee(String username, String firstName, String lastName, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}