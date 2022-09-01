
***REMOVED***
import java.util.ArrayList;
import java.util.Scanner;

public class cardtrading {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int k = sc.nextInt();
        ArrayList<Integer> deck = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            deck.add(sc.nextInt());
        }
        ArrayList<ShopItem> items = new ArrayList<ShopItem>();
        for (int i = 1; i < t + 1; i++) {
            items.add(new ShopItem(i, sc.nextInt(), sc.nextInt()));
        }
        sc.close();




        // Check if inputs are correct
        System.out.println(n);
        System.out.println(t);
        System.out.println(k);
        for (Integer deckItem : deck) {
            System.out.print(deckItem);
        }
        System.out.println("");
        for (ShopItem shopItem : items) {
            System.out.println(shopItem);
        }

    }
}

class ShopItem {
    int cardId;
    int buyPrice;
    int sellPrice;

    public ShopItem(int cardId, int buyPrice, int sellPrice) {
        this.cardId = cardId;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    @Override
    public String toString() {
        return cardId + " " + buyPrice + " " + sellPrice;
    }
}