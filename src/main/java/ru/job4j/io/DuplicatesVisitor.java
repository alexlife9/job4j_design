package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Поиск дубликатов
 *
 * SimpleFileVisitor уже реализует FileVisitor,
 * переопределяя все методы только с указанием на дальнейший обход CONTINUE.
 *
 * Это означает, что унаследовавшись от него мы можем переопределить только нужный нам метод.
 * Например, для выхода всех файлов в консоль мы можем написать такой короткий код: в классе DuplicatesVisitor
 *
 * @author Alex_life
 * @version 1.0
 * @since 31.07.2022
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }
}
