public class Booking {

    private int bookingId;
    private Customer customer;
    private TravelPackage travelPackage;
    private String status;
    private String paymentStatus;

    public Booking(int bookingId, Customer customer,
                   TravelPackage travelPackage) {

        this.bookingId = bookingId;
        this.customer = customer;
        this.travelPackage = travelPackage;
        this.status = "CONFIRMED";
        this.paymentStatus = "PENDING";
    }

    public int getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void makePayment() {
        paymentStatus = "PAID";
    }

    public void cancelBooking() {
        status = "CANCELLED";
    }

    public void displayBooking() {

        System.out.println("------------------------------");
        System.out.println("Booking ID    : " + bookingId);
        System.out.println("Customer ID   : " + customer.getCustomerId());
        System.out.println("Customer Name : " + customer.getCustomerName());
        System.out.println("Destination   : " + travelPackage.getDestination());
        System.out.println("Days          : " + travelPackage.getDays());
        System.out.println("Amount        : Rs." + travelPackage.getPrice());
        System.out.println("Payment       : " + paymentStatus);
        System.out.println("Booking Status: " + status);
    }
}