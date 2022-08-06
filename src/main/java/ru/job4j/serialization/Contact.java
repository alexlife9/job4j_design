package ru.job4j.serialization;

import java.io.*;

/**
 * Сериализация
 *
 * Сериализация – процесс преобразования объектов в бинарный (т.е. последовательность битов) или текстовый формат.
 * Десериализация – процесс преобразования сериализованных данных в объекты, т.е. операция обратная сериализации.
 *
 * Обычно механизм сериализации/десериализации используется для сохранения состояния программы между запусками,
 * для хранения настроек, для передачи данных между программами локально или по сети.
 *
 * В Java существует стандартный механизм сериализации в бинарный формат – Java serialization,
 * из текстовых форматов наиболее популярны JSON, XML, YAML, BSON (binary JSON).
 *
 * Для сериализации объектов в поток используется метод writeObject,
 * для чтения из потока readObject класса ObjectOutputStream.
 *
 * При сериализации объекта сериализуются все объекты, на которые он ссылается в своих полях,
 * поэтому вложенные объекты тоже должны быть Serializable.
 *
 * Для исключения полей из сериализации используется ключевое слово transient.
 *
 * С помощью интерфейса Externalizable можно реализовать собственный алгоритм сериализации/десериализации,
 * для этого нужно переопределить два обязательных метода — writeExternal() и readExternal().
 *
 * @author Alex_life
 * @version 1.0
 * @since 06.08.2022
 */
public class Contact implements Serializable {
    /**
     * Поле serialVersionUID - уникальный идентификатор версии сериализованного класса,
     * необходим для обеспечения механизмов версионности, т.е. нужен JVM для понимания,
     * что сериализованный объект при десериализации имеет те же члены класса, методы и прочее.
     * Если значения не совпадают, будет выброшено исключение java.io.InvalidClassException.
     * Для наглядности в примере мы задаем значение поля вручную,
     * в реальной разработке лучше использовать штатный механизм Java генерации serialVersionUID или разработать свой.
     */
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream("testObjectOutputStream.txt"))) {
            outputStream.writeObject(contact);
        }

        try (FileInputStream fis = new FileInputStream("testObjectOutputStream.txt");
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}
