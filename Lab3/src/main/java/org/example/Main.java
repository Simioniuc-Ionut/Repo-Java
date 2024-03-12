package org.example;

public class Main {
    public static void main(String[] args) {
        Attraction a1 = new Church("Notredam","Visitable","Friday","12:",3);
        Attraction a2 = new Concert("BAttle" ,"Music","Friday","13:00",20);
        Attraction a3 = new Statue("Infinit","Cultural","Friday","12:00",40);
        Trip a4 = new Trip("Barcelona","Sunday");

        a4.addAttractions(a1);
        a4.addAttractions(a2);
        a4.addAttractions(a3);
        System.out.println(a4);
    }
}