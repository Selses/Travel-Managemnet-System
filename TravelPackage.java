public class TravelPackage {

    private int packageId;
    private String destination;
    private int days;
    private double price;

    public TravelPackage(int packageId, String destination, int days, double price) {
        this.packageId = packageId;
        this.destination = destination;
        this.days = days;
        this.price = price;
    }

    public int getPackageId() {
        return packageId;
    }

    public String getDestination() {
        return destination;
    }

    public int getDays() {
        return days;
    }

    public double getPrice() {
        return price;
    }

    public void displayPackage() {
        System.out.println("--------------------------------");
        System.out.println("Package ID : " + packageId);
        System.out.println("Destination: " + destination);
        System.out.println("Days       : " + days);
        System.out.println("Price      : Rs." + price);
    }
}