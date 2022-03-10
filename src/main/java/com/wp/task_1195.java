package com.wp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author: wp
 * @Title: task_1195 1195. 交替打印字符串
 * @Description: https://leetcode-cn.com/problems/fizz-buzz-multithreaded/
 * @date 2022/3/10 9:56
 */
public class task_1195 {
    public static void main( String[] args ) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz( 10 );
        CountDownLatch latch = new CountDownLatch( 4 );
        Runnable fizz = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizz( new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("fizz");
                        }
                    } );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        };
        Runnable buzz = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.buzz( new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("buzz");
                        }
                    } );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        };
        Runnable fizzbuzz = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizzbuzz( new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("fizzbuzz");
                        }
                    } );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        };
        Runnable number = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.number( new IntConsumer() {
                        @Override
                        public void accept( int value ) {
                            System.out.println(value);
                        }
                    } );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        };
        new Thread( fizz ).start();
        new Thread( buzz ).start();
        new Thread( fizzbuzz ).start();
        new Thread( number ).start();
        latch.await();
        System.out.println("done...");
    }
}
class FizzBuzz {
    Lock lock = new ReentrantLock();
    Condition fizz = lock.newCondition();
    Condition buzz = lock.newCondition();
    Condition fizzbuzz = lock.newCondition();
    Condition num = lock.newCondition();
    private int n;
    private volatile int index;

    public FizzBuzz(int n) {
        this.n = n;
        this.index = 1;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        lock.lock();
        while (index<n) {
            if (index%3==0 && index%5!=0) {
                printFizz.run();
                index++;
                buzz.signalAll();
                fizzbuzz.signalAll();
                num.signalAll();
            }else{
                fizz.await();
            }
        }
        lock.unlock();
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        lock.lock();
        while (index<n) {
            if (index%5==0 && index%3!=0) {
                printBuzz.run();
                index++;
                fizz.signalAll();
                fizzbuzz.signalAll();
                num.signalAll();
            }else{
                buzz.await();
            }
        }
        lock.unlock();
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        lock.lock();
        while (index<n) {
            if (index%3==0 && index%5==0) {
                printFizzBuzz.run();
                index++;
                fizz.signalAll();
                buzz.signalAll();
                num.signalAll();
            }else{
                fizzbuzz.await();
            }
        }
        lock.unlock();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number( IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        while (index<n) {
            if (index%3!=0 && index%5!=0) {
                printNumber.accept( index );
                index++;
                fizz.signalAll();
                buzz.signalAll();
                fizzbuzz.signalAll();
            }else{
                num.await();
            }
        }
        lock.unlock();
    }
}