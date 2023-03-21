package com.along.template2;

public abstract class Person {

    public void day() {
        qiChuang();
        chiZaoCan();
        doSume();
        chiWanFan();
    }


    public void chiZaoCan() {
        System.out.println("吃早餐");
    }


    public void qiChuang() {
        System.out.println("起床");
    }

    public abstract void doSume();


    public void chiWanFan() {
        System.out.println("吃晚饭");
    }


}
