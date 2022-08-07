package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Преобразование JSON в POJO. JsonObject
 *
 * @author Alex_life
 * @version 1.0
 * @since 07.08.2022
 */
public class JsonObjectEx {
    public static void main(String[] args) {
        /* JSONObject из json-строки */
        JSONObject jsonContact = new JSONObject("{\"zipCode\":\"123456\", \"phone\":\"+7(111)111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Worker");
        list.add("Married");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Person person = new Person(false, 30,
                new Contact(123456, "+7(111)111-11-11"), "Worker", "Married");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person).toString());
    }
}
