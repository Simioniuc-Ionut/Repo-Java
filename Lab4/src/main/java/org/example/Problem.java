package org.example;

import java.util.*;

public class Problem{
    List<Person> allDrivers;
    List<Person> allPassengers;
    Map<String,Integer> allDestinations;

    public Problem(List<Person> allDrivers,Set<Person> allPassengers , List<String> allDestinations){
            this.allDrivers = new ArrayList<>(allDrivers);
        this.allPassengers= new ArrayList<>();
            for(Person p : allPassengers){
                this.allPassengers.add(p);
            }
    }
    //destinatiile sunt sortate in functie de distanta dintre ele.
    public void matchDriversAndPassengers(){
        int index=0;
        Collections.sort(this.allDrivers ,Comparator.comparing(driver -> driver.getAllLocations().size()));

        int notPassagers=0;
        while (!allPassengers.isEmpty() && notPassagers!=1 ) {
            notPassagers=1;
            for (Person d : allDrivers) {
                for (Iterator<Person> it = allPassengers.listIterator(); it.hasNext(); ) {
                    Person currentPassenger = it.next();
                    if (d.getAllLocations().contains(currentPassenger.getDestination()) && d.currentLocation == currentPassenger.currentLocation) {//daca destinatia pasagerului este inclusa in locatiile driverului
                        d.setCurrentLocation(currentPassenger.getDestination());
                        System.out.println("Driver : " + d.getName() + " destination " + d.getDestination() + " Passenger: " + currentPassenger.getName() + " destination " + currentPassenger.getDestination());
                        it.remove();
                        notPassagers=0;
                        break;
                    }

                }
            }

        }
       if(!allPassengers.isEmpty()){
           System.out.println("Nu s au putut aloca toti pasagerii");
       }
    }

}
