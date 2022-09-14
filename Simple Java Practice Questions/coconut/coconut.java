import java.io.*;
import java.util.*;

public class coconut {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int syllables = fio.nextInt(); // read int
        int players = fio.nextInt(); // read int

        LinkedList<hand> ll = new LinkedList<hand>();
        for (int i = 0; i < players; i++) {
            // fio.println(ll.toString());
            ll.addLast(new hand(i));
        }
        // fio.println(ll.toString());
        // System.out.println(syllables);
        // System.out.println(players);
        do {
            // fio.println(ll.toString());
            for (int i = 0; i < syllables - 1; i++) {
                hand move = ll.removeFirst();
                ll.addLast(move);
            }
            hand curr_hand = ll.removeFirst();
            // fio.println(ll.toString());
            
            if (curr_hand.handType == "folded") {
                // fio.println("ASD");
                ll.addFirst(new hand(curr_hand.playerId));
                ll.addFirst(new hand(curr_hand.playerId));
            } else if (curr_hand.handType == "fist") {
                ll.addLast(new hand(curr_hand.playerId, "palm"));
            }
        } while (ll.size() > 1);

        fio.println(ll.getFirst().playerId); // print the "..." contents with newline at the end

        fio.close(); // important; always close at the end of the code
    }
}

class hand {
    public String playerId;
    public String handType;

    public hand(int id) {
        this.playerId = Integer.toString(id + 1);
        this.handType = "folded";
    }

    public hand(String id) {
        this.playerId = id;
        this.handType = "fist";
    }

    public hand(String id, String type) {
        this.playerId = id;
        this.handType = type;
    }

    public void setHandType(String type) {
        this.handType = type;
    }

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
