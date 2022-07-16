package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * ListIterator
 * @author Alex_life
 * @version 1.0
 * @since 10.07.2022
 */
public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);

        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 10, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);

        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterLastWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 10, 3);
    }

    @Test
    public void whenRemoveIfNegative() {
        List<Integer> input = new ArrayList<>(Arrays.asList(-3, -2, -1, 0, 1, 2, 3));
        ListUtils.removeIf(input, x -> x < 0);

        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfPositive() {
        List<Integer> input = new ArrayList<>(Arrays.asList(-3, -2, -1, 0, 1, 2, 3));
        ListUtils.removeIf(input, x -> x >= 0);

        assertThat(input, is(Arrays.asList(-3, -2, -1)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(-3, -2, -1, 0, 1, 2, 3));
        ListUtils.replaceIf(input, x -> x > 0, 999);

        assertThat(input, is(Arrays.asList(-3, -2, -1, 0, 999, 999, 999)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(-3, -2, -1, 0, 1, 2, 3));
        List<Integer> removes = new ArrayList<>(Arrays.asList(-3, 0, 3));
        ListUtils.removeAll(input, removes);

        assertThat(input, is(Arrays.asList(-2, -1, 1, 2)));
    }

}
