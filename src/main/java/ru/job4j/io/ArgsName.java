package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * Именованные аргументы
 *
 * Класс ArgsName принимает массив параметров и разбивает их на пары: ключ, значение.
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.08.2022
 */
public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    /**
     * метод parse принимает массив аргументов и разбивает их на пары "ключ-значение"
     * 1.проверяем что аргументы вообще есть. если нет - выбрасываем ошибку
     * 2.проходим циклом по всему массиву args
     * 3.удаляем у аргументов первое вхождение "-" и сохраняем в elements
     * 4.разбиваем все элементы на пары по знаку "=" и сохраняем получившиеся пары в массив array
     * 5.если в массиве 0 или 1 элемент, то выбрасываем ошибку
     * 6.остальные пары добавляем в хэшмапу
     * @param args
     */
    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Аргументы для запуска не найдены");
        }
        for (String arguments : args) {
            String elements = arguments.replaceFirst("-", "");
            String[] array = elements.split("=", 2);
            if (array.length < 2 || array[0].isEmpty() || array[1].isEmpty()) {
                throw new IllegalArgumentException("Отсутвует ключ или значение");
            }
            values.put(array[0], array[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
