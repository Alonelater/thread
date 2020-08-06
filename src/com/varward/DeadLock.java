package com.varward;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 *
 * 下面我们介绍一下死锁的问题
 * 死锁产生的条件是大家都拿着对方需要的资源相互僵持着不肯释放 所以造成了死锁 现在我们模拟一个死锁的案例
 *
 */
public class DeadLock {


    public static void main(String[] args) {
        MakeUp makeUp = new MakeUp(0,"校花");
        MakeUp makeUp2 = new MakeUp(1,"小红");
        new Thread(makeUp).start();
        new Thread(makeUp2).start();
    }
    //小红拿到了镜子
    //校花拿到了口红
    //
    //Process finished with exit code -1


    //这样在相互争抢资源 就会造成的死锁   现在我们改良一下这个死锁问题

    //因为我们都是拿着对方的锁所以才会造成死锁 所以我们只要放开就不会抱着别人的锁了
    //所以我们改良一下就是讲每个的第二把锁拿出来就行了 就不会死锁问题了

//
//        if (choice==0){
//        //如果他是0 我们就让他拿到口红 并且休息一秒就去拿镜子化妆
//
//        synchronized (lipStick){
//            System.out.println(this.girlName+"拿到了口红");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//        synchronized (mirror){
//            System.out.println(this.girlName+"拿到了镜子");
//        }
//
//    }else {
//        synchronized (mirror){
//            System.out.println(this.girlName+"拿到了镜子");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//        synchronized (lipStick){
//            System.out.println(this.girlName+"拿到了口红");
//        }
//
//    }


}


//设计一个化妆类  里面需要口红和镜子
//口红类
class LipStick{



}

//镜子类
class  Mirror{}


//化妆类
class MakeUp implements  Runnable{
    //首先我们需要有一个镜子和一个口红  并且保证只有一份
    static LipStick lipStick = new LipStick();
    static Mirror mirror = new Mirror();


    //现在我们准备一个状态 用来区分我们是当前持有什么资源
    int choice;
    String girlName;

    public MakeUp(int choice,String girlName){
        this.choice=choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {

        makeUp();
    }


    //化妆的方法
    private void makeUp(){


        //现在我们根据状态来区别拿到的资源
        if (choice==0){
            //如果他是0 我们就让他拿到口红 并且休息一秒就去拿镜子化妆

            synchronized (lipStick){
                System.out.println(this.girlName+"拿到了口红");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (mirror){
                    System.out.println(this.girlName+"拿到了镜子");
                }

            }


        }else {
            synchronized (mirror){
                System.out.println(this.girlName+"拿到了镜子");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lipStick){
                    System.out.println(this.girlName+"拿到了口红");
                }
            }


        }

    }
}