package com.varward;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 *
 * 为了避免单继承的局限性  我们在这使用实现接口的方式用来模拟火车站售票的场景
 */
public class ThreadTest3 implements Runnable {

    private int ticket=10;
    @Override
    public void run() {


        //我们准备10张票  让3个人来买 看看大家买票的情况如何

        while (true){
            if (ticket<=0){
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"---->买到了第"+ticket--+"张票");

        }

        //小明---->买到了第9张票
        //黄牛---->买到了第10张票
        //老师---->买到了第8张票
        //小明---->买到了第7张票
        //黄牛---->买到了第7张票
        //老师---->买到了第6张票
        //小明---->买到了第5张票
        //黄牛---->买到了第4张票
        //老师---->买到了第3张票
        //黄牛---->买到了第2张票
        //老师---->买到了第0张票
        //小明---->买到了第1张票
        //
        //Process finished with exit code 0

        //所以综上所述 可能就会出现同时买到了第七张票的可能  这对于现实生活来说是不合理的  后面我们会优化这个


        //下面我们再来模拟一个龟兔赛跑的案例


    }


    public static void main(String[] args) {
        ThreadTest3 threadTest3 = new ThreadTest3();
        //首先我们要准备三个身份用来买票
        new Thread(threadTest3,"小明").start();
        new Thread(threadTest3,"老师").start();
        new Thread(threadTest3,"黄牛").start();
    }
}
