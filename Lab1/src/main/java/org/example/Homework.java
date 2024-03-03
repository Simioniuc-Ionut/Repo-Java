package org.example;

public class Homework {
    public int kReductibil;
    public int Numar;
    public int a;
    public int b;
    public  String list;
    public Homework(int kReductibil,int Numar){
        this.kReductibil=kReductibil;
        this.Numar=Numar;
    }
    public Homework(int kReductibil,int a,int b){
        this.kReductibil=kReductibil;
        this.a=a;
        this.b=b;
    }
    public boolean esteReductibil(){
        int rez=0;
        while (Numar != 0){
            rez=0;
            while(Numar!=0){
                rez += (Numar%10) * (Numar%10);
                Numar=Numar/10;
            }
            if(rez >= 9){
                Numar=rez;
            }
        }
        // System.out.println(rez);
        return rez == kReductibil;
    }
    public void esteValid(){

        while(a!=b+1){
            Homework ex = new Homework(kReductibil,a);

            boolean valid = ex.esteReductibil();
            System.out.println("Numarul " + a + " este " + valid);

            if(list == null){
                list = String.valueOf(a);
            }else {
                list = list + " " + String.valueOf(a);
            }
            a++;
        }

    }

}
