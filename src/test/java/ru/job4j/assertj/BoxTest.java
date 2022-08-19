package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * assertj Утверждения с примитивными типами
 *
 * @author Alex_life
 * @version 1.0
 * @since 20.08.2022
 */
class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(5, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }


    @Test
    void whenNumberOfVerticesIs0() {
        Box box = new Box(0, 10);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isEqualTo(0);
    }

    @Test
    void whenNumberOfVerticesIs8() {
        Box box = new Box(8, 12);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isEqualTo(8);
    }

    @Test
    void whenNumberOfVerticesIs4() {
        Box box = new Box(4, 6);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isEqualTo(4);
    }

    @Test
    void whenNumberOfVerticesIs5() {
        Box box = new Box(5, 5);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isEqualTo(-1);
    }

    @Test
    void whenObjIsExist() {
        Box box = new Box(0, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue();
    }

    @Test
    void whenObjIsNotExist() {
        Box box = new Box(5, 5);
        boolean isExist = box.isExist();
        assertThat(isExist).isFalse();
    }

    @Test
    void whenGetAreaOfCube() {
        Box box = new Box(8, 12);
        double area = box.getArea();
        assertThat(area)
                .isEqualTo(864)
                .isNotNegative()
                .isGreaterThan(800)
                .isLessThan(900);
    }

    @Test
    void whenGetObjHasNotArea() {
        Box box = new Box(5, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(0.0);
    }

}