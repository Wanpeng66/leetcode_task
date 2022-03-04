package com.wp;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author: wp
 * @Title: task_1117  1117. H2O 生成
 * @Description: https://leetcode-cn.com/problems/building-h2o/
 * @date 2022/3/2 15:48
 */
public class task_1117 {


}
class H2O {

    public final CyclicBarrier barrier = new CyclicBarrier( 3 );
    public final Semaphore h = new Semaphore( 2 );
    public final Semaphore o = new Semaphore( 1 );
    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        releaseHydrogen.run();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        h.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire();
        releaseOxygen.run();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        o.release();
    }
}