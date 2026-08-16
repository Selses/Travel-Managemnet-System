import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<TravelPackage> packages = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();
        ArrayList<Payment> payments = new ArrayList<>();
        ArrayList<Cancellation> cancellations = new ArrayList<>();

        packages.add(new TravelPackage(101, "Ooty", 3, 5000));
        packages.add(new TravelPackage(102, "Kodaikanal", 2, 4000));
        packages.add(new TravelPackage(103, "Goa", 5, 15000));
        packages.add(new TravelPackage(104, "Kerala", 4, 12000));

        int bookingId = 1;
        int paymentId = 1;

        while (true) {

            System.out.println("\n========== TRAVEL MANAGEMENT SYSTEM ==========");
            System.out.println("1. View Packages");
            System.out.println("2. Book Package");
            System.out.println("3. View Bookings");
            System.out.println("4. Make Payment");
            System.out.println("5. Cancel Booking");
            System.out.println("6. Search Booking");
            System.out.println("7. View Payments");
            System.out.println("8. View Cancellations");
            System.out.println("9. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("\nAvailable Packages");

                    for (TravelPackage p : packages) {
                        p.displayPackage();
                    }

                    break;

                case 2:

                    System.out.print("Enter Customer ID: ");
                    int customerId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Customer Name: ");
                    String customerName = sc.nextLine();

                    System.out.print("Enter Package ID: ");
                    int packageId = sc.nextInt();

                    TravelPackage selectedPackage = null;

                    for (TravelPackage p : packages) {

                        if (p.getPackageId() == packageId) {
                            selectedPackage = p;
                            break;
                        }
                    }

                    if (selectedPackage == null) {
                        System.out.println("Package not found.");
                        break;
                    }

                    Customer customer =
                            new Customer(customerId, customerName);

                    Booking booking =
                            new Booking(
                                    bookingId,
                                    customer,
                                    selectedPackage);

                    bookings.add(booking);

                    System.out.println(
                            "Booking Successful!");
                    System.out.println(
                            "Your Booking ID: " + bookingId);

                    bookingId++;

                    break;

                case 3:

                    if (bookings.isEmpty()) {
                        System.out.println("No bookings available.");
                    } else {

                        System.out.println("\nBooking Details");

                        for (Booking b : bookings) {
                            b.displayBooking();
                        }
                    }

                    break;

                case 4:

                    System.out.print("Enter Booking ID: ");
                    int payBookingId = sc.nextInt();

                    Booking paymentBooking = null;

                    for (Booking b : bookings) {

                        if (b.getBookingId() == payBookingId) {
                            paymentBooking = b;
                            break;
                        }
                    }

                    if (paymentBooking == null) {
                        System.out.println("Booking not found.");
                        break;
                    }

                    if (paymentBooking.getStatus().equals("CANCELLED")) {
                        System.out.println(
                                "Cannot pay for cancelled booking.");
                        break;
                    }

                    if (paymentBooking.getPaymentStatus().equals("PAID")) {
                        System.out.println("Payment already completed.");
                        break;
                    }

                    Payment payment =
                            new Payment(
                                    paymentId,
                                    payBookingId,
                                    paymentBooking
                                            .getTravelPackage()
                                            .getPrice());

                    payments.add(payment);

                    paymentBooking.makePayment();

                    System.out.println("Payment Successful.");
                    System.out.println(
                            "Payment ID: " + paymentId);

                    paymentId++;

                    break;

                case 5:

                    System.out.print("Enter Booking ID: ");
                    int cancelBookingId = sc.nextInt();
                    sc.nextLine();

                    Booking cancelBooking = null;

                    for (Booking b : bookings) {

                        if (b.getBookingId() == cancelBookingId) {
                            cancelBooking = b;
                            break;
                        }
                    }

                    if (cancelBooking == null) {
                        System.out.println("Booking not found.");
                        break;
                    }

                    if (cancelBooking.getStatus().equals("CANCELLED")) {
                        System.out.println(
                                "Booking already cancelled.");
                        break;
                    }

                    System.out.print("Enter cancellation reason: ");
                    String reason = sc.nextLine();

                    cancelBooking.cancelBooking();

                    Cancellation cancellation =
                            new Cancellation(
                                    cancelBookingId,
                                    reason);

                    cancellations.add(cancellation);

                    System.out.println(
                            "Booking cancelled successfully.");

                    break;

                case 6:

                    System.out.print("Enter Booking ID: ");
                    int searchId = sc.nextInt();

                    Booking foundBooking = null;

                    for (Booking b : bookings) {

                        if (b.getBookingId() == searchId) {
                            foundBooking = b;
                            break;
                        }
                    }

                    if (foundBooking == null) {
                        System.out.println("Booking not found.");
                    } else {
                        foundBooking.displayBooking();
                    }

                    break;

                case 7:

                    if (payments.isEmpty()) {
                        System.out.println("No payments available.");
                    } else {

                        System.out.println("\nPayment Details");

                        for (Payment p : payments) {
                            System.out.println("------------------------------");
                            p.displayPayment();
                        }
                    }

                    break;

                case 8:

                    if (cancellations.isEmpty()) {
                        System.out.println(
                                "No cancelled bookings.");
                    } else {

                        System.out.println(
                                "\nCancellation Details");

                        for (Cancellation c : cancellations) {
                            System.out.println(
                                    "------------------------------");
                            c.displayCancellation();
                        }
                    }

                    break;

                case 9:

                    System.out.println("Thank You!");
                    sc.close();
                    return;

                default:

                    System.out.println("Invalid Choice.");
            }
        }
    }
}