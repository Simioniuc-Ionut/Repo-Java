package org.example;

import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person("Maria",30,"Suceava",true);
        Person person2 = new Person("Ioana",20,"Suceava",false);
        Person person3 = new Person("Ana",18,"Suceava",false);
        Person person4 = new Person("Andrei",38,"Iasi",true);
        Person person5 = new Person("Florin",30,"Iasi",false);
        Person person6 = new Person("Robert",25,"Iasi",true);

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);
        persons.add(person6);

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

        Set<Person> passengers = new TreeSet<>(Comparator.comparing(Person::getName));
        passengers.addAll(persons.stream().filter(p->!p.isDriver).toList());

        System.out.println("----Passengers---");
        for (Person pa : passengers){
            System.out.println(pa);
        }

    }
}