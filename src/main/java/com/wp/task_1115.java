package com.wp;

import java.util.concurrent.Semaphore;

/**
 * @author: wp
 * @Title: task_1115 1115. 交替打印 FooBar
 * @Description: https://leetcode-cn.com/problems/print-foobar-alternately/
 * @date 2022/2/25 21:28
 */
public class task_1115 {

}
class FooBar {
    private volatile int n;
    Semaphore foo = new Semaphore( 1 );
    Semaphore bar = new Semaphore( 0 );

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo.acquire();
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            bar.acquire();
            printBar.run();
            foo.release();
        }
    }
}
