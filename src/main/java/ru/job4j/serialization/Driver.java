package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Alex_life
 * @version 1.0
 * @since 07.08.2022
 */
@XmlRootElement(name = "driver")
public class Driver implements Serializable {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;

    public Driver() {
    }

    public Driver(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Driver{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }
}
