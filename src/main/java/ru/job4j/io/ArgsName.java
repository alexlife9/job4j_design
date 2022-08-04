package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * Именованные аргументы
 *
 * Класс ArgsName принимает массив параметров и разбивает их на пары: ключ, значение.
 * -Xmx=512 -encoding=UTF-8
 *
 * @author Alex_life
 * @version 8.0
 * в метод argsCheck добавил проверку присутствия значения
 * @since 05.08.2022
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
     * 1.проходим циклом по всему массиву args
     * 2.удаляем (если оно есть) у первого аргумента первое вхождение "-", разбиваем элемент на пары по знаку "=".
     * 3.запускаем проверку текущего аргумента на корректность переданных параметров
     * 4.сохраняем получившуюся пару "ключ-значение" в хэшмапу в виде готового бакета
     * 8.после этого возвращаемся в цикл и проверяем второй аргумент и повторяем это пока они не кончатся
     * @param args входящие аргументы
     */
    private void parse(String[] args) {
        for (String arguments : args) {
            String[] array = arguments.replaceFirst("-", "").split("=", 2);
            argsCheck(arguments);
            values.put(array[0], array[1]);
        }
    }

    /**
     * метод argsCheck проверяет корректность переданных параметров в аргументы
     * @param argValid проверяемые аргументы
     */
    private void argsCheck(String argValid) {
        if (!argValid.startsWith("-")) {
            throw new IllegalArgumentException("Неверное значение ключа");
        }
        if (argValid.startsWith("-=")) {
            throw new IllegalArgumentException("Отсутствует ключ");
        }
        if (!argValid.contains("=")) {
            throw new IllegalArgumentException("Отсутствует аргумент");
        }
        if (argValid.indexOf("=") == argValid.length() - 1) {
            throw new IllegalArgumentException("Отсутствует значение");
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

    /**
     * метод main принимает входящий строковый массив аргументов
     * аргументы правильно записываются строго по шаблону: -ключ=значение
     * между аргументами ОДИН пробел
     * если нет дефиса или знака равно, то это не аргумент
     * если нет ключа между дефисом и равно, то это ошибочный аргумент
     * @param args аргументы запуска
     */
    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
