// package JavaPrac.timeloop;
import java.util.*;


public class timeloop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int incoming = sc.nextInt();

        for (int i = 1; i < (incoming+1); i++) {
            System.out.println(i + " Abracadabra");
        }

        sc.close();
    }
}
