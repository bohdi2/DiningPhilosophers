package org.chelck.dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimingsTest {

     @Test
     public void test() throws Exception {
         Timings t = new Timings();
         t.start();
         Thread.sleep(10);
         t.mark();
         assertTrue(t.toString().contains("total:"));
         assertTrue(t.toString().contains("max:"));
     }

}