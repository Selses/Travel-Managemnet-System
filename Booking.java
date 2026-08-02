public class Booking {

    private Customer customer;
    private TravelPackage travelPackage;

    public Booking(Customer customer, TravelPackage travelPackage) {
        this.customer = customer;
        this.travelPackage = travelPackage;
    }

    public void displayBooking() {

        System.out.println("--------------------------------");
        System.out.println("Customer ID   : " + customer.getCustomerId());
        System.out.println("Customer Name : " + customer.getCustomerName());
        System.out.println("Package ID    : " + travelPackage.getPackageId());
        System.out.println("Destination   : " + travelPackage.getDestination());
        System.out.println("Duration      : " + travelPackage.getDays() + " Days");
        System.out.println("Amount        : Rs." + travelPackage.getPrice());
    }
}
