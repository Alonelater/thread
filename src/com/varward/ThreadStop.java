package com.varward;

import javax.print.attribute.standard.PrinterURI;
import java.util.TreeMap;

//这个类用来模拟一个线程停止的小案例 我们要记住我们要自己让线程停止 设置标志位 通过自己来的改变标志位 从而达到停止线程的目的
public class ThreadStop implements Runnable {


    private boolean flag=true;


    @Override
    public void run() {
        while (flag) {
            System.out.println(Thread.currentThread().getName()+"============》线程还在跑");
        }
    }


    //自定义让线程停下来的办法
    public void myStop() {
        this.flag = false;
        System.out.println(Thread.currentThread().getName()+"=====================================");

    }


    public static void main(String[] args) {
        //现在我们模拟这个状态
        ThreadStop threadStop = new ThreadStop();
        new Thread(threadStop,"小明").start();
        for (int i = 0; i <200; i++) {
            System.out.println("主线程在"+i+"跑");
            if (i==100){
                threadStop.myStop();
            }
        }


    }


}
