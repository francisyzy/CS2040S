import java.io.*;
import java.util.*;

public class coconut {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int syllables = fio.nextInt(); // read int
        int players = fio.nextInt(); // read int

        // LL of hands that are in the game
        LinkedList<hand> ll = new LinkedList<hand>();
        // Creates the players and adds them in order into the ll
        for (int i = 0; i < players; i++) {
            ll.addLast(new hand(i));
        }
        do {
            for (int i = 0; i < syllables - 1; i++) {
                hand move = ll.removeFirst();
                ll.addLast(move);
            }
            hand current_hand = ll.removeFirst();

            // If the hand is folded, break the hand into two. Typings are auto inside the
            // hand class
            if (current_hand.handType == "folded") {
                ll.addFirst(new hand(current_hand.playerId));
                ll.addFirst(new hand(current_hand.playerId));
            } else if (current_hand.handType == "fist") { // If its a fist, change it to a palm and the next hand starts
                                                          // Thus the current hand will be added into the back
                ll.addLast(new hand(current_hand.playerId, "palm"));
            } // If its a palm, do nothing because it has already been removed from the ll

        } while (ll.size() > 1);
        // Do while loop because the game needs to be played regardless

        fio.println(ll.getFirst().playerId); // gets the first player and prints it
        fio.close(); // important; always close at the end of the code
    }
}

class hand {
    public String playerId;
    public String handType;

    /**
     * Create hand with id + 1. Code i = 0 but id starts @ 1
     * Hand of original state
     * 
     * @param id
     */
    public hand(int id) {
        this.playerId = Integer.toString(id + 1);
        this.handType = "folded";
    }

    /**
     * Create hand using ID from string, means copying from a previous hand. If
     * copying means the hand change to next state
     * 
     * @param id
     */
    public hand(String id) {
        this.playerId = id;
        this.handType = "fist";
    }

    /**
     * Creates hand with given id and state of the hand
     * 
     * @param id
     * @param type
     */
    public hand(String id, String type) {
        this.playerId = id;
        this.handType = type;
    }

    /**
     * Was used to set the type of the hand so that new hands do not need to be
     * created
     * 
     * @param type (current hand type)
     */
    public void setHandType(String type) {
        this.handType = type;
    }

    /** Used for debugging to print the playerId from the ll */
    @Override
    public String toString() {
        return playerId;
    }

}

/**
 * Fast I/O
 * 
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
class FastIO extends PrintWriter {
    BufferedReader br;
    StringTokenizer st;

    public FastIO() {
        super(new BufferedOutputStream(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
