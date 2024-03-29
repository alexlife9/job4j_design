package ru.job4j.collection;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Используя контейнер на базе связанного списка создать контейнер Stack
 *
 * @author Alex_life
 * @version 1.0
 * @since 07.07.2022
 */

public class SimpleStackTest {

    @Test
    public void whenPushThenPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertThat(stack.pop(), is(1));
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertThat(stack.pop(), is(2));
    }

    @Test
    public void whenPushPushThenPollPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertThat(stack.pop(), is(1));
    }
}
