
***REMOVED***
import java.io.*;
import java.util.*;

public class islands3 {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int r = fio.nextInt(); // read int
        int c = fio.nextInt(); // read int
        char[][] grid = new char[r][c];
        boolean[][] visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String line = fio.nextLine(); // read an entire line
            for (int j = 0; j < c; j++) {
                // C = clouds
                // W = water
                // L = land
                grid[i][j] = line.charAt(i);
                if (line.charAt(i) == 'W') {
                    visited[i][j] = true;
                } else {
                    visited[i][j] = false;
                }
            }
        }

        int count = 0;
        Queue<IntegerPair> toSearch = new ArrayDeque<IntegerPair>();
        // toSearch.add(new IntegerPair(0, 0)); // search from 0 0

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // C = clouds
                // W = water
                // L = land
                if (grid[i][j] == 'L' && visited[i][j] == false) {
                    if (i > 0 && j > 0 && i + 1 < r && j + 1 < c) {
                        count++;
                        toSearch.add(new IntegerPair(i, j));
                        visited[i][j] = true;
                        BFS(toSearch, grid, visited);
                    }
                }
            }
        }

        fio.println(count);

        fio.close(); // important; always close at the end of the code
    }

    static void BFS(Queue<IntegerPair> toSearch, char[][] grid, boolean[][] visited) {

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
