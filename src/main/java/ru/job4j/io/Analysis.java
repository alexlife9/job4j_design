package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Анализ доступности сервера
 *
 * @author Alex_life
 * @version 4.0
 * @since 29.07.2022
 */
public class Analysis {

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
        /**
         * startLogs - начало записи интервала неработоспособности сервера
         * endLogs - конец записи интервала неработоспособности сервера
         * previousWork - факт работоспособности сервера: да/нет
         */
        String startLogs = null;
        String endLogs = null;
        boolean previousWork = true;
        List<String> timesLogs = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (line.contains("400") || line.contains("500") && previousWork) {
                    previousWork = false;
                    startLogs = line;
                }
                if (line.contains("200") && previousWork) {
                    previousWork = false;
                }
                if ((line.contains("400") || line.contains("500")) && !previousWork) {
                    line = in.readLine();
                }
                if (line.contains("200") && !previousWork) {
                    previousWork = true;
                    endLogs = line;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            String[] arrayStart = startLogs.split(" ");
            String[] arrayEnd = endLogs.split(" ");
            timesLogs.add(arrayStart[1] + ";" + arrayEnd[1]);
            timesLogs.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("./data/server2.log", "./data/logoutMy2.csv");
        try (PrintWriter out = new PrintWriter(new FileOutputStream("./data/expected2.csv"))) {
            out.println("10:57:01;11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }

        unavailable("./data/server1.log", "./data/logoutMy1.csv");
        try (PrintWriter out = new PrintWriter(new FileOutputStream("./data/expected1.csv"))) {
            out.println("10:57:01;10:59:01");
            out.println("11:01:02;11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
