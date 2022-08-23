import java.util.Arrays;
import java.util.Scanner;

public class akcija {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfBooks = sc.nextInt();
        int[] priceArray = new int[numberOfBooks];
        for (int i = 0; i < numberOfBooks; i++) {
            priceArray[i] = sc.nextInt();
        }
        Arrays.sort(priceArray);

        long total = 0;
        for (int i = numberOfBooks - 1; i > numberOfBooks % 3; i -= 3) {
            total += priceArray[i];
            total += priceArray[i - 1];
        }
        for (int i = 0; i < numberOfBooks % 3; i++)
            total += priceArray[i];
        System.out.println(total);

        // for (int i = 0; i < priceArray.length; i++) {
        // System.out.println(priceArray[i]);
        // }

        sc.close();
    }
}
