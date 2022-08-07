package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Преобразование JSON в POJO. JsonObject
 *
 * @author Alex_life
 * @version 2.0
 * @since 07.08.2022
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

    public boolean isRefueled() {
        return refueled;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getColor() {
        return color;
    }

    public String[] getTrunk() {
        return trunk;
    }

    public Driver getDriver() {
        return driver;
    }

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
        String[] trunk = {"Suitcase", "Painting", "Basket", "Cardboard", "smallDog"};
        Driver driver = new Driver("Alex", 40);
        Car car = new Car(true, 2000, "black", trunk, driver);

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson = gson.toJson(car);
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);


        /* JSONObject из json-строки */
        JSONObject jsonDriver = new JSONObject("{\"name\":\"Alex\", \"age\":\"40\"}");

        /* JSONArray из ArrayList */
        List<String> trunkCar = new ArrayList<>();
        trunkCar.add("Suitcase");
        trunkCar.add("Painting");
        trunkCar.add("Basket");
        trunkCar.add("Cardboard");
        trunkCar.add("smallDog");
        JSONArray jsonTrunk = new JSONArray(trunkCar);

        /* JSONObject напрямую методом put */
        final Car car1 = new Car(true, 2000, "black", trunk, driver);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("refueled", car1.refueled);
        jsonObject.put("releaseYear", car1.releaseYear);
        jsonObject.put("color", car1.color);
        jsonObject.put("trunk", jsonTrunk);
        jsonObject.put("driver", jsonDriver);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(car1).toString());

    }
}

/*
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
*/