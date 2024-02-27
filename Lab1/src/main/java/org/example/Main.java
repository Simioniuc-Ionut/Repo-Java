package  org.example;

public class Main {

    public static void main(String[] args) {
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

    }
}