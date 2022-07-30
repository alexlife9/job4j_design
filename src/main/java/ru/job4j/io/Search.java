package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Сканирование файловой системы
 *
 * Программа Search ищет файлы только по определенному предикату и
 * выводит содержимое всей директории включая вложенные директории
 *
 * @author Alex_life
 * @version 1.0
 * @since 29.07.2022
 */
public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get("C:\\projects\\job4j_design\\src");
        Predicate<Path> predicate = p -> p.toFile().getName().endsWith(".js");
        List<Path> list = search(start, predicate);
        list.forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}