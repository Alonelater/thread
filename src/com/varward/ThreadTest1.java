package com.varward;

//这个类主要是为了学会创建线程 明白程序，进程，线程之间的概念
//介绍第一种方式就是如何创建线程和实现线程
//继承Thread 重写run方法 然后调用start方法开启线程


public class ThreadTest1 extends  Thread {
    //重写改run方法
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("开始学习线程相关知识"+i);
        }
    }

    public static void main(String[] args) {

        //我们不能将这个放在我们的for循环里面  会抛出异常
        ThreadTest1 threadTest1 = new ThreadTest1();
        threadTest1.start();
        //告诉我们cpu我们已经准备好了 你可以随时调用 而不是调用run方法  调用run方法就不是线程的开启
        for (int i = 0; i < 500; i++) {

            System.out.println("这是主程序在跑");
        }




        //总结  主线程main里面的输出语句和线程里面的互不干扰 时间碎片分到谁就是谁之星  并发执行


    }
}
