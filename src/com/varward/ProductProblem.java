package com.varward;

import java.io.ObjectInputStream;

/**
 * 这个案例演示的就是生产者消费者问题
 * <p>
 * 注意事项：顾客和老板线程必须得使用同步代码块包裹起来
 * 保证等待各唤醒只能有一个在执行
 * 同步使用的锁对象必须是唯一
 * 只有锁对象才能调用调用wait和notify方法
 */
public class ProductProblem {


    public static void main(String[] args) {
        //创建锁对象  保证唯一 直接传下去就行了
        Object object = new Object();
        new Thread(new Consumer(object)).start();

        new Thread(new Pruduct(object)).start();
        //这个已经能实现生产者消费者的通信问题了 现在我们再再自己写一个包子铺的问题


    }
}

//生产者的类
class Pruduct implements Runnable {

    private Object object = null;

    public Pruduct(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            System.out.println("老板说：我要开始做包子了");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("老板说：包子做好了，通知他来吃");
            //保证同一个object
            object.notify();
        }
    }

}

//消费者的类
class Consumer implements Runnable {

    private Object object = null;

    public Consumer(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            System.out.println("顾客说：告诉老板吃什么包子");
            try {
                object.wait();//顾客等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("顾客说：开吃");
        }
    }

}