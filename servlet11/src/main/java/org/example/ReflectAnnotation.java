package org.example;

import jakarta.servlet.annotation.WebServlet;

public class ReflectAnnotation {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> welcomeServletClass = Class.forName("org.example.WelcomeServlet");

        if (welcomeServletClass.isAnnotationPresent(WebServlet.class)) {
            WebServlet annotation = welcomeServletClass.getAnnotation(WebServlet.class);
            String[] value = annotation.value();
            for (int i = 0; i < value.length; i++) {
                System.out.println(value[i]);
            }

        }

    }
}
