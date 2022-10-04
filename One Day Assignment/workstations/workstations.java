
***REMOVED***
import java.io.*;
import java.util.*;

public class workstations {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int n = fio.nextInt(); // read int
        int m = fio.nextInt(); // read int

        PriorityQueue<researcher> researchers = new PriorityQueue<researcher>();
        PriorityQueue<Integer> spareComputer = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) {
            int a = fio.nextInt(); // read int
            int s = fio.nextInt(); // read int
            researcher researcher = new researcher(a, s);
            researchers.add(researcher);
        }
        // int unlocks = 0;
        int saved = 0;

        for (int i = 0; i < n; i++) {
            // System.out.println(spareComputer);
            researcher researcher = researchers.poll();
            // System.out.println("Researcher No: " + researcher);
            if (spareComputer.isEmpty()) {
                // System.out.println("no spare computers");
                // unlocks++;
                spareComputer.add(researcher.leavesAfter());
            } else {
                int computerAvailabilityChecker = spareComputer.peek();
                boolean pcNotInUse = computerAvailabilityChecker <= researcher.arrives;
                boolean pcNotExpired = researcher.arrives <= (computerAvailabilityChecker + m);
                // if pc is expired, remove it and check availability again
                while (!pcNotExpired && !spareComputer.isEmpty()) {
                    spareComputer.poll();
                    //Catch run time error
                    if (!spareComputer.isEmpty()) {
                        computerAvailabilityChecker = spareComputer.peek();
                        pcNotInUse = computerAvailabilityChecker <= researcher.arrives;
                        pcNotExpired = researcher.arrives <= (computerAvailabilityChecker + m);
                    }
                }

                // System.out.println("spare computers");
                // System.out.println(researcher.arrives);
                // System.out.println("pcNotInUse " + pcNotInUse);
                // System.out.println("pcNotExpired " + pcNotExpired);
                // System.out.println(computerAvailable + m);
                if (pcNotExpired && pcNotInUse) {
                    // Saved an unlock
                    // System.out.println("Never unlock another PC");
                    // use the pc
                    saved++;
                    spareComputer.poll();
                    // add the computer back in the pile of available computers after use
                    spareComputer.add(researcher.leavesAfter());
                } else {
                    // System.out.println("Unlocked another PC");
                    // unlocks++;
                    spareComputer.add(researcher.leavesAfter());
                }
            }
        }
        // fio.println(n - unlocks);
        fio.println(saved);

        fio.close(); // important; always close at the end of the code
    }
}

class researcher implements Comparable<researcher> {
    int arrives;
    int stays;

    researcher(int a, int s) {
        this.arrives = a;
        this.stays = s;
    }

    int leavesAfter() {
        return arrives + stays;
    }

    int locksAt(int lockMins) {
        return leavesAfter() + lockMins;
    }

    @Override
    public int compareTo(researcher incoming) {
        return this.arrives - incoming.arrives;
    }

    @Override
    public String toString() {
        return Integer.toString(arrives);
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
