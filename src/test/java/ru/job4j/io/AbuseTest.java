package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Тестирование IO
 * Интеграционные тесты используют внешние ресурсы: файловую системы, базу данных, сеть.
 * @author Alex_life
 * @version 1.0
 * @since 27.07.2022
 */
class AbuseTest {

    /**
     * создаем файл source и заполняем его содержимое, считывая файл source.txt
     *
     * @param tempDir
     * @throws IOException
     */
    @Test
    void drop(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("hello foolish dude");
            out.println("java job4j php");
        }
        /**
         * создаем файл target, куда будет выведен результат
         */
        File target  = tempDir.resolve("target.txt").toFile();
        Abuse.drop(source.getAbsolutePath(), target.getAbsolutePath(), List.of("foolish", "php"));

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("hello dude java job4j ").isEqualTo(rsl.toString());
    }
}
