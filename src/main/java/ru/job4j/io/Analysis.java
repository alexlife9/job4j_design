package ru.job4j.io;

import java.io.*;

/**
 * Анализ доступности сервера
 *
 * @author Alex_life
 * @version 7.0
 * убрал избыточные переменные, сократил код
 * @since 31.07.2022
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
         * statusWork - факт работоспособности сервера: да/нет
         *
         * 1.подаем на вход исходный файл и сразу задаем путь куда будем писать итоги программы
         * 2.создаем переменную statusWork для флага работоспособности сервера
         * 3.идем циклом по всем входящим срокам и проверяем условия пока линия не null
         * 4.если линия содержит (400 или 500) И статус сервера true,
         *   то меняем статус сервера на false, и
         *   делим эту линию по пробелу в строке и записываем результат сразу в итоговый файл
         * 5.если линия содержит (200 или 300) И статус сервера false,
         *   то меняем статус сервера на true, и
         *   делим эту линию по пробелу в строке и записываем результат сразу в итоговый файл
         */

        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter timesLogs = new PrintWriter(target)) {
            boolean statusWork = true;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if ((line.contains("400") || line.contains("500")) && statusWork) {
                    statusWork = false;
                    timesLogs.append(line.split(" ")[1]).append(";");
                }
                if ((line.contains("200") || line.contains("300")) && !statusWork) {
                    statusWork = true;
                    timesLogs.append(line.split(" ")[1]).append(System.lineSeparator());
                }
            }
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
