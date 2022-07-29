package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Читаем файл конфигурации
 *
 * @author Alex_life
 * @version 6.0
 * @since 29.07.2022
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
            while (line != null) {
                if ((!line.startsWith("#")) && !line.isEmpty()) {
                    String[] array = line.split("=", 2);
                    if ((array[0].isEmpty() || array[1].isEmpty())) {
                        throw new IllegalArgumentException("неполная пара");
                    }
                    values.put(array[0], array[1]);
                    System.out.println(values);
                }
                line = read.readLine();
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

class Example {
    public static void main(String[] args) {
        String s = "k=";
        String[] a = s.split("=", 2);
        System.out.println(a.length);
        System.out.println(Objects.isNull(a[0]));
        System.out.println(Objects.isNull(a[1]));
        System.out.println(a[0].isEmpty());
        System.out.println(a[1].isEmpty());
        System.out.println(a[0] + a[1]);
        System.out.println("значение первой ячейки при s = \"k=\" :" + a[0] + ": между двоеточий");
        System.out.println("значение второй ячейки при s = \"k=\" :" + a[1] + ": между двоеточий");
        System.out.println();

        String s1 = "=k";
        String[] a1 = s1.split("=", 2);
        System.out.println(a1.length);
        System.out.println(Objects.isNull(a1[0]));
        System.out.println(Objects.isNull(a1[1]));
        System.out.println(a1[0].isEmpty());
        System.out.println(a1[1].isEmpty());
        System.out.println(a1[0] + a1[1]);
        System.out.println("значение первой ячейки при s = \"=k\" :" + a1[0] + ": между двоеточий");
        System.out.println("значение второй ячейки при s = \"=k\" :" + a1[1] + ": между двоеточий");
    }
}
