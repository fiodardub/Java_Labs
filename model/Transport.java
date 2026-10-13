package model;

import java.time.LocalDate;

public class Transport extends AbstractTransport {

    public Transport() {
        super();
    }

    public Transport(int id, String type, String model, int power, double maxSpeed, LocalDate releaseDate, double price) {
        super(id, type, model, power, maxSpeed, releaseDate, price);
    }

    @Override
    public void displayInfo() {
        System.out.println(this.toString());
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getPower() { return power; }
    public void setPower(int power) { this.power = power; }

    public double getMaxSpeed() { return maxSpeed; }
    public void setMaxSpeed(double maxSpeed) { this.maxSpeed = maxSpeed; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return id + ";" + type + ";" + model + ";" + power + ";" + maxSpeed + ";" + releaseDate + ";" + price;
    }
}
