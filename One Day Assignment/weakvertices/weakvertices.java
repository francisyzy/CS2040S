
***REMOVED***
import java.io.*;
import java.util.*;

public class weakvertices {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int numOfVertices = fio.nextInt(); // read int
        while (numOfVertices != -1) {
            int[][] adjMatrix = new int[numOfVertices][numOfVertices];
            int[] count = new int[numOfVertices];
            for (int i = 0; i < numOfVertices; i++) {
                for (int j = 0; j < numOfVertices; j++) {
                    int vertex = fio.nextInt();
                    adjMatrix[i][j] = vertex;
                    if (vertex == 1) {
                        count[i]++;
                    }
                }
            }
            
            for (int i = 0; i < numOfVertices; i++) {
                if (count[i] == 1) {
                    // Weak!
                }
                if (adjMatrix[i][i] == 1) {
                }
                
            }
            fio.println("..."); // print the "..." contents with newline at the end
            numOfVertices = fio.nextInt();
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
