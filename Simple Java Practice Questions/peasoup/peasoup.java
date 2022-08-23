import java.util.*;

public class peasoup {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int noOfRestaurant = sc.nextInt();

        // boolean noPea = true;
        // boolean noPancake = true;
        String foundRestaurants = "";

        for (int i = 0; i < noOfRestaurant; i++) {
            int noOfMenuItems = sc.nextInt();
            sc.nextLine();
            String restaurantName = sc.nextLine();
            // System.out.println(restaurantName);
            boolean peaFound = false;
            boolean pancakeFound = false;
            for (int j = 0; j < noOfMenuItems; j++) {
                String menuItem = sc.nextLine();
                // System.out.println(menuItem);
                // if (menuItem.equals("pea soup") && peaFound == false) {
                // peaFound = true;
                // // noPea = false;
                // if (pancakeFound) {
                // System.out.println(restaurantName);
                // }
                // }
                // if (menuItem.equals("pancakes") && pancakeFound == false) {
                // pancakeFound = true;
                // // noPancake = false;
                // if (peaFound) {
                // System.out.println(restaurantName);
                // }
                // }
                if (menuItem.equals("pea soup")) {
                    peaFound = true;
                }
                if (menuItem.equals("pancakes")) {
                    pancakeFound = true;
                }
                if (pancakeFound && peaFound && foundRestaurants.length() == 0) {
                    foundRestaurants = restaurantName;
                }
            }
        }

        // if (noPea || noPancake) {
        // System.out.println("Anywhere is fine I guess");
        // }
        if (foundRestaurants.length() == 0) {
            System.out.println("Anywhere is fine I guess");
        } else {
            System.out.println(foundRestaurants);
        }

        sc.close();

    }
}
