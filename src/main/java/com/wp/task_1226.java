package com.wp;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wp
 * @Title: task_1226 1226. 哲学家进餐
 * @Description: https://leetcode-cn.com/problems/the-dining-philosophers/
 * @date 2022/3/25 11:21
 */
public class task_1226 {

}
class DiningPhilosophers {
    ReentrantLock[][] forkArray = new ReentrantLock[5][2];
    ReentrantLock l1 = new ReentrantLock( true );
    ReentrantLock l2 = new ReentrantLock( true );
    ReentrantLock l3 = new ReentrantLock( true );
    ReentrantLock l4 = new ReentrantLock( true );
    ReentrantLock l5 = new ReentrantLock( true );



    public DiningPhilosophers() {
        forkArray[0][1] = l1;
        forkArray[0][0] = l5;
        forkArray[1][0] = l1;
        forkArray[1][1] = l2;
        forkArray[2][0] = l2;
        forkArray[2][1] = l3;
        forkArray[3][0] = l3;
        forkArray[3][1] = l4;
        forkArray[4][0] = l4;
        forkArray[4][1] = l5;
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        //编号为philosopher的哲学家的左右叉子
        ReentrantLock[] forks = forkArray[philosopher];
        ReentrantLock leftFork = forks[0];
        ReentrantLock rightFork = forks[1];
        while (!leftFork.tryLock( new Random().nextInt( 100 ) + 1, TimeUnit.MILLISECONDS )
                || !rightFork.tryLock( new Random().nextInt( 100 ) + 1, TimeUnit.MILLISECONDS )) {
            if (leftFork.isHeldByCurrentThread()) {
                leftFork.unlock();
            }
        }
        //开始吃
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        rightFork.unlock();
        leftFork.unlock();
    }

}