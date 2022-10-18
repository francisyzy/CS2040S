
***REMOVED***
import java.io.*;
import java.util.*;

public class dominos {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int numOfTestCase = fio.nextInt(); // read int
        for (int i = 0; i < numOfTestCase; i++) {
            int numberOfDominoTiles = fio.nextInt(); // read int
            int numberOfLines = fio.nextInt(); // read int

            Triple[] edgeList = new Triple[numberOfLines];
            for (int j = 0; j < numberOfLines; j++) {
                int x = fio.nextInt(); // read int
                int y = fio.nextInt(); // read int
                // x will cause y to fall
            }
            // Print out number of disconnected graphs
            fio.println("..."); // print the "..." contents with newline at the end
        }

        fio.close(); // important; always close at the end of the code
    }
}

class Triple {
    private final int first;
    private final int second;
    private final int third;

    public Triple(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public int getThird() {
        return third;
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
