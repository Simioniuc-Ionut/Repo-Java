package org.example;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Attraction a1 = new Church("Notredam","Visitable", new String[]{"Monday", "Tuesday", "Wednesday","Thursday"}, LocalTime.of(2,0),0);
        Attraction a2 = new Concert("BAttle" ,"Music",new String[]{"Monday", "Tuesday", "Wednesday"},LocalTime.of(1,0),20);
        Attraction a3 = new Statue("Infinit","Cultural",new String[]{"Monday"},LocalTime.of(2,0),0);
        Trip tripToBarca = new Trip("Barcelona",new Pair<>(LocalDate.of(2023,3,12),LocalDate.of(2023,3,19)));

        tripToBarca.addAttractions(a1);
        tripToBarca.addAttractions(a2);
        tripToBarca.addAttractions(a3);
        System.out.println(tripToBarca);

        //Homework:
        System.out.println("--------Homework--------");
        tripToBarca.displayLocations();

        Attraction a4 = new Statue("Hydra-Copou","Park-Exponent",new String[]{"Tuesday","Wednesday"},LocalTime.of(2,0),30);
        Attraction a5 = new Concert("Carla's Dreams","Artist",new String[]{"Tuesday","Wednesday"},LocalTime.of(1,0),100);
        Attraction a6 = new Concert("The Motans","Artist" , new String[]{"Tuesday","Wednesday","Thursday"} , LocalTime.of(2,0),95);

        tripToBarca.addAttractions(a4);
        tripToBarca.addAttractions(a5);
        tripToBarca.addAttractions(a6);

        /**
         *Setam intervalul de vizitare
         */
        TimeInterval periodOfVisiting = new TimeInterval(LocalTime.of(10,30),LocalTime.of(12,30));
        a1.setTimetable(periodOfVisiting);
        TimeInterval periodOfVisiting2 = new TimeInterval(LocalTime.of(12,30),LocalTime.of(13,30));
        a2.setTimetable(periodOfVisiting2);
        TimeInterval periodOfVisiting3 = new TimeInterval(LocalTime.of(10,30),LocalTime.of(14,30));
        a3.setTimetable(periodOfVisiting3);
        TimeInterval periodOfVisiting4 = new TimeInterval(LocalTime.of(14,0),LocalTime.of(15,0));
        a4.setTimetable(periodOfVisiting4);
        TimeInterval periodOfVisiting5 = new TimeInterval(LocalTime.of(14,0),LocalTime.of(16,0));
        a5.setTimetable(periodOfVisiting5);
        TimeInterval periodOfVisiting6 = new TimeInterval(LocalTime.of(14,0),LocalTime.of(16,0));
        a6.setTimetable(periodOfVisiting6);


        /**
         * Luam data deschiderii pentru o data(opening hour of a given date);
         */

        System.out.println("Opening hour of a given date: " + a4.getOpeningHour("Tuesday"));
        System.out.println("Opening hour of a given date: " + a3.getOpeningHour("Monday"));
        System.out.println("Opening hour of a given date: " + a2.getOpeningHour("Wednesday"));
        System.out.println("Opening hour of a given date: " + a1.getOpeningHour("Wednesday"));

        System.out.println("---------Travel plan(Bonus)-------");

        //Bonus--
        TravelPlan travelPlan = new TravelPlan(tripToBarca.getPeriodOfTime());
        travelPlan.setTravelPlan(tripToBarca.getAttractions());
        travelPlan.printTravelPlan();


        //Linked list
        /*
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        int index =0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > 3) {
                list.add(i + 1, 3);
                break;
            }
        }
        System.out.println(list);*/


    }
}