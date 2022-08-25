import java.util.*;

public class peasoup {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int noOfRestaurant = sc.nextInt();

        String foundRestaurant = "";
        boolean restaurantFound = false;

        for (int i = 0; i < noOfRestaurant; i++) {
            int noOfMenuItems = sc.nextInt();
            sc.nextLine();
            String restaurantName = sc.nextLine();
            // System.out.println(restaurantName);
            boolean peaFound = false;
            boolean pancakeFound = false;
            for (int j = 0; j < noOfMenuItems; j++) {
                String menuItem = sc.nextLine();
                if (restaurantFound) {
                    continue;
                }
                if (menuItem.equals("pea soup")) {
                    peaFound = true;
                }
                if (menuItem.equals("pancakes")) {
                    pancakeFound = true;
                }
                if (pancakeFound && peaFound && restaurantFound == false) {
                    foundRestaurant = restaurantName;
                    restaurantFound = true;
                }
            }
        }

        if (restaurantFound) {
            System.out.println(foundRestaurant);
        } else {
            System.out.println("Anywhere is fine I guess");
        }

        sc.close();

    }
}
