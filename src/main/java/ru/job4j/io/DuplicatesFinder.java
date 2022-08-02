package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Поиск дубликатов
 *
 * SimpleFileVisitor уже реализует FileVisitor,
 * переопределяя все методы только с указанием на дальнейший обход CONTINUE.
 *
 * Это означает, что унаследовавшись от него мы можем переопределить только нужный нам метод.
 * Например, для выхода всех файлов в консоль мы можем написать такой короткий код: в классе DuplicatesVisitor
 * Запуск обхода в main остается таким же:
 *
 * @author Alex_life
 * @version 1.0
 * @since 31.07.2022
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
    }
}
