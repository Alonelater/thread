package com.varward;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * 下面模拟龟兔赛跑
 * <p>
 * Race是我们的龟兔赛跑的赛道
 */
public class Race implements Runnable {

    private static String winner = null;

    @Override
    public void run() {
        if ("乌龟".equals(Thread.currentThread().getName())) {
            for (int i = 0; i <=100; i++) {
                System.out.println("乌龟"+ "--->跑了" + i + "米");
                if (i >= 100) {
                    winner = Thread.currentThread().getName();
                    break;
                }
            }
        }else {
            for (int j = 0; j <= 100; j=j+50) {
                System.out.println("兔子"+ "--->跑了" + j + "米");
                if(j%10==0){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (j >= 100) {
                    winner = Thread.currentThread().getName();
                    break;
                }
            }
        }
        if (winner!=null){
            return;
        }
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race,"乌龟").start();
        new Thread(race,"兔子").start();
    }

}
