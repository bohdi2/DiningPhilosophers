package org.chelck.dp;

import java.util.Objects;
import java.util.Random;

public class Philosopher implements Runnable {
    private final BlockingTable table;
    private final int philosopherId;
    private int n;
    private final Random random;
    private final Timings timings;

    public Philosopher(BlockingTable table, int philosopherId, int n) {
        this.table = Objects.requireNonNull(table);
        this.philosopherId = philosopherId;
        this.n = n;
        this.random = new Random();
        this.timings = new Timings();
    }

    public void run() {
        System.out.println("Philosopher " + philosopherId + " is starting: ");
        try {
            timings.start();
            while (n-->0) {
                think();

                table.getLeftFork(philosopherId);
                table.getRightFork(philosopherId);

                eat();

                table.returnLeftFork(philosopherId);
                table.returnRightFork(philosopherId);
                timings.mark();
            }
        }
        catch (Exception e) {}
        System.out.println("Philosopher " + philosopherId + " is done: " + timings);
    }

    private void think() {
        long delay = random.nextLong(10);
        //System.out.println("Philosopher " + philosopherId + " is thinking for " + totalThinking);

//        try {
//            Thread.sleep(delay);
//        }
//        catch (InterruptedException e) {
//        }
    }

    private void eat() {
        long delay = random.nextLong(4);
        //System.out.println("Philosopher " + philosopherId + " is eating for " + totalEating);
        try {
            Thread.sleep(delay);
        }
        catch (InterruptedException e) {
        }
    }
}
