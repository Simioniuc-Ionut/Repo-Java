package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Depot depot = new Depot("Depot A");

        Client cl1 = new Client("Client 1",8,12, ClientType.PREMIUM);
        Client cl2 = new Client("Client 2", 9,30,ClientType.REGULAR);
        Client cl3 = new Client("Client 3",5,25,ClientType.REGULAR);

        Vehicle vehicle1 = new Vehicle("Toyota" , depot);

        List<Client> clients = new ArrayList<>();
        clients.add(cl1);
        clients.add(cl2);
        clients.add(cl3);
        vehicle1.setClients(clients);

        System.out.println(depot);
        System.out.println(vehicle1);
        System.out.println(cl1);
        System.out.println(cl2);
        System.out.println(cl3);


    }
}
