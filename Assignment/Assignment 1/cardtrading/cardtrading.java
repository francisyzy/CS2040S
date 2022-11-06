
import java.util.*;

public class cardtrading {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int n = fio.nextInt();
        int t = fio.nextInt();
        int k = fio.nextInt();

        //Create Deck ArrayList
        ArrayList<Integer> deck = new ArrayList<Integer>();
        deck.ensureCapacity(n);

        // Get distribution of deck (frequency of card showing up in the deck)
        int[] cardFrequency = new int[t];

        for (int i = 0; i < n; i++) {
            int cardId = fio.nextInt();
            deck.add(cardId);
            cardFrequency[cardId-1] += 1;
        }

        //Create Shop ArrayList        
        ArrayList<ShopItem> items = new ArrayList<ShopItem>();
        items.ensureCapacity(t);
        for (int i = 0; i < t; i++) {
            items.add(new ShopItem(i + 1, fio.nextInt(), fio.nextInt()));
        }

        // Get and set the opportunity cost of cards & set the frequency of cards
        for (int i = 0; i < cardFrequency.length; i++) {
            //Set the count of deck into the shop card data
            items.get(i).setFrequency(cardFrequency[i]);
            if (cardFrequency[i] == 2) {
                items.get(i).setOpportunityCost(items.get(i).sellPrice * 2);
            } else if (cardFrequency[i] == 1) {
                items.get(i).setOpportunityCost(items.get(i).buyPrice + items.get(i).sellPrice);
            } else if (cardFrequency[i] == 0) {
                items.get(i).setOpportunityCost(items.get(i).buyPrice * 2);
            }
        }

        long cost = 0;
        //Sort the shop items by their opportunity cost
        Collections.sort(items);
        for (int i = 0; i < items.size(); i++) {
            if (i < k) {
                cost -= items.get(i).getBuyPrice();
            } else {
                cost += items.get(i).getSellPrice();
            }
        }
        fio.println(cost);
        fio.close();
    }
}

class ShopItem implements Comparable<ShopItem> {
    int cardId;
    long buyPrice;
    long sellPrice;
    long opportunityCost = 0;
    int frequency = 0;

    public ShopItem(int cardId, long buyPrice, long sellPrice) {
        this.cardId = cardId;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    /**
    Get the buy price of the card. If the user already has 2 card, 2-0 * buy = 0 
    as the user does not need to buy any cards.
    */
    public long getBuyPrice() {
        return (2 - frequency) * this.buyPrice;
    }

    public long getSellPrice() {
        return frequency * this.sellPrice;
    }

    public void setOpportunityCost(long opportunityCost) {
        this.opportunityCost = opportunityCost;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
    Comparing current shop item's opportunity cost and the incoming opportunity cost
    */
    @Override
    public int compareTo(ShopItem card2) {
        if (this.opportunityCost > card2.opportunityCost) {
            return 1;
        } else if (this.opportunityCost < card2.opportunityCost) {
            return -1;
        } else {
            return 0;
        }
    }
    /**
    For debug when printing out the shop items
    */
    @Override
    public String toString() {
        return cardId + " " + buyPrice + " " + sellPrice + " " + opportunityCost;
    }
}