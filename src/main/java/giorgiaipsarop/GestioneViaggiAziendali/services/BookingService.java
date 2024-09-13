package giorgiaipsarop.GestioneViaggiAziendali.services;


import giorgiaipsarop.GestioneViaggiAziendali.entities.Booking;
import giorgiaipsarop.GestioneViaggiAziendali.entities.Employee;
import giorgiaipsarop.GestioneViaggiAziendali.entities.Trip;
import giorgiaipsarop.GestioneViaggiAziendali.payloads.BookingPayload;
import giorgiaipsarop.GestioneViaggiAziendali.repository.BookingRepository;
import giorgiaipsarop.GestioneViaggiAziendali.repository.EmployeeRepository;
import giorgiaipsarop.GestioneViaggiAziendali.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Booking createBooking(Booking booking) {
        
        boolean exists = bookingRepository.existsByEmployeeIdAndRequestDate(
                booking.getEmployee().getId(), booking.getRequestDate());

        if (exists) {
            throw new IllegalArgumentException("Il dipendente ha già una prenotazione per questo giorno");
        }

        return bookingRepository.save(booking);
    }

    public Optional<Booking> getBookingById(UUID id) {
        return bookingRepository.findById(id);
    }

    public Iterable<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking updateBooking(UUID id, BookingPayload bookingPayload, Trip trip, Employee employee) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setRequestDate(bookingPayload.requestDate());
            booking.setNotes(bookingPayload.notes());
            booking.setPreferences(bookingPayload.preferences());

            if (trip != null) {
                booking.setTrip(trip);
            }
            if (employee != null) {
                booking.setEmployee(employee);
            }

            return bookingRepository.save(booking);
        }).orElse(null);
    }

    public void deleteBooking(UUID id) {
        bookingRepository.deleteById(id);
    }
}
