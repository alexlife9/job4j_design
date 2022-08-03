package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Валидация параметров запуска
 *
 * Программа Search ищет файлы только по определенному предикату и
 * выводит содержимое всей директории включая вложенные директории
 *
 * @author Alex_life
 * @version 3.0
 * добавил Валидацию параметров запуска
 * @since 03.08.2022
 */
public class Search {
    public static void main(String[] args) throws IOException {
        /**
         * запускаем обход с указанной папки
         * назначаем предикат который ищет только файлы имена которых заканчиваются на .js
         * результаты поиска складываем в лист
         * и выводим этот лист в консоль
         */
        String validateStart = args[0];
        if (validateStart == null) {
            throw new IllegalArgumentException("Стартовая папка не указана");
        }
        String extensionFile = args[1];
        if (extensionFile == null) {
            throw new IllegalArgumentException("Не указано расширение файла");
        }
        if (args.length == 0) {
            throw new IllegalArgumentException("Аргументы для запуска не найдены");
        }
        Path start = Paths.get(validateStart);
        Predicate<Path> predicate = p -> p.toFile().getName().endsWith(extensionFile);
        List<Path> list = search(start, predicate);
        list.forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        /**
         * метод search стартует с указанной папки и делает обход директории по заданной логике
         * создаем новый объект класса SearchFiles, в параметры которого передаем условия condition
         * запускаем обход дерева с нашими параметрами
         * возвращаем результат поиска
         */
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}