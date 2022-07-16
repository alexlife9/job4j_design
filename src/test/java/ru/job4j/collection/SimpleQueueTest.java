package ru.job4j.collection;


import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * Очередь на двух стеках
 *
 * @author Alex_life
 * @version 1.0
 * @since 09.07.2022
 */
public class SimpleQueueTest {

    @Test
    public void whenPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        int rsl = queue.pollFirst();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        int rsl = queue.pollFirst();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPollPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.pollFirst();
        queue.push(2);
        int rsl = queue.pollFirst();
        assertThat(rsl, is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.pollFirst();
    }
    @Test
    public void whenPushPushPollAndPush() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.pollFirst();
        queue.push(3);
        assertThat(queue.pollFirst(), is(2));
    }
}
