package com.varward.syn;

import com.varward.ThreadStop;
import com.varward.ThreadTest3;

/**
 * 下面我们在回顾一下之前学习的买票 我们就会发现其中存在了并发问题  我们现在通过办法来解决并发问题
 */
public class Ticket implements Runnable {

    private int ticket = 10;


    @Override
    public void run() {

        //我们准备10张票  让3个人来买 看看大家买票的情况如何


        while (true) {

            //这里加了把锁  将我们卖票的过程锁起来 这样就不会有线程安全问题了

            synchronized (this) {
                if (ticket <= 0) {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "---->买到了第" + ticket-- + "张票");


            }
        }

        //小明---->买到了第10张票
        //小明---->买到了第9张票
        //小明---->买到了第8张票
        //小明---->买到了第7张票
        //小明---->买到了第6张票
        //小明---->买到了第5张票
        //小明---->买到了第4张票
        //黄牛---->买到了第3张票
        //老师---->买到了第2张票
        //老师---->买到了第1张票

    }


    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //首先我们要准备三个身份用来买票
        new Thread(ticket, "小明").start();
        new Thread(ticket, "老师").start();
        new Thread(ticket, "黄牛").start();
    }
}
