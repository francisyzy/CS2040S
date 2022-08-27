import java.util.Scanner;

public class skener {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int z1 = sc.nextInt();
        int z2 = sc.nextInt();
        sc.nextLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            String curr = sc.nextLine();
            StringBuilder width = new StringBuilder();
            
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < z2; k++) {
                    width.append(curr.charAt(j));
                }
            }
            width.append("\n");
            
            StringBuilder height = new StringBuilder();
            for (int j = 0; j < z1; j++) {
                height.append(width);
            }
            sb.append(height);
            // }
        }

        System.out.println(sb.toString());

        sc.close();

    }

}
