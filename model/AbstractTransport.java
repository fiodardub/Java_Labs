package model;

import java.time.LocalDate;

public abstract class AbstractTransport {
    protected int id;
    protected String type;
    protected String model;
    protected int power;
    protected double maxSpeed;
    protected LocalDate releaseDate;
    protected double price;

    public AbstractTransport() {}

    public AbstractTransport(int id, String type, String model, int power, double maxSpeed, LocalDate releaseDate, double price) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.power = power;
        this.maxSpeed = maxSpeed;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public abstract void displayInfo();
}
