package org.chelck.dp;

import java.util.concurrent.TimeUnit;

public class Timings {
    private long start;
    private long total;
    private long max;

    public Timings() {
        this.start = 0;
        this.total = 0;
        this.max = 0;
    }

    public void start() {
        this.start = System.nanoTime();
    }

    public void mark() {
        long now = System.nanoTime();
        long elapsed = now - this.start;
        this.total += elapsed;
        this.max = Math.max(this.max, elapsed);
        this.start = now;
    }

    public String toString() {
        return "total: " + f(this.total) + ", max: " + f(this.max);
    }

    private long f(long n) {
        return TimeUnit.NANOSECONDS.toMillis(n);
    }
}
