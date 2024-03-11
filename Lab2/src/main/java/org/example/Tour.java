package org.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Clasa Tour reprezinta programul in care un anumit numar de clienti programati la o anumita ora,
 * pot face un tuur cu o anumita masina
 */
public class Tour {
    private Vehicle vehicle;
    private ArrayList<Client> clients;

    public Tour(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Metoda pentru a atribui intr un mod greedy clientii catre masina. Se face o sortare in
     * functie de ora/minutele programate ale clientilor
     * @param clients un array de clienti
     */
    public void setClients(Client[] clients){
        /** sort dupa timp min
        */
         Arrays.sort(clients, Comparator.comparing(Client::getMinTime));

        this.clients = new ArrayList<>();
        LocalTime currentTime = LocalTime.MIN;

        for (Client c : clients) {
            /**daca e dupa min time ,adaug
            */
             if (c.getMinTime().isAfter(currentTime)) {
                this.clients.add(c);
                /**sa pot face intervalul de tur
                */
                 currentTime = c.getMaxTime();
            }
        }
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "vehicle=" + vehicle +
                ", clients=" + clients +
                '}';
    }
}
