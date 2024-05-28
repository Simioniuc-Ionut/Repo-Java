package org.example;

import org.example.Test;

public class ExampleClass {

    private String name;
    private int age;

    public ExampleClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void staticMethod() {
        System.out.println("This is a static method.");
    }

    @Test
    public static void testMethod() {
        System.out.println("This is a test method.");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}