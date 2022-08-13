import java.util.*;

public class a {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String incoming = sc.nextLine();

        String output = "";

        output+=incoming.charAt(0);

        int currIndex = 0;

        while(incoming.indexOf("-", currIndex)!= -1){
            int curr = incoming.indexOf("-", currIndex);
            // System.out.println(curr);
            output+=incoming.charAt(curr+1);
            currIndex = curr+1;
        }

        System.out.println(output);

        sc.close();

    }
}