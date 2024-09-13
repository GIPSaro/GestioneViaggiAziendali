package giorgiaipsarop.GestioneViaggiAziendali.repository;


import giorgiaipsarop.GestioneViaggiAziendali.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {

}
