import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.TreeMap;

public class StatementsService {

    private TreeMap<Integer, Booking> bookingsById =
            new TreeMap<>();

    private TreeMap<LocalDateTime, Booking> bookingsByDate =
            new TreeMap<>();

    // Add booking to TreeMaps
    public void addBooking(Booking booking) {

        bookingsById.put(
                booking.getBookingId(),
                booking
        );

        LocalDateTime time =
                booking.getBookingDate();

        // Avoid same timestamp overwrite
        while (bookingsByDate.containsKey(time)) {
            time = time.plusNanos(1);
        }

        bookingsByDate.put(
                time,
                booking
        );
    }

    // ==========================================
    // WEEK 3 - SORT BY BOOKING ID
    // ==========================================

    public void showBookingsSortedById() {

        System.out.println(
                "\n===== BOOKINGS SORTED BY ID ====="
        );

        if (bookingsById.isEmpty()) {
            System.out.println(
                    "No bookings available."
            );
            return;
        }

        for (Booking booking : bookingsById.values()) {
            booking.displayBooking();
        }
    }

    // ==========================================
    // WEEK 3 - SORT BY PRICE
    // ==========================================

    public void showBookingsSortedByPrice() {

        /*
         * Explicit (Booking b) is important.
         * This fixes the Object -> Booking compilation error.
         */
        Comparator<Booking> comparator =
                Comparator
                        .comparingDouble(
                                (Booking b) ->
                                        b.getTravelPackage().getPrice()
                        )
                        .thenComparingInt(
                                (Booking b) ->
                                        b.getBookingId()
                        );

        TreeMap<Booking, Booking> sortedBookings =
                new TreeMap<>(comparator);

        for (Booking booking : bookingsById.values()) {
            sortedBookings.put(
                    booking,
                    booking
            );
        }

        System.out.println(
                "\n===== BOOKINGS SORTED BY PRICE ====="
        );

        if (sortedBookings.isEmpty()) {
            System.out.println(
                    "No bookings available."
            );
            return;
        }

        for (Booking booking : sortedBookings.values()) {
            booking.displayBooking();
        }
    }

    // ==========================================
    // WEEK 3 - DATE RANGE USING subMap()
    // ==========================================

    public void showBookingsBetween(
            LocalDateTime start,
            LocalDateTime end) {

        NavigableMap<LocalDateTime, Booking> range =
                bookingsByDate.subMap(
                        start,
                        true,
                        end,
                        true
                );

        System.out.println(
                "\n===== BOOKINGS BETWEEN DATES ====="
        );

        if (range.isEmpty()) {
            System.out.println(
                    "No bookings found in this range."
            );
            return;
        }

        for (Booking booking : range.values()) {
            booking.displayBooking();
        }
    }

    // ==========================================
    // WEEK 3 - TreeMap RANGE OPERATIONS
    // ==========================================

    public void showRangeOperations() {

        if (bookingsByDate.isEmpty()) {
            System.out.println(
                    "No bookings available."
            );
            return;
        }

        LocalDateTime first =
                bookingsByDate.firstKey();

        LocalDateTime last =
                bookingsByDate.lastKey();

        System.out.println(
                "\n===== TREEMAP RANGE OPERATIONS ====="
        );

        System.out.println(
                "First Key   : " + first
        );

        System.out.println(
                "Last Key    : " + last
        );

        System.out.println(
                "Ceiling Key : "
                        + bookingsByDate.ceilingKey(first)
        );

        System.out.println(
                "Floor Key   : "
                        + bookingsByDate.floorKey(last)
        );

        System.out.println(
                "HeadMap Count : "
                        + bookingsByDate
                        .headMap(last, false)
                        .size()
        );

        System.out.println(
                "TailMap Count : "
                        + bookingsByDate
                        .tailMap(first, true)
                        .size()
        );
    }

    // ==========================================
    // FIND BOOKING
    // ==========================================

    public Booking findBooking(int id) {

        return bookingsById.get(id);
    }

    // ==========================================
    // REMOVE BOOKING
    // ==========================================

    public void removeBooking(int id) {

        bookingsById.remove(id);

        bookingsByDate
                .values()
                .removeIf(
                        booking ->
                                booking.getBookingId() == id
                );
    }
}