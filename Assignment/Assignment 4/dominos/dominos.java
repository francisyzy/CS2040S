
***REMOVED***
import java.io.*;
import java.util.*;

public class dominos {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int numOfTestCase = fio.nextInt(); // read int
        for (int i = 0; i < numOfTestCase; i++) {
            int numberOfDominoTiles = fio.nextInt(); // read int
            int numberOfEdges = fio.nextInt(); // read int

            int[] nodes = new int[numberOfDominoTiles];
            IntegerPair[] edgeList = new IntegerPair[numberOfEdges];
            for (int j = 0; j < numberOfEdges; j++) {
                int x = fio.nextInt(); // read int
                int y = fio.nextInt(); // read int
                edgeList[j] = new IntegerPair(x, y);
                // x will cause y to fall
            }
            int count = 0;
            for (int j = 0; j < numberOfDominoTiles; j++) {
                nodes[j] = 0;
            }
            // Print out number of disconnected graphs
            fio.println("..."); // print the "..." contents with newline at the end
        }

        fio.close(); // important; always close at the end of the code
    }
}

class IntegerPair {
    int x, y;

    IntegerPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object in) {
        if (this != in) {
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return x + " " + y;
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
