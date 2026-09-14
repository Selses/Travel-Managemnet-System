import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonFileBookingRepository
        implements BookingRepository {

    private final File file;
    private final ObjectMapper mapper;

    public JsonFileBookingRepository(String fileName) {

        file = new File(fileName);

        mapper = new ObjectMapper();

        // Support LocalDateTime
        mapper.registerModule(
                new JavaTimeModule());

        mapper.disable(
                SerializationFeature
                        .WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public Booking save(Booking booking) {

        List<BookingDTO> bookings =
                readAllDTO();

        // Remove old version if it exists
        bookings.removeIf(
                b -> b.getBookingId()
                        == booking.getBookingId());

        // Add latest version
        bookings.add(
                new BookingDTO(booking));

        writeAllDTO(bookings);

        return booking;
    }

    @Override
    public Optional<Booking> findById(int id) {

        return readAllDTO()
                .stream()
                .filter(b ->
                        b.getBookingId() == id)
                .findFirst()
                .map(this::convertToBooking);
    }

    @Override
    public List<Booking> findAll() {

        return readAllDTO()
                .stream()
                .map(this::convertToBooking)
                .toList();
    }

    @Override
    public void deleteById(int id) {

        List<BookingDTO> bookings =
                readAllDTO();

        bookings.removeIf(
                b -> b.getBookingId() == id);

        writeAllDTO(bookings);
    }

    private List<BookingDTO> readAllDTO() {

        try {

            if (!file.exists()) {
                return new ArrayList<>();
            }

            return mapper.readValue(
                    file,
                    new TypeReference<List<BookingDTO>>() {
                    });

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to read bookings.json",
                    e);
        }
    }

    private void writeAllDTO(
            List<BookingDTO> bookings) {

        try {

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, bookings);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to write bookings.json",
                    e);
        }
    }

    private Booking convertToBooking(
            BookingDTO dto) {

        Customer customer =
                new Customer(
                        dto.getCustomerId(),
                        dto.getCustomerName());

        TravelPackage travelPackage =
                new TravelPackage(
                        dto.getPackageId(),
                        dto.getDestination(),
                        dto.getDays(),
                        dto.getPrice());

        Booking booking =
                new Booking(
                        dto.getBookingId(),
                        customer,
                        travelPackage,
                        dto.getBookingDate());

        // Restore payment status
        if ("PAID".equals(
                dto.getPaymentStatus())) {

            booking.makePayment();
        }

        // Restore cancellation status
        if ("CANCELLED".equals(
                dto.getStatus())) {

            booking.cancelBooking();
        }

        return booking;
    }
}