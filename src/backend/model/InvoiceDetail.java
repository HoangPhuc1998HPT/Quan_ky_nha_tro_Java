package backend.model;

public class InvoiceDetail {
    private double rentPrice;
    private double electricPrice;
    private double waterPrice;
    private double garbagePrice;

    public InvoiceDetail(double rentPrice, double electricPrice, double waterPrice, double garbagePrice) {
        this.rentPrice = rentPrice;
        this.electricPrice = electricPrice;
        this.waterPrice = waterPrice;
        this.garbagePrice = garbagePrice;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public double getElectricPrice() {
        return electricPrice;
    }

    public void setElectricPrice(double electricPrice) {
        this.electricPrice = electricPrice;
    }

    public double getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(double waterPrice) {
        this.waterPrice = waterPrice;
    }

    public double getGarbagePrice() {
        return garbagePrice;
    }

    public void setGarbagePrice(double garbagePrice) {
        this.garbagePrice = garbagePrice;
    }
}
