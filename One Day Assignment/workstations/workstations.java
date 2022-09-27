
***REMOVED***
import java.io.*;
import java.util.*;

public class workstations {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int n = fio.nextInt(); // read int
        int m = fio.nextInt(); // read int

        ArrayList<researcher> researchers = new ArrayList<researcher>();
        for (int i = 0; i < n; i++) {
            int a = fio.nextInt(); // read int
            int s = fio.nextInt(); // read int
            researchers.add(new researcher(a, s));
        }
        int unlocks = 1;
        int prev = 0;
        for (int i = 1; i < n; i++) {
            researcher researcher = researchers.get(i);
            if (researcher.arrives < researchers.get(prev).leavesAfter()) {
                unlocks++;
            } else {
                prev++;
            }
        }
        fio.println(unlocks);

        fio.close(); // important; always close at the end of the code
    }
}

class researcher {
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
