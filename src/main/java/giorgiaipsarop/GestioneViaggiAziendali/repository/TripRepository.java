package giorgiaipsarop.GestioneViaggiAziendali.repository;

import giorgiaipsarop.GestioneViaggiAziendali.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<Trip, UUID> {

}