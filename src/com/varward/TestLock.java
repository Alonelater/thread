package com.varward;

//由于我们不能显示的声明 jdk5之后帮我们提供了一个lock我们来看一看 这个是怎么帮我们解决并发问题的



import java.util.List;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        Ticket11 ti = new Ticket11();
        new Thread(ti, "小明").start();
        new Thread(ti, "小红").start();
        new Thread(ti, "小黄").start();
        //小红===》拿到了第10
        //小明===》拿到了第10
        //小黄===》拿到了第9
        //小明===》拿到了第8
        //小黄===》拿到了第7
        //小红===》拿到了第6
        //小明===》拿到了第5
        //小黄===》拿到了第5
        //小红===》拿到了第5
        //小红===》拿到了第4
        //小黄===》拿到了第4
        //小明===》拿到了第4
        //小红===》拿到了第3
        //小黄===》拿到了第2
        //小明===》拿到了第2
        //小红===》拿到了第1
        //小明===》拿到了第1
        //小黄===》拿到了第1
        //小红===》拿到了第0
        //小黄===》拿到了第-1
        //小明===》拿到了第0
        //通过上面我们就会发现会有线程安全问题


        //然后在下面的程序中我们创建了一个lock对象
        //小明===》拿到了第10
        //小明===》拿到了第9
        //小明===》拿到了第8
        //小明===》拿到了第7
        //小红===》拿到了第6
        //小黄===》拿到了第5
        //小黄===》拿到了第4
        //小黄===》拿到了第3
        //小黄===》拿到了第2
        //小明===》拿到了第1
        //小明===》拿到了第0

    }
}


class Ticket11 implements Runnable {

    int ticketNum = 10;


    //现在我们解决这个问题
    ReentrantLock lock = new ReentrantLock();


    @Override
    public void run() {




        while (true) {

            try {
                lock.lock();
                if (ticketNum < 0) {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println(Thread.currentThread().getName() + "===》拿到了第" + ticketNum--);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        }
    }
}