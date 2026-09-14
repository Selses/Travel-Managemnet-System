import java.util.List;
import java.util.Optional;

public interface BookingRepository {

    Booking save(Booking booking);

    Optional<Booking> findById(int id);

    List<Booking> findAll();

    void deleteById(int id);
}