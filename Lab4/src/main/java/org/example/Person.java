package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Person {
    String name;
    int age;
    Boolean isDriver;
    String destination;
    String currentLocation;
    List<String> allLocations;



    public Person(String name, int age, String destination , Boolean isDriver) {
        this.name = name;
        this.age = age;
        this.isDriver = isDriver;
        this.currentLocation="1";
        this.destination = destination;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setDriverLocations(List<String> locations){
        if(isDriver == true){
            //pot seta mai multe locatii.
            allLocations = new ArrayList<>(locations);
        }else {
            System.out.println("Isn t a driver");
        }
    }

    public List<String> getAllLocations() {
        return allLocations;
    }

    public String getDestination() {
        return destination;
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
                "name= " + name + '\'' +
                " age= " + age + '\'' + "isDrive= " + isDriver + '\'' + "Destination= "+ destination + '\'' +
                '}';
    }

}
