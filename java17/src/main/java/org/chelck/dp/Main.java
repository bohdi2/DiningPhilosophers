package org.chelck.dp;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Hello Philosophers!");

            int numberOfPhilosophers = 100;
            int numberOfInterations = 1_000;

            List<Thread> threads = new ArrayList<>();
            Table table = new Table(numberOfPhilosophers);
            BlockingTable blockingTable = new BlockingTable(table);

            for (int ii = 0; ii < numberOfPhilosophers; ii++) {
                threads.add(new Thread(new Philosopher(blockingTable, ii, numberOfInterations)));
            }

            threads.forEach(Thread::start);
            for (Thread thread : threads) {
                thread.join();
            }
        }
        catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
