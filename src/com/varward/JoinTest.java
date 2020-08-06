package com.varward;


//这个join就是插队 很霸道 就是一定要让他所在的线程执行完了 别的线程才能执行
public class JoinTest implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("插队客户来了"+i);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        JoinTest joinTest = new JoinTest();

        Thread thread = new Thread(joinTest);
        thread.start();
        for (int i = 0; i < 500; i++) {
            if (i==100){
                //当i==100时  他一定要执行完了自己的线程才能执行其他的  就算是主线程都不行

                thread.join();
                //System.out.println("===============================");
            }
            System.out.println("mian在执行"+i);
        }
    }
}
