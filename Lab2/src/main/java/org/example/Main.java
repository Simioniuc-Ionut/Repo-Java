package org.example;

import java.time.LocalTime;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /**
         * Am creat:
         * 3 depozite
         * 4 clienti ,din care clientul 4 cu clientul 3 sunt egali
         * 4 vehicule ,2 trucks ,2 Drone
         */
        Depot depot  = new Depot("Depot A");
        Depot depot2 = new Depot("Depot B");
        Depot depot3 = new Depot("Depot C");
        Depot depot4 = new Depot("Depot D");

        Client cl1 = new Client("Client 1",LocalTime.of(8,30),LocalTime.of(10,0), ClientType.PREMIUM);
        Client cl2 = new Client("Client 2",LocalTime.of(4,20),LocalTime.of(6,0), ClientType.REGULAR);
        Client cl3 = new Client("Client 3",LocalTime.of(6,25),LocalTime.of(7,25),ClientType.REGULAR);
        Client cl4 = new Client("Client 4",LocalTime.of(5,25),LocalTime.of(6,25),ClientType.REGULAR);
        Client cl5 = new Client("Client 5",LocalTime.of(9,0),LocalTime.of(9,30),ClientType.REGULAR);
        Client cl6 = new Client("Client 6",LocalTime.of(5,0),LocalTime.of(9,0),ClientType.REGULAR);

        Vehicle vehicle1 = new Trucks("Toyota" , depot,50);
        Vehicle vehicle2 = new Drone("Mazada" , depot, LocalTime.of(4,30));
        Vehicle vehicle3 = new Trucks("Tesla",depot,40);
        Vehicle vehicle4 = new Drone("Mazada" , depot, LocalTime.of(4,30));

        Vehicle vehicle5 = new Trucks("Mercedes" , depot,50);
        Vehicle vehicle6 = new Drone("Volvo" , depot2, LocalTime.of(4,30));
        Vehicle vehicle7 = new Trucks("Audi",depot3,40);
        Vehicle vehicle8 = new Drone("Skoda" , depot2, LocalTime.of(4,30));

        Vehicle vehicle9 = new Trucks("Lamburghini" , depot2,50);
        Vehicle vehicle10 = new Drone("Ferari" , depot3, LocalTime.of(4,30));


        /**
         * setam vehiculele in depot
         */
        depot.setVehicles(vehicle1,vehicle2,vehicle3,vehicle4,vehicle5);
        depot2.setVehicles(vehicle6,vehicle8,vehicle9);
        depot3.setVehicles(vehicle7,vehicle10);

        /**
         * Afisez tot ce am creat
         */

        System.out.println(vehicle2);
        System.out.println(vehicle1);
        System.out.println(vehicle3);
        System.out.println(vehicle4);
        System.out.println(cl1);
        System.out.println(cl2);
        System.out.println(cl3);
        System.out.println(cl4);

        System.out.println(depot);
        System.out.println(depot2);
        System.out.println(depot3);

        /**
         * Problem part:
         * Creez 2 array uri de depoturi si clienti care sa includa tot ce am creat mai sus
         * Instantiez clasa Problem si adaug toate depoturile. Apoi extrag toate vehiculele setate in depot ul respectiv
         * Testez duplicatele(depot,vehicles,clients),acestea nu sunt incluse in instanta clasei Problem
         */

        Depot arrayOfDepot[] = new Depot[5];
        arrayOfDepot[0]=depot;
        arrayOfDepot[1]=depot2;
        arrayOfDepot[2]=depot;
        arrayOfDepot[3]=depot3;
        arrayOfDepot[4]=depot4;


        Client arrayOfClients[] = new Client[6];
        arrayOfClients[0]=cl1;
        arrayOfClients[1]=cl2;
        arrayOfClients[2]=cl3;
        arrayOfClients[3]=cl4;
        arrayOfClients[4]=cl5;
        arrayOfClients[5]=cl6;

        System.out.println("-------All Depots from problem--------");
        Problem problem = new Problem(arrayOfDepot);
        System.out.println(problem.getDepots() + " ~ ");

        ArrayList<Vehicle> allVehiclesFromProblem;
        problem.setVehicles();
        allVehiclesFromProblem = problem.getVehicle();

         /**
         * Afisez toate vehiculele din depozit ,fara duplicate
         */
         System.out.println("-------All Vehicles from problem--------");
        for(Vehicle v : allVehiclesFromProblem){
            System.out.print(" " + v.getName() + " , ");
        }
        System.out.println();
        System.out.println("-----Tour------");
        /**
         * Creez un tour care asigneaza clietnii(in functie de localtime) la vehiculul corespunzator
         */
        Tour tour = new Tour(vehicle3);
        tour.setClients(arrayOfClients);
        System.out.println(tour.toString());

        System.out.println("----------Random Generator--------");
        Depot generateDepot = new Depot("Depot R");

        Tour randomTourInstance = new Tour();
        ArrayList<Client> randomClients = new ArrayList<>(randomTourInstance.getRandomInstance(generateDepot));
        randomTourInstance.setClients(randomClients.toArray(Client[]::new));
        System.out.println(randomTourInstance);

    }
}
