
***REMOVED***
import java.io.*;
import java.util.*;

public class millionairemadness {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int M = fio.nextInt(); // read int
        int N = fio.nextInt(); // read int
        int[][] adjMatrix = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                adjMatrix[i][j] = fio.nextInt();
            }
        }
        ArrayList<ArrayList<IntegerPair>> adjList = adjToList_BFS(adjMatrix, M, N);
        // Calculate the number of times it needs to go up and whats the biggest bump?

        PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>();
        ArrayList<Boolean> taken = new ArrayList<Boolean>();
        taken.addAll(Collections.nCopies(M * N, false));
        process(0, adjList, taken, pq);
        // int mst_cost = 0;

        ArrayList<Integer> largestCost = new ArrayList<Integer>();

        while (!pq.isEmpty()) { // we will do this until all V vertices are taken (or E = V-1 edges are taken)
            IntegerPair front = pq.poll();

            if (!taken.get(front.y)) { // we have not connected this vertex yet
                largestCost.add(front.x);
                // mst_cost += front.x; // add the weight of this edge
                // System.out.println("Adding edge: (" + front.x + ", " + front.y + "), MST cost
                // now = " + mst_cost);
                process(front.y, adjList, taken, pq);
                // process(front.y);
                if (front.y == (M * N - 1)) { // reach the last vertex total -1 due to 0 index
                    break;
                }
            } else { // this vertex has been connected before via some other tree branch
                // System.out.println("Ignoring edge: (" + front.x + ", " + front.y + "), MST
                // cost now = " + mst_cost);
            }
        }
        // System.out.println(largestCost);
        Collections.sort(largestCost);
        Collections.reverse(largestCost);
        // System.out.println(largestCost);
        // System.out.println(adjList);
        if (largestCost.size() == 0) {
            largestCost.add(0);
        }
        fio.println(largestCost.get(0)); // print the "..." contents with newline at the end

        fio.close(); // important; always close at the end of the code
    }

    static ArrayList<ArrayList<IntegerPair>> adjToList_BFS(int[][] adjMatrix, int r, int c) {
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
                    // System.out.println("Check up");
                    updateAdjList(adjMatrix, adjList, x, y, check_x, check_y, adjListItem, countBase);
                }
                // Check Down
                check_x = x + 1;
                check_y = y;
                adjListItem = countBase + c;
                if (check_x < r) {
                    // System.out.println("Check down");
                    updateAdjList(adjMatrix, adjList, x, y, check_x, check_y, adjListItem, countBase);
                }
                // Check Left
                check_x = x;
                check_y = y - 1;
                adjListItem = countBase - 1;
                if (check_y >= 0) {
                    // System.out.println("Check left");
                    updateAdjList(adjMatrix, adjList, x, y, check_x, check_y, adjListItem, countBase);
                }
                // Check Right
                check_x = x;
                check_y = y + 1;
                adjListItem = countBase + 1;
                if (check_y < c) {
                    // System.out.println("Check right");
                    updateAdjList(adjMatrix, adjList, x, y, check_x, check_y, adjListItem, countBase);
                }
                countBase++;
            }
        }

        return adjList;
    }

    static void updateAdjList(int[][] adjMatrix,
            ArrayList<ArrayList<IntegerPair>> adjList,
            int x, int y, int check_x, int check_y, int adjListItem, int countBase) {
        // System.out.println(Arrays.deepToString(checked));
        // System.out.println(adjListItem);
        // System.out.println("check x: " + check_x);
        // System.out.println("check y: " + check_y);
        int checkDifference = adjMatrix[check_x][check_y] - adjMatrix[x][y];
        checkDifference = checkDifference < 0 ? 0 : checkDifference;
        // System.out.println(checkDifference);
        adjList.get(countBase).add(new IntegerPair(adjListItem, checkDifference));
    }

    static void process(int vtx, ArrayList<ArrayList<IntegerPair>> AdjList, ArrayList<Boolean> taken,
            PriorityQueue<IntegerPair> pq) {
        // System.out.println(">> At vertex " + vtx);
        taken.set(vtx, true);
        for (int j = 0; j < AdjList.get(vtx).size(); j++) {
            IntegerPair v = AdjList.get(vtx).get(j);
            if (!taken.get(v.x)) {
                pq.offer(new IntegerPair(v.y, v.x)); // we sort by weight then by adjacent vertex
                // System.out.println(">> Inserting (" + v.y + ", " + v.x + ") to priority
                // queue");
            } else {
                // System.out.println(">> Ignoring (" + v.y + ", " + v.x + ")");
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
