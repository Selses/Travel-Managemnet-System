import java.time.LocalDateTime;

public class BookingDTO {

    private int bookingId;

    private int customerId;
    private String customerName;

    private int packageId;
    private String destination;
    private int days;
    private double price;

    private String status;
    private String paymentStatus;

    private LocalDateTime bookingDate;

    // Required by Jackson
    public BookingDTO() {
    }

    // Convert Booking -> DTO
    public BookingDTO(Booking booking) {

        this.bookingId =
                booking.getBookingId();

        this.customerId =
                booking.getCustomer().getCustomerId();

        this.customerName =
                booking.getCustomer().getCustomerName();

        this.packageId =
                booking.getTravelPackage().getPackageId();

        this.destination =
                booking.getTravelPackage().getDestination();

        this.days =
                booking.getTravelPackage().getDays();

        this.price =
                booking.getTravelPackage().getPrice();

        this.status =
                booking.getStatus();

        this.paymentStatus =
                booking.getPaymentStatus();

        this.bookingDate =
                booking.getBookingDate();
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}