package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * Тестирование IO
 * Интеграционные тесты используют внешние ресурсы: файловую системы, базу данных, сеть.
 *
 * Задача по удалению из файла запрещенных слов.
 * Метод drop принимает два файла и список слов для удаления.
 * Результатом работы этой программы будет новый файл.
 *
 * @author Alex_life
 * @version 1.0
 * @since 27.07.2022
 */
public class Abuse {

    public static void drop(String source, String target, List<String> words) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            in.lines()
                    .flatMap(line -> Stream.of(line.split("\\s+")))
                    .filter(word -> !words.contains(word)).map(word -> word + " ")
                    .forEach(out::print);
        }
    }
}
