package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * Именованные аргументы
 *
 * Класс ArgsName принимает массив параметров и разбивает их на пары: ключ, значение.
 *
 * @author Alex_life
 * @version 4.0
 * вынес проверку аргументов в отдельный метод
 * @since 04.08.2022
 */
public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Ключ не найден");
        }
        return values.get(key);
    }

    /**
     * метод parse принимает массив аргументов и разбивает их на пары "ключ-значение"
     * 1.проверяем что аргументы вообще есть. если нет - выбрасываем ошибку
     * 2.проходим циклом по всему массиву args
     * 3.удаляем у первого аргумента первое вхождение "-" и сохраняем в elements
     * 4.разбиваем элемент на пары по знаку "=" и сохраняем получившуюся пару "ключ-значение" в массив array
     * 5.если в массиве 0 или 1 элемент ИЛИ первая или вторая ячейка пустая, то выбрасываем ошибку
     * 6.остальные пары добавляем в хэшмапу в виде готового бакета
     * 7.после этого проверяем второй аргумент и повторяем пока они не кончатся
     * @param args входящие аргументы
     */
    private void parse(String[] args) {
        argsCheck(args);
        for (String arguments : args) {
            String elements = arguments.replaceFirst("-", "");
            String[] array = elements.split("=", 2);
            values.put(array[0], array[1]);
        }
    }

    private void argsCheck(String[] argValid) {
        if (argValid.length != 2 || argValid[0].isEmpty() || argValid[1].isEmpty() || !argValid[0].startsWith("-")) {
            throw new IllegalArgumentException("Отсутствует ключ или значение");
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Аргументы для запуска не найдены");
        }
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
