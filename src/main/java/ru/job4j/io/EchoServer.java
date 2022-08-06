package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Что такое Socket
 *
 * Вызов конструктора ServerSocket создает серверный сокет, привязанный к указанному порту.
 * Чтобы клиент мог узнать, где находится сервер ему нужен адрес и порт, 9000 - это порт.
 * По умолчанию адрес будет localhost.
 * Вызов метода accept() заставляет программу ждать подключений по указанному порту,
 * работа программы продолжится только после подключения клиента.
 * После успешного подключения метод возвращает объект Socket, который используется для взаимодействия с клиентом.
 * Сервер работает, пока его принудительно не закроют !server.isClosed()
 * С помощью объекта Socket программа может получить входной поток и может отправить данные в выходной поток.
 * Метод ассеpt принимает один запрос от клиента.Чтобы отправить второй, программа должна снова получить объект socket.
 *  В ответ мы записываем строчку.
 *  И читаем весь входной поток.
 *  Если клиент отправляет запрос http:/localhost:9000/?msg=Bye - завершаем работу сервера.
 *  После чтения отправляем ответ окончательно.
 *
 * @author Alex_life
 * @version 2.0
 * @since 06.08.2022
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.contains("Hello")) {
                            out.write("Hello".getBytes(StandardCharsets.UTF_8));
                            break;
                        } else if (str.contains("Exit")) {
                            server.close();
                        } else if (!str.contains("Hello") || !str.contains("Exit")) {
                            out.write("What".getBytes(StandardCharsets.UTF_8));
                            break;
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}
