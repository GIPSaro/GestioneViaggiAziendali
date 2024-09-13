package giorgiaipsarop.GestioneViaggiAziendali.controllers;

import giorgiaipsarop.GestioneViaggiAziendali.entities.Booking;
import giorgiaipsarop.GestioneViaggiAziendali.entities.Employee;
import giorgiaipsarop.GestioneViaggiAziendali.entities.Trip;
import giorgiaipsarop.GestioneViaggiAziendali.payloads.BookingPayload;
import giorgiaipsarop.GestioneViaggiAziendali.services.BookingService;
import giorgiaipsarop.GestioneViaggiAziendali.services.EmployeeService;
import giorgiaipsarop.GestioneViaggiAziendali.services.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final EmployeeService employeeService;
    private final TripService tripService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingPayload bookingPayload) {
        try {
            UUID employeeId = UUID.fromString(bookingPayload.employeeId());
            UUID tripId = UUID.fromString(bookingPayload.tripId());

            Optional<Employee> employeeOpt = employeeService.getEmployeeById(employeeId);
            Optional<Trip> tripOpt = tripService.getTripById(tripId);

            if (employeeOpt.isEmpty() || tripOpt.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Employee employee = employeeOpt.get();
            Trip trip = tripOpt.get();

            Booking booking = new Booking(
                    bookingPayload.requestDate(),
                    bookingPayload.notes(),
                    bookingPayload.preferences(),
                    trip,
                    employee
            );

            Booking savedBooking = bookingService.createBooking(booking);
            return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable UUID id) {
        return bookingService.getBookingById(id)
                .map(booking -> new ResponseEntity<>(booking, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Iterable<Booking>> getAllBookings() {
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable UUID id, @RequestBody BookingPayload bookingPayload) {
        try {
            UUID employeeId = UUID.fromString(bookingPayload.employeeId());
            UUID tripId = UUID.fromString(bookingPayload.tripId());

            Optional<Employee> employeeOpt = employeeService.getEmployeeById(employeeId);
            Optional<Trip> tripOpt = tripService.getTripById(tripId);

            if (employeeOpt.isEmpty() || tripOpt.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Employee employee = employeeOpt.get();
            Trip trip = tripOpt.get();

            Booking updatedBooking = bookingService.updateBooking(id, bookingPayload, trip, employee);
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable UUID id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
