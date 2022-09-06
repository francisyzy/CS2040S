
***REMOVED***
import java.util.*;

public class cardtrading {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int n = fio.nextInt();
        int t = fio.nextInt();
        int k = fio.nextInt();
        ArrayList<Integer> deck = new ArrayList<Integer>();
        // Get distribution of deck
        int[] cardFrequency = new int[t];
        deck.ensureCapacity(n);
        for (int i = 0; i < n; i++) {
            int cardId = fio.nextInt();
            deck.add(cardId);
            cardFrequency[cardId-1] += 1;
        }
        
        ArrayList<ShopItem> items = new ArrayList<ShopItem>();
        items.ensureCapacity(t);
        for (int i = 0; i < t; i++) {
            items.add(new ShopItem(i + 1, fio.nextInt(), fio.nextInt()));
        }

        // deck.sort((c1, c2) -> c1.compareTo(c2));

        // Get value of deck
        // long valueOfDeck = 0;
        // for (Integer deckCard : deck) {
        // valueOfDeck += items.get(deckCard).sellPrice;
        // }

        // // Get distribution of deck
        // int[] cardFrequency = new int[t];
        // Set<Integer> distinct = new HashSet<Integer>(deck);
        // for (Integer card : distinct) {
        //     cardFrequency[card - 1] = Collections.frequency(deck, card);
        // }

        // Get and set the opportunity cost of cards & set the frequency of cards
        for (int i = 0; i < cardFrequency.length; i++) {
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
        // items.sort((card1, card2) -> card1.compare(card1, card2));
        Collections.sort(items);
        // for (int i = 1; i < items.size(); i++) {
        // if (i < k+1) {
        // cost -= items.get(i).buyPrice;
        // } else {
        // cost += items.get(i).sellPrice;
        // }
        // }
        // if (n < 1) {
        // for (int i = 0; i < k; i++) {
        // cost -= items.get(i).getBuyPrice();
        // }
        // } else {
        // for (int i = 0; i < k; i++) {
        // cost -= items.get(i).getBuyPrice();
        // // System.out.println(items.get(i).getBuyPrice());
        // }
        // // System.out.println(cost);
        // for (int i = k; i < items.size(); i++) {
        // cost += items.get(i).getSellPrice();
        // // System.out.println(items.get(i).getSellPrice());
        // }
        // }
        for (int i = 0; i < items.size(); i++) {
            if (i < k) {
                cost -= items.get(i).getBuyPrice();
            } else {
                cost += items.get(i).getSellPrice();
            }
        }
        fio.println(cost);
        fio.close();
        // System.out.println(items.toString());

        // Check if inputs are correct
        // System.out.println(n);
        // System.out.println(t);
        // System.out.println(k);
        // for (Integer deckItem : deck) {
        // System.out.print(deckItem);
        // }
        // System.out.println("");
        // for (ShopItem shopItem : items) {
        // System.out.println(shopItem);
        // }

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

    @Override
    public String toString() {
        return cardId + " " + buyPrice + " " + sellPrice + " " + opportunityCost;
    }
}