
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
            boolean[] triangle = new boolean[numOfVertices];
            for (int i = 0; i < numOfVertices; i++) {
                triangle[i] = false;
                if (count[i] == 1) { // skip checking if the guy only have one connection because no way u can form a
                                     // triangle using one line
                    continue;
                }
                for (int j = 0; j < numOfVertices; j++) {
                    for (int k = 0; k < numOfVertices; k++) {
                        if (i != j && i != k && j != k) {
                            // If they are the same numbers, they will check if [1][1] == 1 which is never
                            // going to be true, save compute
                            if (edgeExist(adjMatrix, i, j) // if 3 edges exist, means there is a triangle
                                    && edgeExist(adjMatrix, i, k)
                                    && edgeExist(adjMatrix, j, k)) {
                                triangle[i] = true;
                            }
                        }
                    }
                }

            }

            // Prints out the vectors that have no triangles found
            for (int i = 0; i < triangle.length; i++) {
                if (!triangle[i]) {
                    fio.print(i);
                    fio.print(" ");
                }

            }
            fio.println(); // new line to signify end of case
            numOfVertices = fio.nextInt(); // get input for next test case
        }

        fio.close(); // important; always close at the end of the code
    }

    private static boolean edgeExist(int[][] adjMatrix, int x, int y) {
        if (adjMatrix[x][y] == 1 && adjMatrix[y][x] == 1) {
            return true;
        }
        return false;
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
