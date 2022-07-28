package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Анализ доступности сервера
 *
 * @author Alex_life
 * @version 2.0
 * @since 28.07.2022
 */
public class Analizy {

    /**
     * Метод unavailable() находит диапазоны когда сервер не работал и записывает их в файл
     * Сервер не работал, если status = 400 или 500. Диапазоном считается последовательность статусов 400 и 500
     *
     * 1.создаем начало и конец записи строчки лога
     * 2.создаем список временных интервалов, в который будут писаться интервалы времени
     * 3.подаем на вход исходный файл
     * 4.читаем в нем каждую линию и проверяем условия пока линия не пуста
     * 5.если начало лога еще пустое И линия содержит (400 или 500)
     * то делим эту линию по пробелу в строке
     * и первую часть после применения сплита записываем в начало временного интервала
     * 6.если линия содержит (400 или 500) И в старт уже записано значение 200,
     * то делим эту линию по пробелу в строке
     * и первую часть записываем как конец интервала
     * 7.после всех проверок добавляем получившиеся интервалы в timesLogs
     * 8.проходим по ним форичем и записываем отфильтрованные данные в файл target
     *
     * @param source исходный файл с данными лога
     * @param target файл в который сохраняем результаты анализа в формате "начала периода;конец периода"
     */
    public static void unavailable(String source, String target) {
        String startLogs = null;
        String endLogs = null;
        List<String> timesLogs = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (startLogs == null && line.contains("200")) {
                    startLogs = line;
                } else if (line.contains("400") || line.contains("500")) {
                    startLogs = line;
//                    String[] arrayStart = line.split(" ");
//                    timesLogs.add(arrayStart[1]);
                }

                if (startLogs.contains("200") && (line.contains("400") || line.contains("500"))) {
                    endLogs = line;
                } else if ((startLogs.contains("400") || startLogs.contains("500")) && line.contains("200")) {
                    endLogs = line;
//                    String[] arrayEnd = line.split(" ");
//                    timesLogs.add(arrayEnd[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String[] arrayStart = startLogs.split(" ");
            String[] arrayEnd = endLogs.split(" ");
            timesLogs.add(arrayStart[1] + ";" + arrayEnd[1]);
            timesLogs.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("./data/server.log", "./data/logoutMy.csv");
        try (PrintWriter out = new PrintWriter(new FileOutputStream("./data/expected.csv"))) {
            out.println("10:58:01;10:59:01");
            out.println("11:01:02;11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
