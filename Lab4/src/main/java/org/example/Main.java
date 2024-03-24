package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        Person person1 = new Person("Maria",30,"6",true);
        Person person2 = new Person("Ioana",20,"6",false);
        Person person3 = new Person("Ana",18,"5",true);
        Person person4 = new Person("Andrei",38,"5",false);
        Person person5 = new Person("Florin",30,"3",false);
        Person person6 = new Person("Robert",25,"4",true);
        Person person7 = new Person("Ex1",23,"4",false);
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);
        persons.add(person6);
        persons.add(person7);
        person7.setCurrentLocation("3");

        System.out.println("----Persons---");
        for(Person p : persons){
            System.out.println(p);
        }
        List<Person> dirvers = persons.stream()
                .filter(Person::getDriver)
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println("----Drivers---");
        for(Person d : dirvers){
            System.out.println(d);
        }
        //setam driverilor locatiile prin care trec
        //...
        List<String> locations = new ArrayList<>();
        locations.add("3");
        locations.add("4");
        locations.add("5");
        locations.add("6");
        person1.setDriverLocations(locations);
        locations.clear();
        locations.add("3");
        locations.add("4");
        locations.add("5");
        person3.setDriverLocations(locations);
        locations.clear();
        locations.add("3");
        locations.add("4");
        person6.setDriverLocations(locations);

        Set<Person> passengers = new TreeSet<>(Comparator.comparing(Person::getName));
        passengers.addAll(persons.stream().filter(p->!p.isDriver).toList());

        System.out.println("----Passengers---");
        for (Person pa : passengers){
            System.out.println(pa);
        }

        System.out.println("-----Homework------");
        List<String> allDestinations = dirvers.stream()
                .map(Person::getDestination).distinct()
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(allDestinations);

        Map<String , List<Person>> mapOfDestinations = new HashMap<>();

        for (String d : allDestinations){
            List<Person> keyDestination = new ArrayList<>();
            for(Person p : persons){
                if(p.getDestination() == d){
                    keyDestination.add(p);
                }
            }
            mapOfDestinations.put(d,keyDestination);
        }
        Faker fake = new Faker();
        List<Person> fakePersons = new ArrayList<>();
        for(int i=0; i<3; i++){
            fakePersons.add(new Person(fake.name().firstName(),fake.number().numberBetween(15,70),fake.address().city(),fake.random().nextBoolean()));
        }
        System.out.println("Fake persons " + fakePersons);

        System.out.println("-----------");
        Problem problem1 = new Problem(dirvers,passengers,allDestinations);
        problem1.matchDriversAndPassengers();
    }
}