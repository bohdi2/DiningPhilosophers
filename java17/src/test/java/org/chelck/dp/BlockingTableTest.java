package org.chelck.dp;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class BlockingTableTest {

    @Test
    public void t() throws Exception {
        Table table = new Table(5);
        BlockingTable blockingTable = new BlockingTable(table);

        Thread t = new Thread(() -> {
            blockingTable.getLeftFork(3);
            blockingTable.getLeftFork(3);
        }
        );

        t.start();

        expectToBlock(t, 1, TimeUnit.SECONDS);
    }

    private static void expectToBlock(Thread thread, long waitCount, TimeUnit waitUnits) throws InterruptedException {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < waitUnits.toMillis(waitCount)) {
            System.out.println("Thread State: " + thread.getState());
            if (thread.getState() == Thread.State.WAITING) {
                return;
            }
            Thread.sleep(50); // Don't hog the CPU
        }
        fail("Timed out while waiting for thread to block");
    }

}