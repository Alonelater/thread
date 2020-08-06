package com.varward;

//这是实现线程类的第二种方式 在一定程度上解决了只要单继承带来的局限性


public class ThreadTest2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("开始学习线程相关知识"+i);
        }

    }

    public static void main(String[] args) {
        //首先我们是实现接口  所以我们启动需要一个thread的实例类
        new Thread(new ThreadTest2()).start();
        for (int i = 0; i < 500; i++) {

            System.out.println("这是主函数的输出语句"+i);
        }
    }

    //总结这两种方式都能帮我们实现创建一个线程类  并且开启线程  现在我们模拟两个案例帮我们认识线程在
    //生活中的妙用一级可能带来的问题



}
