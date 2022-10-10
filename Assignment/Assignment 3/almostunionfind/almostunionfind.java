***REMOVED***
import java.io.*;
import java.util.*;

public class almostunionfind {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int n = fio.nextInt(); // read int
        int m = fio.nextInt(); // read int

        DisjointUnionSets disjointUnionSets = new DisjointUnionSets(n);
        int[] display = new int[n + 1];
        Arrays.setAll(display, i -> i);

        for (int i = 0; i < m; i++) {
            System.out.println("i: " + i);
            // System.out.println("whole display: " + Arrays.toString(display));
            // System.out.println("whole array  : " + disjointUnionSets);
            // System.out.println("whole rank   : " + disjointUnionSets.rankString());
            int opCode = fio.nextInt(); // read int
            int p = fio.nextInt(); // read int
            if (opCode == 3) {
                // System.out.println(disjointUnionSets.findParentRank(p));
                // System.out.println(collections[p_parentIndex]);
                // fio.println(collections[p_parentIndex]); // print the "..." contents with
                // newline at the end
            } else {
                int q = fio.nextInt(); // read int
                if (opCode == 1) { // union
                    disjointUnionSets.union(p, q);
                }
                if (opCode == 2) { // move
                    disjointUnionSets.move(p, q);
                }
            }
        }

        fio.close(); // important; always close at the end of the code
    }
}

class DisjointUnionSets {
    int[] rank, parent;
    int n;

    // Constructor
    public DisjointUnionSets(int n) {
        n++;
        rank = new int[n];
        parent = new int[n];
        Arrays.setAll(this.parent, i -> i);
        this.n = n;
    }

    int findSize(int x) {

        return 0;
    }

    // Returns representative of x's set
    int findParent(int x) {
        // Finds the representative of the set
        // that x is an element of
        if (parent[x] != x) {
            // if x is not the parent of itself
            // Then x is not the representative of
            // his set,
            parent[x] = findParent(parent[x]);

            // so we recursively call Find on its parent
            // and move i's node directly under the
            // representative of this set
        }

        return parent[x];
    }

    void move(int x, int y) {
        // Find representatives of two sets
        int x_parent = findParent(x), y_parent = findParent(y);

        // Elements are in the same set, no need
        // to unite anything.
        // System.out.println(x_parent);
        // System.out.println(y_parent);
        if (x_parent == y_parent) {
            return;
        }
        // Different set, need to move set x into set y.
        else {
            parent[x] = y;
        }

    }

    // Unites the set that includes x and the set
    // that includes x
    void union(int x, int y) {
        // Find representatives of two sets
        int x_parent = findParent(x), y_parent = findParent(y);

        // Elements are in the same set, no need
        // to unite anything.
        if (x_parent == y_parent) {
            return;
        }

        // If x's rank is less than y's rank
        if (rank[x_parent] < rank[y_parent]) {
            // Then move x under y so that depth
            // of tree remains less
            parent[x_parent] = y_parent;
        }

        // Else if y's rank is less than x's rank
        else if (rank[y_parent] < rank[x_parent]) {
            // Then move y under x so that depth of
            // tree remains less
            parent[y_parent] = x_parent;
        }

        else // if ranks are the same
        {
            // Then move y under x (doesn't matter
            // which one goes where)
            parent[y_parent] = x_parent;

            // And increment the the result tree's
            // rank by 1
            rank[x_parent] = rank[x_parent] + 1;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(parent);
    }

    public String rankString() {
        return Arrays.toString(rank);
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
