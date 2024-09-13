package giorgiaipsarop.GestioneViaggiAziendali.controllers;

import giorgiaipsarop.GestioneViaggiAziendali.TripStatus;
import giorgiaipsarop.GestioneViaggiAziendali.entities.Trip;
import giorgiaipsarop.GestioneViaggiAziendali.payloads.TripPayload;
import giorgiaipsarop.GestioneViaggiAziendali.services.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = (List<Trip>) tripService.getAllTrips();
        return ResponseEntity.ok(trips);
    }

    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody @Valid TripPayload payload) {

        try {
            Trip trip = new Trip(payload.destination(), payload.date(), TripStatus.valueOf(payload.status()));
            Trip createdTrip = tripService.createTrip(trip);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTrip);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable UUID id) {
        Optional<Trip> trip = tripService.getTripById(id);
        if (trip.isPresent()) {
            return ResponseEntity.ok(trip.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID id, @RequestBody @Valid TripPayload payload) {
        Trip trip = new Trip(payload.destination(), payload.date(), TripStatus.valueOf(payload.status()));
        Trip updatedTrip = tripService.updateTrip(id, trip);
        if (updatedTrip != null) {
            return ResponseEntity.ok(updatedTrip);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable UUID id) {
        Optional<Trip> trip = tripService.getTripById(id);
        if (trip.isPresent()) {
            tripService.deleteTrip(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
