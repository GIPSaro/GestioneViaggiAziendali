package giorgiaipsarop.GestioneViaggiAziendali.services;

import giorgiaipsarop.GestioneViaggiAziendali.entities.Log;
import giorgiaipsarop.GestioneViaggiAziendali.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public Log createLog(Log log) {
        return logRepository.save(log);
    }

    public Optional<Log> getLogById(UUID id) {
        return logRepository.findById(id);
    }

    public Iterable<Log> getAllLogs() {
        return logRepository.findAll();
    }

    public Log updateLog(UUID id, Log logDetails) {
        return logRepository.findById(id).map(log -> {
            log.setTimestamp(logDetails.getTimestamp());
            log.setMessage(logDetails.getMessage());
            log.setLevel(logDetails.getLevel());
            return logRepository.save(log);
        }).orElse(null);
    }

    public void deleteLog(UUID id) {
        logRepository.deleteById(id);
    }
}
