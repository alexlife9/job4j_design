package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Анализ доступности сервера
 *
 * @author Alex_life
 * @version 5.0
 * @since 30.07.2022
 */
public class Analysis {

    /**
     * Метод unavailable() находит диапазоны когда сервер не работал и записывает их в файл
     * Сервер не работал, если status = 400 или 500. Диапазоном считается последовательность статусов 400 и 500
     * Сервер работает, если status = 200 или 300.
     *
     * @param source исходный файл с данными лога
     * @param target файл в который сохраняем результаты анализа в формате "начала периода;конец периода"
     */
    public static void unavailable(String source, String target) {
        /**
         * startLogs - начало записи интервала неработоспособности сервера
         * endLogs - конец записи интервала неработоспособности сервера
         * statusWork - факт работоспособности сервера: да/нет
         *
         * 1.создаем начало и конец записи строчки лога
         * 2.создаем переменную statusWork для флага работоспособности сервера
         * 3.создаем список временных интервалов, в который будут писаться интервалы времени
         * 4.подаем на вход исходный файл
         * 5.идем циклом по всем входящим срокам и проверяем условия пока линия не null
         * 6.если линия содержит (400 или 500) И статус сервера true,
         *   то меняем статус сервера на false, и
         *   делим эту линию по пробелу в строке и сохраняем результат второй ячейки в стартовый интервал
         * 7.если линия содержит (200 или 300) И статус сервера false,
         *   то меняем статус сервера на true, и
         *   делим эту линию по пробелу в строке и сохраняем результат второй ячейки в конечный интервал
         *   и сразу добавляем получившиеся интервалы в timesLogs
         * 8.проходим по ним форичем и записываем отфильтрованные данные в файл target
         */
        String startLogs = null;
        String endLogs = null;
        boolean statusWork = true;
        List<String> timesLogs = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if ((line.contains("400") || line.contains("500")) && statusWork) {
                    statusWork = false;
                    startLogs = line.split(" ")[1];
                }
                if ((line.contains("200") || line.contains("300")) && !statusWork) {
                    statusWork = true;
                    endLogs = line.split(" ")[1];
                    timesLogs.add(startLogs + ";" + endLogs);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
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
