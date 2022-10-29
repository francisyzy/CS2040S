
***REMOVED***
import java.io.*;
import java.util.*;

public class islands3 {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int r = fio.nextInt(); // read int
        while (r != 0) {

            int c = fio.nextInt(); // read int
            char[][] grid = new char[r][c];
            boolean[][] visited = new boolean[r][c];
            for (int i = 0; i < r; i++) {
                String line = fio.nextLine(); // read an entire line
                for (int j = 0; j < c; j++) {
                    // C = clouds
                    // W = water
                    // L = land
                    grid[i][j] = line.charAt(j);
                }
            }

            int count = 0;
            Queue<IntegerPair> toSearch = new ArrayDeque<IntegerPair>();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    // C = clouds
                    // W = water
                    // L = land
                    if (grid[i][j] == 'L' && visited[i][j] == false) {
                        count++;
                        toSearch.add(new IntegerPair(i, j));
                        BFS(toSearch, grid, visited, r, c);
                    }
                }
            }

            fio.println(count);
            try {
                r = fio.nextInt(); // read int
            } catch (Exception e) {
                break;
            }
        }
        fio.close(); // important; always close at the end of the code
    }

    static void BFS(Queue<IntegerPair> toSearch, char[][] grid, boolean[][] visited, int r, int c) {
        while (!toSearch.isEmpty()) {
            IntegerPair search = toSearch.poll();
            int x = search.x;
            int y = search.y;
            // Check up
            visited[x][y] = true;
            if (search.x - 1 >= 0) {
                x = search.x - 1;
                updateSearch(toSearch, grid, visited, x, y);
            }
            x = search.x; //reset x value
            // Check Down
            if (search.x + 1 < r) {
                x = search.x + 1;
                updateSearch(toSearch, grid, visited, x, y);
            }
            x = search.x; //reset x value
            // Check Left
            if (search.y - 1 >= 0) {
                y = search.y - 1;
                updateSearch(toSearch, grid, visited, x, y);
            }
            y = search.y; //reset y value
            // Check Right
            if (search.y + 1 < c) {
                y = search.y + 1;
                updateSearch(toSearch, grid, visited, x, y);
            }
            y = search.y; //reset y value
        }
    }

    static void updateSearch(Queue<IntegerPair> toSearch, char[][] grid, boolean[][] visited, int x, int y) {
        if (visited[x][y] == false) {
            if (grid[x][y] == 'L' || grid[x][y] == 'C') {
                visited[x][y] = true;
                toSearch.add(new IntegerPair(x, y));
            }
        }
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

// PSVM
//     FastIO fio = new FastIO(); // create new instance
//     int r = fio.nextInt(); // read int
//     while (r != 0)
//         int c = fio.nextInt(); // read int
//         char[][] grid = new char[r][c]
//         boolean[][] visited = new boolean[r][c]
//         for (int i = 0; i < r; i++)
//             String line = fio.nextLine(); // read an entire line
//             for (int j = 0; j < c; j++)
//                 // C = clouds
//                 // W = water
//                 // L = land
//                 grid[i][j] = line.charAt(j)

//         int count = 0
//         Queue<IntegerPair> toSearch = new ArrayDeque<IntegerPair>()
//         for (int i = 0; i < r; i++)
//             for (int j = 0; j < c; j++)
//                 // C = clouds
//                 // W = water
//                 // L = land
//                 if (grid[i][j] == 'L' && visited[i][j] == false)
//                     count++
//                     toSearch.add(new IntegerPair(i, j))
//                     BFS(toSearch, grid, visited, r, c)
                
            
        

//         fio.println(count)
//         try
//             r = fio.nextInt(); // read int
//          catch (Exception e)
//             break
        
    
//     fio.close(); // important; always close at the end of the code


// static void BFS(Queue<IntegerPair> toSearch, char[][] grid, boolean[][] visited, int r, int c)
//     while (!toSearch.isEmpty())
//         IntegerPair search = toSearch.poll()
//         // Check up
//         int x = search.x
//         int y = search.y
//         visited[x][y] = true
//         if (search.x - 1 >= 0)
//             x = search.x - 1
//             updateSearch(toSearch, grid, visited, x, y)
        
//         x = search.x; //reset x value
//         // Check Down
//         if (search.x + 1 < r)
//             x = search.x + 1
//             updateSearch(toSearch, grid, visited, x, y)
        
//         x = search.x; //reset x value
//         // Check Left
//         if (search.y - 1 >= 0)
//             y = search.y - 1
//             updateSearch(toSearch, grid, visited, x, y)
        
//         y = search.y; //reset y value
//         // Check Right
//         if (search.y + 1 < c)
//             y = search.y + 1
//             updateSearch(toSearch, grid, visited, x, y)
        
//         y = search.y; //reset y value
    


// static void updateSearch(Queue<IntegerPair> toSearch, char[][] grid, boolean[][] visited, int x, int y)
//     if (visited[x][y] == false)
//         if (grid[x][y] == 'L' || grid[x][y] == 'C')
//             visited[x][y] = true
//             toSearch.add(new IntegerPair(x, y))

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
