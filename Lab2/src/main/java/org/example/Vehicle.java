package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehicle{
    private String name;
    private Depot depot;
    private List<Client> clients;
    public Vehicle(String name,Depot depot) {
        this.name = name;
        this.depot = depot;
        this.clients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Client> getClients() {
        return clients;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", depot=" + depot +
                ", clients=" + clients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || !(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(name, vehicle.name) && Objects.equals(depot, vehicle.depot) && Objects.equals(clients, vehicle.clients);
    }

}
