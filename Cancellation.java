public class Cancellation {

    private int bookingId;
    private String reason;
    private String status;

    public Cancellation(int bookingId, String reason) {
        this.bookingId = bookingId;
        this.reason = reason;
        this.status = "CANCELLED";
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public void displayCancellation() {
        System.out.println("Booking ID : " + bookingId);
        System.out.println("Reason     : " + reason);
        System.out.println("Status     : " + status);
    }
}