package ru.job4j.generics;

/**
 * Существует такое понятие, связанное с generics, как необработанные типы
 * (в литературе, интернете еще можно встретить такое название как "сырые типы").
 * Обозначаются они также как и generics в скобках <>, в которых проставляются заглавные латинские символы,
 * зарезервированные специально для этих целей символы - полный список можно найти по ссылке:
 *
 * https://docs.oracle.com/javase/tutorial/java/generics/types.html
 *
 * Для того чтобы создать класс общего типа достаточно в его объявлении в <> указать перечень общих типов,
 * которые будут использоваться для реализации класса (типов может быть несколько)
 * @param <K>
 * @param <V>
 */
public class GenericsClass<K, V> {
    private K key;

    private V value;

    public GenericsClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "GenericsClass{"
                + "key=" + key
                + ", value=" + value
                + '}';
    }

    /**
     * Когда мы будем использовать этот класс нам будет необходимо указать точный параметр,
     * который будет использоваться вместо K и V.
     */
    public static void main(String[] args) {
        GenericsClass<String, String> gen = new GenericsClass<>("First key", "First value");
        System.out.println("Вывод в консоль: " + gen);

        GenericsClass<Integer, String> second = new GenericsClass<>(12345, "Second value");
        System.out.println("Вывод в консоль: " + second);
    }
}
