
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

            // int[] nodes = new int[numberOfDominoTiles];
            // IntegerPair[] edgeList = new IntegerPair[numberOfEdges];

            DisjointUnionSets sets = new DisjointUnionSets(numberOfDominoTiles);
            for (int j = 0; j < numberOfEdges; j++) {
                int x = fio.nextInt(); // read int
                int y = fio.nextInt(); // read int
                // edgeList[j] = new IntegerPair(x, y);
                // x will cause y to fall
                sets.union(x, y);
                // System.out.println(sets);
            }
            int count = 0;
            for (int j = 0; j < numberOfDominoTiles; j++) {
                // nodes[j] = 0;
                if (sets.collections[j] < 0) {
                    count++;
                }
            }
            // Print out number of disconnected graphs
            fio.println(count); // print the "..." contents with newline at the end
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

class DisjointUnionSets {
    int[] collections;

    // Constructor
    public DisjointUnionSets(int n) {
        n++;
        this.collections = new int[n];
        for (int i = 0; i < n; i++) {
            this.collections[i] = -1;
        }
        this.collections[0] = 0;
    }

    /**
     * Find the parent/root of the element
     * 
     * @param x
     * @return int
     */
    int findParentIndex(int x) {
        int parentIndex = collections[x];
        if (parentIndex < 0) {
            return x; // means i am my own parent
        }
        while (parentIndex > 0) {
            parentIndex = collections[parentIndex];
        }
        return parentIndex;
    }

    /**
     * Checks if both elements are in the same set
     * Not used because checking within the function itself is faster
     * 
     * @param p
     * @param q
     * @return boolean
     */
    boolean isSameSet(int p, int q) {
        return findParentIndex(p) == findParentIndex(q);
    }

    /**
     * Union the sets with element p & q
     * 
     * @param p
     * @param q
     */
    void union(int p, int q) {
        int p_parentIndex = findParentIndex(p);
        int p_parentValue = p_parentIndex < 0 ? -1 : collections[p_parentIndex];
        // System.out.println("p_ : " + p);
        // System.out.println("p_parentIndex: " + p_parentIndex);
        // System.out.println("p_parentValue: " + p_parentValue);
        int q_parentIndex = findParentIndex(q);
        int q_parentValue = q_parentIndex < 0 ? -1 : collections[q_parentIndex];
        // System.out.println("q_ : " + q);
        // System.out.println("q_parentIndex: " + q_parentIndex);
        // System.out.println("q_parentValue: " + q_parentValue);

        // if (p_parentIndex == q_parentIndex) {
        // // System.out.println("sameParent!");
        // return;
        // }

        if (p_parentValue < 0 && q_parentValue < 0) { // means both are roots
            collections[p] += collections[q]; // update the number of child nodes
            collections[q] = p; // set parent of q into p
        } else if (p_parentValue == q_parentValue) { // means both roots are same size
            collections[p_parentIndex] += collections[q_parentIndex]; // update the number of child nodes
            collections[q_parentIndex] = p_parentIndex; // set parent of q into p
            collections[q] = p_parentIndex;// does the tree collapse thing
        } else if (p_parentValue > q_parentValue) { // means p union is bigger than q
            collections[p_parentIndex] += collections[q_parentIndex]; // update the number of child nodes
            collections[q_parentIndex] = p_parentIndex; // set parent of q into p
            collections[q] = p_parentIndex;// does the tree collapse thing
        } else if (p_parentValue < q_parentValue) { // means q union is bigger than p
            collections[q_parentIndex] += collections[p_parentIndex]; // update the number of child nodes
            collections[p_parentIndex] = q_parentIndex; // set parent of q into p
            collections[p] = q_parentIndex;// does the tree collapse thing
        }
    }

    /**
     * Default toString of parent nodes
     * 
     * @return String
     */
    @Override
    public String toString() {
        return Arrays.toString(collections);
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
