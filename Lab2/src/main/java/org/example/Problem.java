package org.example;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Clasa Problem stocheaza 2 array uri cu toate depozitele si vehiculele depozitelor respective.
 */
public class Problem {
    private ArrayList<Depot> depots;
    private ArrayList<Vehicle> vehicles;

    /**
     * Constructorul atribuie doar masinile unice,fara duplicate.
     */
    public Problem(Depot[] depots) {
        this.depots=new ArrayList<>();
        int i =0;
        for(Depot d : depots) {
            boolean ok=true;
            for(Depot d1 : this.depots){
                if(d != null){
                if(Objects.equals(d1.getName(), d.getName())){
                    ok=false;
                    i--;
                }}
            }
            i++;
            if(ok == true){
                /**elementul nu exista deja in array
                    */
                 this.depots.add(d);
            }

        }

    }

 public ArrayList<Depot> getDepots() {
 return this.depots;
 }

 public ArrayList<Vehicle> getVehicle(){
 return  this.vehicles;
 }

    /**
     * Metoda setVehicles seteaza vehiculele din depozite in array ul cu toate vehiculel
     * Nu vor exista duplicate,deoarece nu pot exista in depozite masini duplicate.
     */
 public void setVehicles() {
    this.vehicles = new ArrayList<>();
    for(Depot d : this.depots){
        // System.out.println("Depot: " + d.getName());
        if(d != null){
            if(!Objects.equals(d.getName(),null)){
                this.vehicles.addAll(d.getVehicles());
            }
        }
    }
 }

}