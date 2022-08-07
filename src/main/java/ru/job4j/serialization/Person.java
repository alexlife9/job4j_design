package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getStatuses() {
        return statuses;
    }

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


        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Person person2 = new Person(false, 30,
                new Contact(123456, "+7 (111) 111-11-11"), "Worker", "Married");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person2.isSex());
        jsonObject.put("age", person2.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person2).toString());
    }
}

