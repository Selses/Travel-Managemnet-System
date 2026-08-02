import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<TravelPackage> packages = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();

        packages.add(new TravelPackage(101, "Ooty", 3, 5000));
        packages.add(new TravelPackage(102, "Kodaikanal", 2, 4000));
        packages.add(new TravelPackage(103, "Goa", 5, 15000));
        packages.add(new TravelPackage(104, "Kerala", 4, 12000));

        while (true) {

            System.out.println("\n========== TRAVEL MANAGEMENT SYSTEM ==========");
            System.out.println("1. View Packages");
            System.out.println("2. Book Package");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
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
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();

                    Customer customer = new Customer(id, name);

                    System.out.print("Enter Package ID: ");
                    int pid = sc.nextInt();

                    boolean found = false;

                    for (TravelPackage p : packages) {

                        if (p.getPackageId() == pid) {

                            Booking booking = new Booking(customer, p);
                            bookings.add(booking);

                            System.out.println("\nBooking Successful!");

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Invalid Package ID.");
                    }

                    break;

                case 3:

                    if (bookings.isEmpty()) {
                        System.out.println("No Bookings Available.");
                    } else {

                        System.out.println("\nBooking Details");

                        for (Booking b : bookings) {
                            b.displayBooking();
                        }
                    }

                    break;

                case 4:
                    System.out.println("Thank You!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}