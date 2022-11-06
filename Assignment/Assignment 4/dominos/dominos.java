

import java.io.*;
import java.util.*;

public class dominos {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance
        int numOfTestCase = fio.nextInt(); // read int
        for (int i = 0; i < numOfTestCase; i++) {
            int numberOfDominoTiles = fio.nextInt(); // read int
            int numberOfEdges = fio.nextInt(); // read int

            boolean[] visited = new boolean[numberOfDominoTiles];
            boolean[] toposortVisited = new boolean[numberOfDominoTiles];
            Stack<Integer> topo = new Stack<>();
            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(numberOfDominoTiles);
            for (int j = 0; j < numberOfDominoTiles; j++) {
                visited[j] = false;
                toposortVisited[j] = false;
                adjList.add(new ArrayList<>());
            }

            for (int j = 0; j < numberOfEdges; j++) {
                int x = fio.nextInt() - 1; // read int
                int y = fio.nextInt() - 1; // read int
                adjList.get(x).add(y);
            }

            int count = 0;
            for (int j = 0; j < numberOfDominoTiles; j++) {
                if (!toposortVisited[j]) {
                    toposortVisited[j] = true;
                    toposort(j, toposortVisited, adjList, topo);
                }
            }
            while (!topo.empty()) {
                Integer toSearch = topo.pop();
                if (!visited[toSearch]) {
                    count++;
                    visited[toSearch] = true;
                    dfs(toSearch, visited, adjList);
                }
            }
            // Print out number of disconnected graphs
            fio.println(count);
        }

        fio.close(); // important; always close at the end of the code
    }

    /**
     * Recursively topologically sort the arrayList. Will add
     * it into a stack called topo
     * 
     * @param u
     * @param toposortVisited
     * @param adjList
     * @param topo
     */
    static void toposort(int u, boolean[] toposortVisited, ArrayList<ArrayList<Integer>> adjList,
            Stack<Integer> topo) {
        toposortVisited[u] = true; // mark u as visited
        for (Integer v : adjList.get(u)) {
            if (toposortVisited[v] == false) {// to avoid cycle
                toposort(v, toposortVisited, adjList, topo); // recursively visits v
            }
        }
        topo.add(u);
    }

    /**
     * DFS to visit every item and mark it in the array as visited
     * 
     * @param u
     * @param visited
     * @param adjList
     */
    static void dfs(int u, boolean[] visited, ArrayList<ArrayList<Integer>> adjList) {
        visited[u] = true; // mark u as visited
        for (Integer v : adjList.get(u)) {
            if (visited[v] == false) {// to avoid cycle
                dfs(v, visited, adjList); // recursively visits v
            }
        }
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
