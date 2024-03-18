package org.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Clasa Tour reprezinta programul in care un anumit numar de clienti programati la o anumita ora,
 * pot face un tuur cu o anumita masina
 */
public class Tour {
    private Vehicle vehicle;
    private ArrayList<Client> clients;
    private ArrayList<ArrayList<Client>> matrix;

    private ArrayList<Vehicle> visitedVehicles;
    public Tour(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public Tour(){}
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

        LocalTime currentTime ;

        Depot currentDepot = vehicle.getDepot();

        this.matrix = new ArrayList<>();
        visitedVehicles = new ArrayList<>();
        visitedVehicles.add(vehicle);
        while(true) {
            ArrayList<Client> currentVehicleClients= new ArrayList<>();
            this.matrix.add(currentVehicleClients);
           currentTime=LocalTime.MIN;
            for (Client c : clients) {

                /**daca e dupa min time ,adaug
                 */
                if (!this.clients.contains(c)) {
                    if (c.getMinTime().isAfter(currentTime)) {
                        this.clients.add(c);
                        currentVehicleClients.add(c);
                        /**sa pot face intervalul de tur
                         */
                        currentTime = c.getMaxTime();

                    }
                }
            }
            if (this.clients.size() != clients.length) {
                //au mai ramas clienti nealocati.
                int ok=1;
                for (Vehicle v : currentDepot.getVehicles()) {
                    if (!visitedVehicles.contains(v)) {
                        vehicle = v;
                        ok=0;
                        visitedVehicles.add(v);
                        break;
                    }
                }
                if(ok == 1){//nu s a gasit un alt vehicul nevizitat
                    System.out.println("Nu mai exista vehicule disponibile");
                    break;
                }
            }else{
                break;
            }
        }

    }

    public ArrayList<Client> getClients() {
        return clients;
    }
    public ArrayList<Client> getRandomInstance(Depot depot){
        Random random = new Random();
        int numberOfVehicles= random.nextInt(10);
        while (numberOfVehicles == 0){//ne asiguram ca se genereaza macar un vehicul
            numberOfVehicles=random.nextInt(15);
        }
        int numberOfClients = random.nextInt(15);

        System.out.println("Random vehicels : " + numberOfVehicles);
        System.out.println("Random clients : "+numberOfClients);

        ArrayList<Client> randomClients= new ArrayList<>();

        for(int i=1; i<=numberOfVehicles; i++){
            String name = "Vehicle " + i;
            Vehicle v = new Trucks(name,depot);
            depot.setVehicles(v);
        }
        this.vehicle=depot.getVehicles().get(0);

        for(int i=1; i<=numberOfClients;i++){
            String name = "Client "+ i;
            LocalTime min ,max;

            min=LocalTime.of(random.nextInt(24), random.nextInt(59));
            max = LocalTime.of(random.nextInt(24), random.nextInt(59));
            while(min.isAfter(max)) {//generez pana cand maximul este dupa minim,ca sa putem crea intervalul
                max = LocalTime.of(random.nextInt(24), random.nextInt(59));
            }
            Client c = new Client(name,min,max,ClientType.REGULAR);
            randomClients.add(c);
        }
        return randomClients;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.size(); i++) {
            sb.append("Vehiculul ").append(i+1).append(" | ").append(visitedVehicles.get(i)).append(" : ");
            for (Client c : matrix.get(i)) {
                sb.append(c.getName()).append(" | ").append(c.getMinTime()).append(" - ").append(c.getMaxTime()).append(", ");
            }
            // Elimină ultima virgulă și spațiul
            sb.setLength(sb.length() - 2);
            sb.append("\n");
        }
        return sb.toString();
    }
}
