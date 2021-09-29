package ru.job4j.generics;

import java.util.*;

public class GenericUsage {

    /**
     * Рассмотрим ключевые варианты реализации generics в Java:
     * 1. WildCard.
     * Добавляем универсальный метод, который будет выводить в консоль наш список.
     * Если записать Collection<Object> col, то получим ошибку компиляции, поскольку типы не совместимы.
     * Метод ожидает коллекцию с одним типом, а получает с другим. Collection<Object> не является выходом из проблемы.
     * Для того чтобы решить эту проблему используется WildCard (обозначает <?>).
     * В этом случае ограничений в использовании не будет (т.е. он имеет соответствие с любым типом)
     * @param col список
     */
    public void printRsl(Collection<?> col) {
        for (Iterator<?> it = col.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println(next);
        }
    }

    /**
     * 2. Bounded Wildcard
     * Создаем метод который позволит вывести в консоль все элементы коллекции,
     * которая может содержать объекты Person или объекты класса Programmer.
     * Создаем в методе main() два списка и вызовем этот метод для списка разных объектов.
     * Для второго списка мы получим ошибку из-за несовместимости типов.
     * Такой подход используется, если метод который нужно реализовать использует определенный тип и все его подтипы.
     * Для того чтобы справиться с проблемой используется так называемое "Ограничение сверху".
     * В этом случае вместо <Person> записывается конструкция <? extends Person>
     * @param col элементы коллекции
     */
    public void printInfo(Collection<? extends Person> col) {
        for (Iterator<? extends Person> it = col.iterator(); it.hasNext();) {
            Person next = it.next();
            System.out.println(next);
        }
    }

    /**
     * 3. Lower bounded Wildcard
     * Аналогичным образом ограниченный снизу wildcard ограничивает неизвестный тип
     * определенным типом или супертипом этого типа.
     * Ограниченный снизу wildcard выражается с помощью wildcard символа ("?"), за которым
     * следует ключевое слово super после которого указывается нижняя граница - <? super A>.
     *
     * Создаем метод, который помещает объекты Integer в список и выводит этот список в консоль.
     * При этом наш метод должен быть более гибким и работал не только с типом Integer,
     * но и с его суперклассами (т.е. Number и Object). Чтобы реализовать такой метод,
     * в его объявлении должна фигурировать такая строка - List<? super Integer>.
     *
     * Важно понимать, что запись List<Integer> является более строгой, чем List<? super Integer>,
     * потому что первый соответствует списку только типа Integer,
     * тогда как второй соответствует списку любого типа, который является супертипом Integer.
     * @param list список объектов Integer
     */
    public void addAll(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        for (Object o : list) {
            System.out.println("Текущий элемент: " + o);
        }
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("first");
        list.add("second");
        list.add("third");
        System.out.println("Количество элементов в списке: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            String s = (String) list.get(i);
            System.out.println("Текущий элемент: " + s);
        }

        List<Integer> l = List.of(1, 2, 3, 4, 5);
        new GenericUsage().printRsl(l);

        List<Person> per = List.of(new Person("name", 21, new Date(913716000000L)));
        new GenericUsage().printInfo(per);

        List<Programmer> pro = List.of(new Programmer("name123", 23, new Date(913716000000L)));
        new GenericUsage().printInfo(pro);

        List<? super Integer> list2 = new ArrayList<>();
        new GenericUsage().addAll(list2);
    }
}