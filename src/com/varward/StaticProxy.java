package com.varward;

//今天我们讲述一下静态代理在线程中的妙用 首先我们先模拟一个静态代理的案例 就是结婚的案例
//主角：自己
//婚庆公司负责婚礼 其实就是你结婚 婚庆公司代理你结婚 帮你办事情

public class StaticProxy {


    public static void main(String[] args) {
        new WeddingCompany(new You()).happyMarry();
    }

}

//一个结婚的方法
interface Marry{


    void happyMarry();
}

//现在我要结婚了 所以我就要实现接口
class  You implements  Marry{
    @Override
    public void happyMarry() {
        System.out.println("今天我要结婚了 好开心");
    }
}


class WeddingCompany implements Marry{


    private Marry marry;


    public  WeddingCompany(Marry marry){
        this.marry=marry;
    }
    public Marry getMarry() {
        return marry;
    }

    public void setMarry(Marry marry) {
        this.marry = marry;
    }

    @Override
    public void happyMarry() {
        this.marry.happyMarry();
    }
}

