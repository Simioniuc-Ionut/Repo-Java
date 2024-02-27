import java.lang.reflect.Array;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello World!");

        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        int n = (int) (Math.random() * 1000000);
        int rez=n;
        System.out.println(n);

        String binaryNr = "10101";
        String hexazecimalNr = "FF";
        rez*=3;
        rez += Integer.parseInt(binaryNr, 2);
        rez += Integer.parseInt(hexazecimalNr,16);
        rez *=6;

        System.out.println("rez= " + rez);

        int newRez=0;
        int newRez2=0;
        int sum=0;

        while(rez > 0 ) {

            newRez =rez % 10;

            rez=rez/10;

            newRez2= rez%10;

            rez=rez/10;

            sum = newRez2+newRez;
            if(rez < 9 && sum >=9 ){
                rez=sum;
                sum=0;
            }

        }

        System.out.println(sum);

        System.out.println("Willy-nilly, this semester I will learn " + languages[sum]);

    }
}