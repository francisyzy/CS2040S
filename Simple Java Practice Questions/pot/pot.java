// package JavaPrac.pot;
import java.util.*;
public interface pot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        int output = 0;
        for (int i = 0; i < testCases; i++) {
            int input = sc.nextInt();
            int exponent = input % 10;
            int base = input / 10;
            output += Math.pow(base, exponent);
        }
        System.out.println(output);

        sc.close();
    }

}
