
// package JavaPrac.lastfactorialdigit;
import java.util.*;

public class lastfactorialdigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        for (int i = 0; i < input; i++) {
            int factorial = sc.nextInt();
            switch (factorial) {
                case 0:
                    System.out.println(1);
                    break;
                case 1:
                    System.out.println(1);
                    break;
                case 2:
                    System.out.println(2);
                    break;
                case 3:
                    System.out.println(6);
                    break;
                case 4:
                    System.out.println(4);
                    break;

                default:
                    System.out.println(0);
                    break;
            }
        }
        sc.close();
    }
}
