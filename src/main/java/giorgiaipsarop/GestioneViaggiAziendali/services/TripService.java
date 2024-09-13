package giorgiaipsarop.GestioneViaggiAziendali.services;

import giorgiaipsarop.GestioneViaggiAziendali.TripStatus;
import giorgiaipsarop.GestioneViaggiAziendali.entities.Trip;
import giorgiaipsarop.GestioneViaggiAziendali.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
            return tripRepository.save(trip);
        }).orElse(null);
    }

    public void deleteTrip(UUID id) {
        tripRepository.deleteById(id);
    }

    public Trip updateTripStatus(UUID id, String status) {
        return tripRepository.findById(id).map(trip -> {
            trip.setStatus(TripStatus.valueOf(status));
            return tripRepository.save(trip);
        }).orElse(null);
    }
}
