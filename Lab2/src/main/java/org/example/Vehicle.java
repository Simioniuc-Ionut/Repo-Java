package org.example;

import java.util.Objects;

/**
 * Clasa Vehicle este abstracta ,car are un nume de masina , si un anumit depozit unde este inregistrata
 */
public  abstract class  Vehicle{
    protected String name;
    protected Depot depot;
    public Vehicle(String name,Depot depot) {
        this.name = name;
        this.depot = depot;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", depot=" + this.depot.getName() +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || !(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(name, vehicle.name) && Objects.equals(depot, vehicle.depot);
    }

}
