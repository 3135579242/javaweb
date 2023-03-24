package com.along;

public class Test {
    public static void main(String[] args) {


        Thread thread = Thread.currentThread();
        System.out.println(thread);

        UserService userService = new UserService();
        userService.save();
    }
}
