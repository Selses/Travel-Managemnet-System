public class Payment {

    private int paymentId;
    private int bookingId;
    private double amount;
    private String status;

    public Payment(int paymentId, int bookingId, double amount) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.status = "PAID";
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void displayPayment() {
        System.out.println("Payment ID : " + paymentId);
        System.out.println("Booking ID : " + bookingId);
        System.out.println("Amount     : Rs." + amount);
        System.out.println("Status     : " + status);
    }
}