package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * JAXB. Преобразование XML в POJO
 *
 * Для того чтобы сериализовать/десериализовать объекты в/c XML, нужно добавить аннотации JAXB,
 * которая даст библиотеке информацию о том как парсить объект.
 *
 * 1) Как вы уже знаете xml обязательно должен иметь корневой тег, в котором все и будет располагаться.
 * Для его обозначения служит @XmlRootElement.
 * Эту аннотацию нужно ставить над сущностью, которая будет корневой в нашем случае это Person
 *
 * 2) Над вложенными сущностями нам нужно поставить просто @XmlElement
 *
 * 3) Для того чтобы поле считалось атрибутом нужно поставить  @XmlAttribute, по умолчанию поле парсится как тег
 *
 * 4) Мы можем указать также как мы хотим читать/писать объект.
 * По геттерам/сеттерам или напрямую по полям (используется рефлексия). Мы будем использовать доступ по полям.
 * Для этих целей служит аннотация @XmlAccessorType
 *
 * @author Alex_life
 * @version 1.0
 * @since 07.08.2022
 */
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlAttribute
    private boolean sex;

    @XmlAttribute
    private int age;
    private Contact contact;

    @XmlElementWrapper(name = "strausesss")
    @XmlElement(name = "what")
    private String[] statuses;

    public Person() {
    }

    public Person(boolean sex, int age, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }


    public static void main(String[] args) throws JAXBException {

        final Person person = new Person(false, 30,
                new Contact(123456, "+7 (111) 111-11-11"), "Worker", "Married");

        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

