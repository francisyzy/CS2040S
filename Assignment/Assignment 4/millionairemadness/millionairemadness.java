

import java.io.*;
import java.util.*;

public class millionairemadness {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int M = fio.nextInt(); // read int
        int N = fio.nextInt(); // read int
        int[][] grid = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = fio.nextInt();
            }
        }
        ArrayList<ArrayList<IntegerPair>> adjList = gridToAdjList(grid, M, N);

        PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>();
        ArrayList<Integer> largestCost = new ArrayList<Integer>();
        ArrayList<Boolean> taken = new ArrayList<Boolean>();
        taken.addAll(Collections.nCopies(M * N, false)); // set all of the paths as not taken yet

        // int mst_cost = 0;
        process(0, adjList, taken, pq);
        while (!pq.isEmpty()) { // we will do this until all V vertices are taken (or E = V-1 edges are taken)
            IntegerPair front = pq.poll();

            if (!taken.get(front.y)) { // we have not connected this vertex yet
                largestCost.add(front.x);
                // mst_cost += front.x; // add the weight of this edge
                process(front.y, adjList, taken, pq);
                if (front.y == (M * N - 1)) { // reach the last vertex total -1 due to 0 index
                    break;
                }
            }
        }
        Collections.sort(largestCost);
        Collections.reverse(largestCost);
        if (largestCost.size() == 0) {
            largestCost.add(0);
        }
        fio.println(largestCost.get(0)); // Print the largest ladder needed

        fio.close(); // important; always close at the end of the code
    }

    /**
     * With a grid, & its sizes. Convert it into an adjList
     * 
     * @param grid
     * @param r
     * @param c
     * @return ArrayList<ArrayList<IntegerPair>>
     */
    static ArrayList<ArrayList<IntegerPair>> gridToAdjList(int[][] grid, int r, int c) {
        int numberOfPilersOfGold = r * c;

        ArrayList<ArrayList<IntegerPair>> adjList = new ArrayList<ArrayList<IntegerPair>>();
        for (int i = 0; i < numberOfPilersOfGold; i++) {
            adjList.add(new ArrayList<IntegerPair>()); // store blank vector first
        }

        int countBase = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                final int x = i;
                final int y = j;
                int check_x = 0;
                int check_y = 0;
                int adjListItem = 0;

                // Check up
                check_x = x - 1;
                check_y = y;
                adjListItem = countBase - c;
                if (check_x >= 0) {
                    updateAdjList(grid, adjList, x, y, check_x, check_y, adjListItem, countBase);
                }
                // Check Down
                check_x = x + 1;
                check_y = y;
                adjListItem = countBase + c;
                if (check_x < r) {
                    updateAdjList(grid, adjList, x, y, check_x, check_y, adjListItem, countBase);
                }
                // Check Left
                check_x = x;
                check_y = y - 1;
                adjListItem = countBase - 1;
                if (check_y >= 0) {
                    updateAdjList(grid, adjList, x, y, check_x, check_y, adjListItem, countBase);
                }
                // Check Right
                check_x = x;
                check_y = y + 1;
                adjListItem = countBase + 1;
                if (check_y < c) {
                    updateAdjList(grid, adjList, x, y, check_x, check_y, adjListItem, countBase);
                }
                countBase++;
            }
        }

        return adjList;
    }

    /**
     * Helper function for the grid to AdjList. Gets the differences between
     * the pillars and add it into the list
     * 
     * @param grid
     * @param adjList
     * @param x
     * @param y
     * @param check_x
     * @param check_y
     * @param adjListItem
     * @param countBase
     */
    static void updateAdjList(int[][] grid,
            ArrayList<ArrayList<IntegerPair>> adjList,
            int x, int y, int check_x, int check_y, int adjListItem, int countBase) {
        int checkDifference = grid[check_x][check_y] - grid[x][y];
        checkDifference = checkDifference < 0 ? 0 : checkDifference;
        adjList.get(countBase).add(new IntegerPair(adjListItem, checkDifference));
    }

    /**
     * Process the current vertex and add more items to be process (if applicable)
     * 
     * @param vtx
     * @param AdjList
     * @param taken
     * @param pq
     */
    static void process(int vtx, ArrayList<ArrayList<IntegerPair>> AdjList, ArrayList<Boolean> taken,
            PriorityQueue<IntegerPair> pq) {
        taken.set(vtx, true);
        for (int j = 0; j < AdjList.get(vtx).size(); j++) {
            IntegerPair v = AdjList.get(vtx).get(j);
            if (!taken.get(v.x)) {
                pq.offer(new IntegerPair(v.y, v.x)); // we sort by weight then by adjacent vertex
            }
        }

    }
}

class IntegerPair implements Comparable<IntegerPair> {
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
    public int compareTo(IntegerPair in) {
        if (this.x != in.x) {
            return this.x - in.x;
        } else {
            return this.y - in.y;
        }
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
