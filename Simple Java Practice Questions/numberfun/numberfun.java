
// package JavaPrac.numberfun;
import java.util.*;

public class numberfun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for (int i = 0; i < testCases; i++) {
            Double a = sc.nextDouble();
            Double b = sc.nextDouble();
            Double c = sc.nextDouble();
            // System.out.println(a);
            // System.out.println(b);
            // System.out.println(c);
            if (a + b == c) {
                System.out.println("Possible");
            } else if (a - b == c) {
                System.out.println("Possible");
            } else if (b - a == c) {
                System.out.println("Possible");
            } else if (a * b == c) {
                System.out.println("Possible");
            } else if (a / b == c) {
                System.out.println("Possible");
            } else if (b / a == c) {
                System.out.println("Possible");
            } else {
                System.out.println("Impossible");
            }
        }
        
        sc.close();
    }
}
