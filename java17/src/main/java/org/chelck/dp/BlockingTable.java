package org.chelck.dp;

import java.util.Objects;
import java.util.Optional;

public class BlockingTable {
    private final Object lock;
    private final Table table;

    public BlockingTable(Table table) {
        this.lock = new Object();
        this.table = Objects.requireNonNull(table);
    }

    public int getLeftFork(int philosopher) {
        synchronized (lock) {
            while (true) {
                Optional<Integer> f = this.table.getLeftFork(philosopher);
                if (f.isPresent()) {
                    return f.get();
                }
                else {
                    try {
                        lock.wait();
                    }
                    catch (InterruptedException e) {

                    }
                }
            }
        }
    }

    public void returnLeftFork(int philosopher) {
        synchronized (lock) {
            this.table.returnLeftFork(philosopher);
            lock.notifyAll();
        }
    }

    public int getRightFork(int philosopher) {
        synchronized (lock) {
            while (true) {
                Optional<Integer> f = this.table.getRightFork(philosopher);
                if (f.isPresent()) {
                    return f.get();
                }
                else {
                    try {
                        lock.wait();
                    }
                    catch (InterruptedException e) {

                    }
                }
            }
        }
    }

    public void returnRightFork(int philosopher) {
        synchronized (lock) {
            this.table.returnRightFork(philosopher);
            lock.notifyAll();
        }
    }


}
