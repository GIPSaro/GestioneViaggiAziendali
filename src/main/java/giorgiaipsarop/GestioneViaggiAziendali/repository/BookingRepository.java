package giorgiaipsarop.GestioneViaggiAziendali.repository;


import giorgiaipsarop.GestioneViaggiAziendali.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    boolean existsByEmployeeIdAndRequestDate(UUID employeeId, LocalDate requestDate);

}
