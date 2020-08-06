package com.varward;

import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;
import org.omg.CORBA.PRIVATE_MEMBER;

public class ProductAndConsumer {

    public static void main(String[] args) {
        Baozi baozi = new Baozi();
        new Thread(new BaoZiPu(baozi)).start();
        new Thread(new ChiHuo(baozi)).start();
    }

}

/**
 * 生产者（包子铺） 是一个线程类 可以实现Runnable接口
 * 设置线程任务就是生产包子
 * 对包子的状态进行判断
 * true  表示有包子
 * 那包子铺进入等待状态wait
 * false 表示没包子
 * 包子铺生产包子
 * 然后修改包子状态
 * 唤醒吃货线程 ，让吃货线程吃包子
 * <p>
 * <p>
 * 注意包子铺线程和包子线程关系是互斥的
 * 必须使用同步技术保证两个线程只能有一个在执行
 * 必须保证唯一  可以使用包子对象作为锁对象
 * 所以包子铺和吃货的类必须将包子这个类作为参数传递进来
 * 1.作为成员变量
 * 2.利用构造器赋值
 */
//包子类
class Baozi {

    //包子的属性
    //皮
    String pi;
    //馅
    String xian;
    //是否有包子
    boolean flag = false;

}

class BaoZiPu implements Runnable {

    //需要一个包子的成员变量做为锁
    private Baozi baozi = null;

    public BaoZiPu(Baozi baozi) {
        this.baozi = baozi;
    }


    @Override
    public void run() {
        int count = 0;
        while (true) {
            //必须使用同步技术保证只有一个进行
            synchronized (baozi) {

                //对包子的状态进行判断
                //有包子
                if (baozi.flag == true) {
                    //那就别做了
                    try {
                        baozi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                //被唤醒之后就是没有包子了 那我们就要开始制作包子
                if (count % 2 == 0) {
                    baozi.pi = "薄皮";
                    baozi.xian = "三鲜馅";
                } else {
                    baozi.pi = "厚皮";
                    baozi.xian = "虾仁馅";
                }

                count++;
                System.out.println("包子铺正在生产" + baozi.pi + baozi.xian + "的包子");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //更改包子线程状态
                baozi.flag = true;
                //唤醒吃货线程
                baozi.notify();
                System.out.println("包子铺已经生产好了包子，吃货可以开始吃了");
            }
        }

    }
}


class ChiHuo implements Runnable {

    //需要一个包子的成员变量做为锁
    private Baozi baozi = null;

    public ChiHuo(Baozi baozi) {
        this.baozi = baozi;
    }

    @Override
    public void run() {

        while (true) {
            //必须使用同步技术保证只有一个进行
            synchronized (baozi) {

                //对包子的状态进行判断
                //没有包子
                if (baozi.flag == false) {
                    //那就吃不了的  那就等着
                    try {
                        baozi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                System.out.println("吃货正在吃" + baozi.pi + baozi.xian);
                //更改包子线程状态
                baozi.flag = false;
                //唤醒包子铺线程
                baozi.notify();
                System.out.println("-----------------------");
            }

        }
    }
}