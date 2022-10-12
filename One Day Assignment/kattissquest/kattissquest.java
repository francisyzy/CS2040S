
***REMOVED***
import java.io.*;
import java.util.*;

public class kattissquest {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int n = fio.nextInt(); // read int
        TreeMap<Integer, PriorityQueue<Integer>> quests = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            // System.out.println(quests);
            String command = fio.next(); // read a single token
            if (command.equals("add")) {
                int e = fio.nextInt(); // read int
                int g = fio.nextInt(); // read int
                if (quests.containsKey(e)) {
                    PriorityQueue<Integer> gold = quests.get(e);
                    gold.offer(g);
                    quests.put(e, gold);
                } else {
                    PriorityQueue<Integer> gold = new PriorityQueue<Integer>(Collections.reverseOrder());
                    gold.offer(g);
                    quests.put(e, gold);
                }
            } else {
                int availableEnergy = fio.nextInt(); // read int
                long goldEarned = 0;
                Map.Entry<Integer, PriorityQueue<Integer>> foundQuest = quests.floorEntry(availableEnergy);
                while (foundQuest != null) {
                    PriorityQueue<Integer> goldValue = foundQuest.getValue();
                    if (goldValue.peek() != null) {
                        availableEnergy -= foundQuest.getKey();
                        goldEarned += goldValue.poll();
                    } else {
                        quests.remove(foundQuest.getKey());
                    }
                    foundQuest = quests.floorEntry(availableEnergy);
                }
                fio.println(goldEarned);
            }

        }
        fio.close(); // important; always close at the end of the code
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