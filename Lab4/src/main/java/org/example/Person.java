package org.example;

public class Person {
    String name;
    int age;
    Boolean isDriver;
    String destination;

    public Person(String name, int age,String destination,Boolean isDriver) {
        this.name = name;
        this.age = age;
        this.isDriver = isDriver;
        this.destination = destination;
    }

    public Boolean getDriver() {
        return isDriver;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

}
