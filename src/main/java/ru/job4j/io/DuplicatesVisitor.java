package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Поиск дубликатов
 *
 * SimpleFileVisitor для быстрого обхода директории без переопределения всех методов FileVisitor
 *
 * SimpleFileVisitor уже реализует FileVisitor,
 * переопределяя все методы только с указанием на дальнейший обход CONTINUE.
 *
 * Это означает, что унаследовавшись от него мы можем переопределить только нужный нам метод.
 * Например, для выхода всех файлов в консоль мы можем написать такой короткий код: в классе DuplicatesVisitor
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.08.2022
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    /**
     * в методе visitFile реализована логика поиска дубликатов
     *
     * @param file - текущий файл
     * @param attrs - атрибуты файла (оставляем все по-умолчанию)
     * @return возвращаем результат работы метода - то есть все найденные дубликаты
     * @throws IOException выбрасываем эксепшн если файл не найден
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        List<FileProperty> duplicateFiles = new ArrayList<>();
        List<FileProperty> allFiles = new ArrayList<>();
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        duplicateFiles.add(fileProperty);
        allFiles.add(fileProperty);
        if (duplicateFiles.size() != allFiles.size()) {
            System.out.println("найден дубль : " + "и тут надо понять как вывести этот дубль :)");
        }
        return super.visitFile(file, attrs);
    }
}
