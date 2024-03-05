package  org.example;

public class Main {
    public static void main(String[] args) {
        /*
       //Compulsory
        //Ex1
        System.out.print("Hello World!");
        //Ex2
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        //Ex3
        int n = (int) (Math.random() * 1000000);
        int rez=n;
        System.out.println(n);
        //Ex4
        rez*=3;
        rez += 0b10101;
        rez += 0xFF;
        rez *= 6;

        System.out.println("rez= " + rez);
        //Ex5
        int newRez;
        int newRez2;
        int sum=0;

        while(rez > 0 ) {

            sum +=rez % 10;

            rez=rez/10;
            if(rez == 0 && sum > 9 ){
                rez=sum;
                sum=0;
            }

        }

        System.out.println(sum);
        //Ex6
        System.out.println("Willy-nilly, this semester I will learn " + languages[sum]);
        */

        //Homework:

        long startRuningTime = System.currentTimeMillis();

        Homework a = new Homework(4,25);
        System.out.println(a.esteReductibil());

        Homework b = new Homework(1,13,15);
        b.esteValid();
        /*
        if (args.length<3){
            System.out.println("Not enough arguments");
        }
        Homework c = new Homework(Integer.parseInt(args[2]),Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        c.esteValid();
        System.out.println(c.list);
        */
        long endRuningTime= System.currentTimeMillis();
        long totalTime = endRuningTime - startRuningTime;

        System.out.print("Timp total " + totalTime);

        //Bonus:
        WheelGraph wheel = new WheelGraph( 6);
        wheel.setTheMatrixOfWhell();
        wheel.getTheMatrixOfWhell();
        wheel.getCycles();

    }
}
