package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Читаем файл конфигурации
 *
 * @author Alex_life
 * @version 4.0
 * @since 27.07.2022
 */
public class Config {

    /**
     * path - файл настроек которые надо прочитать
     * values - карта с прочитанным файлом уже с учетом исключений
     */
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод load считывает все ключи из входящего фалйа path и загружает пару "ключ-значение" в Map values.
     * Пустые строки и комментарии в файле нужно пропускать.
     *
     * 1.считываем файл path, передаем его в буферный поток read
     * 2.с помощью метода readLine записываем из файла всю информацию построчно в переменную line
     * 3.создаем массив стрингов для хранения отдельных частей строчки
     * 4.пока строчка не пустая, и если она не содержит комментарий
     *   делим строчку на 2 части - до и после знака "="
     * 5.проверяем что частей ровно 2 И ни одна из них не является пустой И строка не начинается с =
     * и если это не так - выбрасываем исклчюение
     * 6.иначе, добавляем в Map values пару ключ-значение в виде левой и правой части разрезанной строки
     *
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String line = read.readLine();
            String[] array;
            while (line != null) {
                if (!line.startsWith("#") && !line.isEmpty()) {
                    array = line.split("=", 2);
                    if (array.length != 2 && (array[0] == null || array[1] == null || array[0].equals(line.startsWith("=")))) {
                        throw new IllegalArgumentException("неполная пара");
                    }
                    values.put(array[0], array[1]);
                }
                line = read.readLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (values.get(key) == null) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        String path = "./data/pair_with_pattern_violation_and_empty_line_and_comment.properties";
        Config config = new Config(path);
        config.load();
    }
}
