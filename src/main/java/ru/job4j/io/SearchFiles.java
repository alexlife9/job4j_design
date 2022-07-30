package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

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
public class SearchFiles implements FileVisitor<Path> {
    private final Predicate<Path> condition;
    private final List<Path> paths = new ArrayList<>();

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;

    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        System.out.println(dir);
        return CONTINUE;
    }

    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            paths.add(file);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return null;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return null;
    }

    public List<Path> getPaths() {
        return paths;
    }
}
