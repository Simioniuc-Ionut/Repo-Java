package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Clasa Depot reprezinta un depozit cu un nume si un numar de vehicule (inregistrate in acel depozit)
 */
public class Depot{
    private String name;
    private ArrayList<Vehicle> vehicles;
    public Depot(String name) {
        this.name = name;
        this.vehicles = new ArrayList<>();
    }

    public Depot(String name, Vehicle[] vehicles) {
        this.name = name;
        this.vehicles = (ArrayList<Vehicle>) Arrays.asList(vehicles);
    }

    public String getName() {
        return name;
    }

    public  ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setName(String name) {
        this.name = name;
    }
    /**
     * Metoda pentru setare vehiculelor in depozit
     * @param vehicles , numarul de vehicule setate pe acel depozit
     * -verific daca sa nu exista duplicate si daca vehiculele sunt inregistrate pe depozitul curent
     */
    public void setVehicles(Vehicle ... vehicles) {
        //this.vehicles = vehicles;
        for(Vehicle v : vehicles){
            if(Objects.equals(v.getDepot().getName(), this.name) && (!this.vehicles.contains(v))){//doar daca masina este trecuta in acelasi depot
             v.setDepot(this);
             this.vehicles.add(v);
            }
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof Depot))) return false;
        Depot depot = (Depot) o;
        return Objects.equals(getName(), depot.getName()) && Objects.equals(getVehicles(), depot.getVehicles());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getName(), getVehicles());
    }

    @Override
    public String toString() {
        if(vehicles == null){
        return "--Depot{" +
                "name='" + name + '\'' +
                ", vehicles=Not" + '\'' +
                '}';
        }else {
            return "--Depot{" +
                    "name='" + name + '\'' +
                    ", vehicles=" + this.vehicles.toString() + '\'' +
                    '}';
        }
    }
}
