// package JavaPrac.detaileddifferences;

import java.util.Scanner;

public class detaileddifferences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        sc.nextLine();//move to next line
        for (int i = 0; i < testCases; i++) {
            String output = "";
            String original = sc.nextLine();
            String modified = sc.nextLine();
            System.out.println(original);
            System.out.println(modified);
            for (int j = 0; j < original.length(); j++) {
                if(original.charAt(j)==modified.charAt(j)){
                    output += ".";
                } else {
                    output += "*";
                }
            }
            System.out.println(output);
            System.out.println("");
        }
        sc.close();
    }
}
