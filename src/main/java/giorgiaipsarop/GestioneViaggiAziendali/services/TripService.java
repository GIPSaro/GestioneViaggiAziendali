package giorgiaipsarop.GestioneViaggiAziendali.services;

import giorgiaipsarop.GestioneViaggiAziendali.TripStatus;
import giorgiaipsarop.GestioneViaggiAziendali.entities.Trip;
import giorgiaipsarop.GestioneViaggiAziendali.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public Optional<Trip> getTripById(UUID id) {
        return tripRepository.findById(id);
    }

    public Iterable<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip updateTrip(UUID id, Trip tripDetails) {
        return tripRepository.findById(id).map(trip -> {
            trip.setDestination(tripDetails.getDestination());
            trip.setDate(tripDetails.getDate());
            trip.setStatus(tripDetails.getStatus());
            validateTrip(trip); // Validazione dell'aggiornamento
            return tripRepository.save(trip);
        }).orElse(null);
    }

    public void deleteTrip(UUID id) {
        tripRepository.deleteById(id);
    }

    public void validateTrip(Trip trip) {
        if (trip.getStatus() == TripStatus.IN_PROGRAMMA && trip.getDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data del viaggio in programma deve essere futura");
        } else if (trip.getStatus() == TripStatus.COMPLETATO && trip.getDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La data del viaggio completato non può essere futura");
        }

    }
    public Trip updateTripStatus(UUID id, TripStatus status) {
        return tripRepository.findById(id).map(trip -> {
            trip.setStatus(status);
            return tripRepository.save(trip);
        }).orElse(null);
    }
}
