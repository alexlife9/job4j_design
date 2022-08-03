package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Сканирование файловой системы
 * Метод Files.walkFileTree(Path start, FileVisitor visitor) используется для обхода дерева файлов
 * Метод принимает в параметры Путь откуда начнется обход и логику обхода
 * Логика обхода заключается в классе имплементирующем интерфейс FileVisitor
 * Она содержит 4 метода:
 * 1.preVisitDirectory - срабатывает ПЕРЕД обращением к элементам папки
 * 2.visitFile - срабатывает при обращении к файлу
 * 3.visitFileFailed - срабатывает когда файла по каким-то причинам недоступен
 * 4.postVisitDirectory - срабатывает ПОСЛЕ обращеня ко всем элементам папки
 *
 * Чтобы не переопределять все 4 метода, когда нам допустим нужен только 1 метод, существует класс SimpleFileVisitor,
 * который имплементирует FileVisitor.
 *
 * @author Alex_life
 * @version 2.0
 * добавил документацию
 * @since 03.08.2022
 */
public class PrintFiles implements FileVisitor<Path> {

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file.toAbsolutePath());
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}
