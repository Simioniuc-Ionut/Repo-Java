package org.example;


import org.example.Test;

import java.lang.reflect.Method;


public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java ClassAnalyzer <fully-qualified-class-name>");
            System.exit(1);
        }

        String className = args[0];
        try {
            // Load the class dynamically
            Class<?> clazz = Class.forName(className);
            System.out.println("Class loaded: " + clazz.getName());

            // Extract and display class information
            analyzeClass(clazz);

            // Invoke methods annotated with @Test
            invokeTestMethods(clazz);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void analyzeClass(Class<?> clazz) {
        // Print class methods
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("Methods:");
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    private static void invokeTestMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // Check if the method is annotated with @Test
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    // If the method is static and has no parameters, invoke it
                    if (method.getParameterCount() == 0 && (method.getModifiers() & java.lang.reflect.Modifier.STATIC) != 0) {
                        method.setAccessible(true);
                        method.invoke(null);
                        System.out.println("Invoked @Test method: " + method.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}