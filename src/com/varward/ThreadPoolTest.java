package com.varward;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * 这个我们是有用来创建线程池的
 * jdk1.5后 有的java.util.concurrent.Executors:线程池的工厂类 用来生产线程池
 * Executors 类中的静态方法：
 *
 *
 * 线程池的使用步骤
 * 1.调用线程池的工厂类生产线程池
 * 2.创建一个类 用来实现runnable接口  实现run方法
 * 3.执行ExecutorService里面的submit方法  传递线程的实现类 开启线程
 * 4.归还线程到线程池
 *
 */
public class ThreadPoolTest {
    public static void main(String[] args) {

        // 1.调用线程池的工厂类生产线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //执行ExecutorService里面的submit方法  传递线程的实现类 开启线程
        //线程池开启后除非你销毁 不然就会一直存在  一直执行 我们这里创建了两个线程池对象 但是我们 有三个线程等待着调用 所以没轮到的
        //那个就要等到前面两个的任意一个用完了归还回线程池才能用 所以就是这个结果 2,1,2
        //Thread[pool-1-thread-2,5,main]这是我创建的一个线程池
        //Thread[pool-1-thread-1,5,main]这是我创建的一个线程池
        //Thread[pool-1-thread-2,5,main]这是我创建的一个线程池
        Future<?> submit = executorService.submit(new RunnableImpl());
        Future<?> submit1 = executorService.submit(new RunnableImpl());
        Future<?> submit2 = executorService.submit(new RunnableImpl());

        //关闭线程池
        executorService.shutdown();
        //再执行就会抛异常了  因为已经销毁了线程池了
        executorService.submit(new RunnableImpl());
    }
}

    //创建一个类 用来实现runnable接口  实现run方法
class RunnableImpl implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread()+"这是我创建的一个线程池");
    }
}