
import java.util.*;

public class apaxiaaans {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Character previousChar = name.charAt(0);
        StringBuilder sb = new StringBuilder(previousChar.toString());
        for (int i = 1; i < name.length(); i++) {
            Character curr = name.charAt(i);
            if (previousChar.equals(curr)) {
                previousChar = curr;
                continue;
            }
            previousChar = curr;
            sb.append(curr.toString());
        }
        System.out.println(sb.toString());
        sc.close();
    }
}
