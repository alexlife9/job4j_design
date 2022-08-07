package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Формат JSON
 *
 * @author Alex_life
 * @version 1.0
 * @since 06.08.2022
 */
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean refueled;

    @XmlAttribute
    private int releaseYear;

    private String color;

    private String[] trunk;

    private Driver driver;

    public Car() {
    }

    public Car(boolean refueled, int releaseYear, String color, String[] trunk, Driver driver) {
        this.refueled = refueled;
        this.releaseYear = releaseYear;
        this.color = color;
        this.trunk = trunk;
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Car{"
                + "refueled=" + refueled
                + ", releaseYear=" + releaseYear
                + ", color='" + color + '\''
                + ", trunk=" + Arrays.toString(trunk)
                + ", driver=" + driver
                + '}';
    }

    public static void main(String[] args) {
        String[] trunk = {"Suitcase",  "Painting", "Basket", "Cardboard",  "smallDog"};
        Driver driver = new Driver("Alex", 40);
        Car car = new Car(true, 2000, "black", trunk, driver);

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson = gson.toJson(car);
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}

@XmlRootElement(name = "driver")
class Driver implements Serializable {
    @XmlAttribute
    String name;
    @XmlAttribute
    int age;

    public Driver() {
    }

    public Driver(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Driver{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }
}
