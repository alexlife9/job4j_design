package ru.job4j.serialization;

import org.json.JSONPropertyIgnore;

/**
 * Преобразование JSON в POJO. JsonObject
 *
 * JSON-Java (org.json) легковесная функциональная библиотека для работы с JSON,
 * которая дополнительно умеет преобразовывать JSON в XML, HTTP header, Cookies и др.
 * В отличие от Jackson или Gson, JSON-Java преобразует json-строку
 * не в объект пользовательского класса (способ Data bind),
 * а в объекты своей библиотеки JSONObject, JSONArray (способ Tree Model).
 *
 * При преобразовании объектов в json-строку возможно рекурсивное зацикливание,
 * простейший пример, когда объект A содержит ссылку на объект B,
 * а он в свою очередь ссылается на первоначальный объект A.
 * При выполнении будут осуществляться переходы по ссылке и сериализация до возникновения исключения StackOverflowError
 * Чтобы избежать исключения необходимо разорвать цепочку,
 * как правило это делается в момент перехода по ссылке на объект, который уже сериализован.
 * В библиотеке JSON-Java (org.json) для этого существует аннотация @JSONPropertyIgnore
 *
 * @author Alex_life
 * @version 1.0
 * @since 07.08.2022
 */
public class A {
    private B b;

    /**
     * если убрать аннотацию @JSONPropertyIgnore, то возникает рекурсивное зацикливание исключение StackOverflowError
     */
    @JSONPropertyIgnore
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }

}
