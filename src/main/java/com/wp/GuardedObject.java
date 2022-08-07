package com.wp;


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @author: wp
 * @Title: GuardedObject
 * @Description: 异步转同步类
 * @date 2022/3/22 14:34
 */
public class GuardedObject<T> {

    static Map<Object,GuardedObject> map = new ConcurrentHashMap<>();

    private T res = null;
    private final Lock lock;
    private final Condition notDone;
    private long timeOut;

    public T getRes() {
        return res;
    }

    public void setRes( T res ) {
        this.res = res;
    }

    public Lock getLock() {
        return lock;
    }

    public Condition getNotDone() {
        return notDone;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut( long timeOut ) {
        this.timeOut = timeOut;
    }



    public GuardedObject() {
        lock = new ReentrantLock();
        notDone = lock.newCondition();
        timeOut = -1;
    }

    public static <K> GuardedObject create( K key, long timeOut){
        GuardedObject object = new GuardedObject();
        object.setTimeOut( timeOut );
        map.putIfAbsent( key,object );
        return map.get( key );
    }

    public static <K,T> void onChanged(K key,T res){
        GuardedObject guardedObject = map.get( key );
        if (Objects.isNull( guardedObject )) {
            return;
        }
        Lock lock = guardedObject.getLock();
        lock.lock();
        guardedObject.setRes( res );
        guardedObject.getNotDone().signalAll();
        map.values().remove( guardedObject );
        lock.unlock();
    }

    public T get( Predicate<T> predicate ){
        boolean isTimeOut = false;
        lock.lock();
        long start = System.currentTimeMillis();
        String name = Thread.currentThread().getName();
        try{
            while (!predicate.test( res )) {
                System.out.println(name+"等待答案...");
                notDone.await(100,TimeUnit.MILLISECONDS);
                long now = System.currentTimeMillis();
                if ( timeOut!=-1 && !predicate.test( res ) && now-start>timeOut ) {
                    isTimeOut = true;
                    break;
                }
            }
            if (isTimeOut) {
                throw new TimeoutException(name+"等待超时");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            map.values().remove( this );
            lock.unlock();
        }
        return res;
    }

    public static void main( String[] args ) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch( 5 );
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>( 4 );
        ExecutorService threadPool = Executors.newFixedThreadPool( 6, Executors.defaultThreadFactory() );
        Runnable questionTask = () -> {
            String name = Thread.currentThread().getName();
            Integer key = new Random().nextInt()+new Random(10).nextInt();
            try {
                queue.put( key );
                System.out.println(name+"提交问题...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GuardedObject guardedObject = GuardedObject.create( key, 2000 );
            Object res = guardedObject.get( Objects :: nonNull );
            System.out.println(name+"线程拿到结果:"+res);

            latch.countDown();
        };
        Runnable answerTask = () -> {
            try {
                Thread.sleep( 3000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (! queue.isEmpty()) {
                Integer poll = null;
                try {
                    poll = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                GuardedObject.onChanged( poll, "key:" + poll + "的结果在此!!!" );
            }
        };
        for(int i=0;i<5;i++){
            threadPool.submit( questionTask );
        }
        threadPool.submit( answerTask );
        threadPool.shutdown();
        latch.await();
        System.out.println("done......");
    }

}
