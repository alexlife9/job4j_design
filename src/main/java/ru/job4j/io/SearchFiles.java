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
 *
 *
 * @author Alex_life
 * @version 2.0
 * добавил документацию
 * @since 03.08.2022
 */
public class SearchFiles implements FileVisitor<Path> {
    /**
     * condition - условие поиска (задаем в мейне в классе Search)
     * paths - найденные файлы, удовлетворяющие нашему условию
     */
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

    /**
     *
     * @param file - текущий файл
     * @param attrs - атрибуты файла по умолчанию
     * @return возвращаем добавление файла с выполненным условием в List paths
     * @throws IOException выбрасываем эксепшн если файл не найден
     */
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            paths.add(file);
        }
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

    /**
     * геттер для доступа к paths
     * @return обработанные файлы удовлетворяющие условию обхода
     */
    public List<Path> getPaths() {
        return paths;
    }
}
