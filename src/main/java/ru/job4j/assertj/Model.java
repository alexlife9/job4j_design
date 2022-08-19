package ru.job4j.assertj;

/**
 * assertj Утверждения с примитивными типами
 * Поговорим о структуре тестового метода. Тесты желательно писать в одном стиле.
 * Хорошо зарекомендовал себя шаблон тестов, получивший название AAA - Arrange, Act, Assert.
 * ____На этапе Arrange производится подготовка данных для проверяемого действия -
 * создаются нужные объекты, поля объектов наполняются тестовыми данными.
 * ____Этап Act - это выполнение действия объекта, которое должно будет оцениваться,
 * и сохранение результата этого действия.
 * ____Этап Assert - это проверка соответствие фактического результата действия ожидаемому результату.
 *
 * @author Alex_life
 * @version 1.0
 * @since 20.08.2022
 */
public class Model {
    private final int top;
    private final double num;
    private final String line;
    private boolean condition;

    public Model(int top, double num, String line, boolean condition) {
        this.top = top;
        this.num = num;
        this.line = line;
        this.condition = condition;
    }

    public int getTop() {
        return top;
    }

    public double getNum() {
        return num;
    }

    public String getLine() {
        return line;
    }

    public boolean isCondition() {
        return condition;
    }
}