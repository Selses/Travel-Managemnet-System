import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ==============================
        // Existing collections
        // ==============================

        ArrayList<TravelPackage> packages =
                new ArrayList<>();

        ArrayList<Booking> bookings =
                new ArrayList<>();

        ArrayList<Payment> payments =
                new ArrayList<>();

        ArrayList<Cancellation> cancellations =
                new ArrayList<>();

        // ==============================
        // Week 3 service
        // ==============================

        StatementsService statements =
                new StatementsService();

        // ==============================
        // Week 4 Repository
        // ==============================

        BookingRepository repository =
                new JsonFileBookingRepository(
                        "bookings.json");

        // ==============================
        // Travel Packages
        // ==============================

        packages.add(
                new TravelPackage(
                        101,
                        "Ooty",
                        3,
                        5000));

        packages.add(
                new TravelPackage(
                        102,
                        "Kodaikanal",
                        2,
                        4000));

        packages.add(
                new TravelPackage(
                        103,
                        "Goa",
                        5,
                        15000));

        packages.add(
                new TravelPackage(
                        104,
                        "Kerala",
                        4,
                        12000));

        // ==============================
        // Load saved bookings
        // ==============================

        try {

            List<Booking> savedBookings =
                    repository.findAll();

            bookings.addAll(
                    savedBookings);

            savedBookings.forEach(
                    statements::addBooking);

            System.out.println(
                    savedBookings.size()
                            + " booking(s) loaded from JSON.");

        } catch (Exception e) {

            System.out.println(
                    "Could not load saved bookings.");
        }

        // ==============================
        // Next Booking ID
        // ==============================

        int bookingId =
                repository.findAll()
                        .stream()
                        .mapToInt(
                                Booking::getBookingId)
                        .max()
                        .orElse(0) + 1;

        int paymentId = 1;

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd HH:mm");

        // ==============================
        // Main Menu
        // ==============================

        while (true) {

            System.out.println(
                    "\n========================================");

            System.out.println(
                    "       TRAVEL MANAGEMENT SYSTEM");

            System.out.println(
                    "========================================");

            System.out.println(
                    "1. View Packages");

            System.out.println(
                    "2. Book Package");

            System.out.println(
                    "3. View Bookings");

            System.out.println(
                    "4. Make Payment");

            System.out.println(
                    "5. Cancel Booking");

            System.out.println(
                    "6. Search Booking");

            System.out.println(
                    "7. View Payments");

            System.out.println(
                    "8. View Cancellations");

            System.out.println(
                    "9. Bookings Sorted By ID");

            System.out.println(
                    "10. Bookings Sorted By Price");

            System.out.println(
                    "11. Bookings Between Dates");

            System.out.println(
                    "12. Range Query Demo");

            System.out.println(
                    "13. Exit");

            System.out.println(
                    "========================================");

            System.out.print(
                    "Enter Choice: ");

            int choice;

            try {

                choice = sc.nextInt();

            } catch (Exception e) {

                System.out.println(
                        "Please enter a valid number.");

                sc.nextLine();

                continue;
            }

            try {

                switch (choice) {

                    // =================================
                    // 1. VIEW PACKAGES
                    // =================================

                    case 1:

                        System.out.println(
                                "\n===== AVAILABLE PACKAGES =====");

                        packages.forEach(
                                TravelPackage::displayPackage);

                        break;

                    // =================================
                    // 2. BOOK PACKAGE
                    // =================================

                    case 2:

                        System.out.print(
                                "Enter Customer ID: ");

                        int customerId =
                                sc.nextInt();

                        sc.nextLine();

                        System.out.print(
                                "Enter Customer Name: ");

                        String customerName =
                                sc.nextLine();

                        System.out.print(
                                "Enter Package ID: ");

                        int packageId =
                                sc.nextInt();

                        TravelPackage selectedPackage =
                                packages.stream()
                                        .filter(
                                                p ->
                                                        p.getPackageId()
                                                                == packageId)
                                        .findFirst()
                                        .orElse(null);

                        if (selectedPackage == null) {

                            System.out.println(
                                    "Package not found.");

                            break;
                        }

                        Customer customer =
                                new Customer(
                                        customerId,
                                        customerName);

                        Booking booking =
                                new Booking(
                                        bookingId,
                                        customer,
                                        selectedPackage);

                        // Memory
                        bookings.add(
                                booking);

                        // Week 3
                        statements.addBooking(
                                booking);

                        // Week 4 JSON
                        repository.save(
                                booking);

                        System.out.println(
                                "\nBooking Successful!");

                        System.out.println(
                                "Booking ID: "
                                        + bookingId);

                        System.out.println(
                                "Data saved to bookings.json");

                        bookingId++;

                        break;

                    // =================================
                    // 3. VIEW BOOKINGS
                    // =================================

                    case 3:

                        if (bookings.isEmpty()) {

                            System.out.println(
                                    "No bookings available.");

                        } else {

                            System.out.println(
                                    "\n===== ALL BOOKINGS =====");

                            bookings.forEach(
                                    Booking::displayBooking);
                        }

                        break;

                    // =================================
                    // 4. MAKE PAYMENT
                    // =================================

                    case 4:

                        System.out.print(
                                "Enter Booking ID: ");

                        int payId =
                                sc.nextInt();

                        Booking payBooking =
                                statements.findBooking(
                                        payId);

                        if (payBooking == null) {

                            System.out.println(
                                    "Booking not found.");

                            break;
                        }

                        if (payBooking.getStatus()
                                .equals("CANCELLED")) {

                            System.out.println(
                                    "Cannot pay for cancelled booking.");

                            break;
                        }

                        if (payBooking.getPaymentStatus()
                                .equals("PAID")) {

                            System.out.println(
                                    "Payment already completed.");

                            break;
                        }

                        Payment payment =
                                new Payment(
                                        paymentId,
                                        payId,
                                        payBooking
                                                .getTravelPackage()
                                                .getPrice());

                        payments.add(
                                payment);

                        payBooking.makePayment();

                        // Save updated booking
                        repository.save(
                                payBooking);

                        System.out.println(
                                "Payment Successful.");

                        System.out.println(
                                "Payment ID: "
                                        + paymentId);

                        System.out.println(
                                "Payment status saved.");

                        paymentId++;

                        break;

                    // =================================
                    // 5. CANCEL BOOKING
                    // =================================

                    case 5:

                        System.out.print(
                                "Enter Booking ID: ");

                        int cancelId =
                                sc.nextInt();

                        sc.nextLine();

                        Booking cancelBooking =
                                statements.findBooking(
                                        cancelId);

                        if (cancelBooking == null) {

                            System.out.println(
                                    "Booking not found.");

                            break;
                        }

                        if (cancelBooking.getStatus()
                                .equals("CANCELLED")) {

                            System.out.println(
                                    "Booking already cancelled.");

                            break;
                        }

                        System.out.print(
                                "Enter cancellation reason: ");

                        String reason =
                                sc.nextLine();

                        cancelBooking.cancelBooking();

                        Cancellation cancellation =
                                new Cancellation(
                                        cancelId,
                                        reason);

                        cancellations.add(
                                cancellation);

                        // Save updated status
                        repository.save(
                                cancelBooking);

                        System.out.println(
                                "Booking cancelled successfully.");

                        System.out.println(
                                "Cancellation status saved.");

                        break;

                    // =================================
                    // 6. SEARCH BOOKING
                    // =================================

                    case 6:

                        System.out.print(
                                "Enter Booking ID: ");

                        int searchId =
                                sc.nextInt();

                        Booking found =
                                statements.findBooking(
                                        searchId);

                        if (found == null) {

                            System.out.println(
                                    "Booking not found.");

                        } else {

                            found.displayBooking();
                        }

                        break;

                    // =================================
                    // 7. VIEW PAYMENTS
                    // =================================

                    case 7:

                        if (payments.isEmpty()) {

                            System.out.println(
                                    "No payments available.");

                        } else {

                            System.out.println(
                                    "\n===== PAYMENT DETAILS =====");

                            payments.forEach(
                                    Payment::displayPayment);
                        }

                        break;

                    // =================================
                    // 8. VIEW CANCELLATIONS
                    // =================================

                    case 8:

                        if (cancellations.isEmpty()) {

                            System.out.println(
                                    "No cancellations available.");

                        } else {

                            System.out.println(
                                    "\n===== CANCELLATION DETAILS =====");

                            cancellations.forEach(
                                    Cancellation
                                            ::displayCancellation);
                        }

                        break;

                    // =================================
                    // 9. SORT BY BOOKING ID
                    // =================================

                    case 9:

                        statements
                                .showBookingsSortedById();

                        break;

                    // =================================
                    // 10. SORT BY PRICE
                    // =================================

                    case 10:

                        statements
                                .showBookingsSortedByPrice();

                        break;

                    // =================================
                    // 11. DATE RANGE
                    // =================================

                    case 11:

                        sc.nextLine();

                        System.out.print(
                                "Start date (yyyy-MM-dd HH:mm): ");

                        String startText =
                                sc.nextLine();

                        System.out.print(
                                "End date (yyyy-MM-dd HH:mm): ");

                        String endText =
                                sc.nextLine();

                        LocalDateTime start =
                                LocalDateTime.parse(
                                        startText,
                                        formatter);

                        LocalDateTime end =
                                LocalDateTime.parse(
                                        endText,
                                        formatter);

                        statements
                                .showBookingsBetween(
                                        start,
                                        end);

                        break;

                    // =================================
                    // 12. RANGE QUERY
                    // =================================

                    case 12:

                        statements
                                .showRangeOperations();

                        break;

                    // =================================
                    // 13. EXIT
                    // =================================

                    case 13:

                        System.out.println(
                                "\nThank You!");

                        System.out.println(
                                "All saved data remains in bookings.json.");

                        sc.close();

                        return;

                    default:

                        System.out.println(
                                "Invalid Choice.");
                }

            } catch (Exception e) {

                System.out.println(
                        "Error: "
                                + e.getMessage());
            }
        }
    }
}