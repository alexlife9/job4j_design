package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Поиск дубликатов
 *
 * SimpleFileVisitor для быстрого обхода директории без переопределения всех методов FileVisitor
 * SimpleFileVisitor уже реализует FileVisitor,
 * переопределяя все методы только с указанием на дальнейший обход CONTINUE.
 *
 * Это означает, что унаследовавшись от него мы можем переопределить только нужный нам метод.
 * Например, для выхода всех файлов в консоль мы можем написать такой короткий код: в классе DuplicatesVisitor
 * Запуск обхода в main остается таким же:
 *
 * Класс DuplicatesFinder запускает программу поиска дубликатов
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.08.2022
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path startFind = Paths.get("C:\\Projects\\job4j_design\\DirForFindDuplicate");
        Files.walkFileTree(startFind, new DuplicatesVisitor());
    }
}
