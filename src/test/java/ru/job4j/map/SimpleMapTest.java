//package ru.job4j.map;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import java.util.*;
//import static org.assertj.core.api.Assertions.*;
//
///**
// * Реализация собственной структуры данных - HashMap
// *
// * @author Alex_life
// * @version 1.0
// * @since 16.07.2022
// */
//class SimpleMapTest {
//
//    private final SimpleMap<Integer, String> map = new SimpleMap<>();
//
//    @BeforeEach
//    void setUp() {
//        map.put(1, "1");
//        map.put(2, "2");
//        map.put(3, "3");
//        map.put(4, "4");
//    }
//
//    @Test
//    void whenCheckGet() {
//        assertThat(map.get(1)).isEqualTo("1");
//        assertThat(map).hasSize(4);
//        assertThat(map.get(5)).isNull();
//        assertThat(map).hasSize(4);
//    }
//
//    @Test
//    void whenCheckPut() {
//        assertThat(map.put(0, "0")).isTrue();
//        assertThat(map).hasSize(5);
//        assertThat(map.put(8, "8")).isFalse();
//        assertThat(map).hasSize(5);
//        assertThat(map.put(1, "10")).isFalse();
//        assertThat(map.get(1)).isEqualTo("1");
//        assertThat(map).hasSize(5);
//    }
//
//    @Test
//    void whenCheckRemove() {
//        assertThat(map.remove(2)).isTrue();
//        assertThat(map).hasSize(3);
//        assertThat(map.remove(2)).isFalse();
//        assertThat(map).hasSize(3);
//        assertThat(map.remove(5)).isFalse();
//        assertThat(map).hasSize(3);
//    }
//
//    @Test
//    void whenCheckIterator() {
//        map.remove(2);
//        map.remove(3);
//        Iterator<Integer> it = map.iterator();
//        assertThat(it.hasNext()).isTrue();
//        assertThat(it.next()).isEqualTo(1);
//        assertThat(it.next()).isEqualTo(4);
//        assertThat(it.hasNext()).isFalse();
//        assertThatThrownBy(it::next)
//                .isInstanceOf(NoSuchElementException.class);
//    }
//
//    @Test
//    void whenConcurrentIteratorAdd() {
//        Iterator<Integer> it = map.iterator();
//        map.put(0, "0");
//        assertThatThrownBy(it::hasNext)
//                .isInstanceOf(ConcurrentModificationException.class);
//    }
//
//    @Test
//    void whenConcurrentIteratorRemove() {
//        Iterator<Integer> it = map.iterator();
//        map.remove(1);
//        assertThatThrownBy(it::hasNext)
//                .isInstanceOf(ConcurrentModificationException.class);
//    }
//
//    @Test
//    void whenNotConcurrentIteratorGet() {
//        Iterator<Integer> it = map.iterator();
//        map.get(1);
//        assertThat(it.hasNext()).isTrue();
//    }
//
//    @Test
//    void whenMapExpand() {
//        List<Integer> listKey = new ArrayList<>();
//        map.put(0, "0");
//        assertThat(map.put(15, "15")).isTrue();
//        assertThat(map).hasSize(6);
//        assertThat(map.put(8, "8")).isTrue();
//        assertThat(map.put(16, "16")).isFalse();
//        assertThat(map.get(4)).isEqualTo("4");
//        assertThat(map.get(8)).isEqualTo("8");
//        assertThat(map.get(15)).isEqualTo("15");
//        for (Integer integer : map) {
//            listKey.add(integer);
//        }
//        assertThat(listKey).hasSize(7).contains(0, 1, 2, 3, 4, 8, 15);
//    }
//}



package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleMapTest {

    @Test
    public void whenPutThenGet() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(i, i);
        }
        for (int i = 0; i < 5; i++) {
            assertThat(map.get(i), is(i));
        }
    }

    @Test
    public void whenDontHaveKey() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        assertNull(map.get(3));
    }

    @Test
    public void whenGetNull() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        assertNull(map.get(null));
    }

    @Test
    public void whenKeysAreNotEqual() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(5, 5);
        assertFalse(map.remove(2));
    }

    @Test
    public void whenRemove() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(2, 2);
        assertTrue(map.remove(2));
        assertFalse(map.remove(2));
        assertNull(map.get(2));
    }

    @Test
    public void whenResizeTable() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        for (int i = 0; i < 30; i++) {
            map.put(i, i);
        }
        for (int i = 0; i < 30; i++) {
            assertThat(map.get(i), is(i));
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenHasNextExceptionWithPut() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        Iterator<Integer> it = map.iterator();
        map.put(2, 2);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenHasNextExceptionWithRemove() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        Iterator<Integer> it = map.iterator();
        map.remove(1);
        it.next();
    }
}