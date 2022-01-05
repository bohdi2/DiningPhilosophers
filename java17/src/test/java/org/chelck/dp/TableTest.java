package org.chelck.dp;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {
    @Test
    public void test_getLeftFork() {
        Table table = new Table(5);

        assertEquals(Optional.of(0), table.getLeftFork(0));
        assertEquals(Optional.of(1), table.getLeftFork(1));
        assertEquals(Optional.of(2), table.getLeftFork(2));
        assertEquals(Optional.of(3), table.getLeftFork(3));
        assertEquals(Optional.empty(), table.getLeftFork(4));
    }

    @Test
    public void test_getRightFork() {
        Table table = new Table(5);

        assertEquals(Optional.of(1), table.getRightFork(0));
        assertEquals(Optional.of(2), table.getRightFork(1));
        assertEquals(Optional.of(3), table.getRightFork(2));
        assertEquals(Optional.of(4), table.getRightFork(3));
        assertEquals(Optional.empty(), table.getRightFork(4));
    }

    @Test
    public void getSameLeftFork() {
        Table table = new Table(5);
        table.getLeftFork(3);

        assertEquals(Optional.empty(), table.getLeftFork(3));
    }

    @Test
    public void getSameRightFork() {
        Table table = new Table(5);
        table.getRightFork(3);

        assertEquals(Optional.empty(), table.getRightFork(3));
    }

    @Test
    public void returnLeftFork() {
        Table table = new Table(5);
        table.getLeftFork(3);
        table.returnLeftFork(3);

        assertEquals(Optional.of(3), table.getLeftFork(3));
    }

    @Test
    public void returnRightFork() {
        Table table = new Table(5);
        table.getRightFork(3);
        table.returnRightFork(3);

        assertEquals(Optional.of(4), table.getRightFork(3));
    }

    @Test
    public void twoPhilosophers() {
        Table table = new Table(2);
        assertEquals(Optional.of(0), table.getLeftFork(0));
        assertEquals(Optional.of(1), table.getRightFork(0));
        assertEquals(Optional.empty(), table.getLeftFork(1));
        assertEquals(Optional.empty(), table.getRightFork(1));
    }

    @Test
    public void twoPhilosophers2() {
        Table table = new Table(2);
        assertEquals(Optional.of(0), table.getLeftFork(0));
        assertEquals(Optional.empty(), table.getLeftFork(1));
        assertEquals(Optional.empty(), table.getRightFork(1));
    }

}