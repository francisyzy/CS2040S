
import java.io.*;
import java.util.*;

public class almostunionfind {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int n = fio.nextInt(); // read int
        while (true) {
            int m = fio.nextInt(); // read int
            DisjointUnionSets disjointUnionSets = new DisjointUnionSets(n);
            for (int i = 0; i < m; i++) {
                int opCode = fio.nextInt(); // read int
                int p = fio.nextInt(); // read int
                if (opCode == 3) {
                    disjointUnionSets.print(p, fio);
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
            try {
                n = fio.nextInt(); // read next test case
            } catch (Exception e) {
                // No more test cases
                break;
            }
        }
        fio.close(); // important; always close at the end of the code
    }
}

class DisjointUnionSets {
    int[] parent, movement, size;
    long[] sum;

    // Constructor
    public DisjointUnionSets(int n) {
        n++;
        this.sum = new long[n];
        this.movement = new int[n];
        this.size = new int[n];
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.movement[i] = i;
            this.sum[i] = i;
            this.size[i] = 1;
        }
        this.sum[0] = 0;
        this.size[0] = 0;
    }

    /**
     * Find the set's sum
     * 
     * @param x
     * @return long
     */
    long findParentSum(int x) {
        int parentIndex = findParentIndex(x);
        return sum[parentIndex];
    }

    /**
     * Find the sum of the represented node
     * 
     * @param x
     * @return long
     */
    long findSum(int x) {
        return sum[x];
    }

    /**
     * Find the parent/root of the element
     * 
     * @param x
     * @return int
     */
    int findParentIndex(int x) {
        int parentIndex = movement[x];
        while (parentIndex != parent[parentIndex]) {
            parentIndex = parent[parentIndex];
        }
        return parentIndex;
    }

    /**
     * Get the size of the set that element x belongs in
     * 
     * @param x
     * @return int
     */
    int findParentSize(int x) {
        return size[findParentIndex(x)];
    }

    /**
     * Get the size of the element (should be root then get size is more better)
     * 
     * @param x
     * @return int
     */
    int getSize(int x) {
        return size[x];
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
        int q_parentIndex = findParentIndex(q);
        if (p_parentIndex == q_parentIndex) {
            // System.out.println("sameParent!");
            return;
        }
        int p_parentSize = getSize(p_parentIndex);
        int q_parentSize = getSize(q_parentIndex);
        if (p_parentSize < q_parentSize) { // means q union is bigger than p
            size[q_parentIndex] += size[p_parentIndex]; // update the number of child nodes
            sum[q_parentIndex] += sum[p_parentIndex]; // update sum
            parent[p_parentIndex] = q_parentIndex; // set parent of p's as q's parent
        } else { // means p union is bigger than q
            // or both roots are same size
            size[p_parentIndex] += size[q_parentIndex]; // update the number of child nodes
            sum[p_parentIndex] += sum[q_parentIndex]; // update sum
            parent[q_parentIndex] = p_parentIndex; // set parent of q as p's parent
        }

    }

    /**
     * Move element p into the set containing q
     * 
     * @param p
     * @param q
     */
    void move(int p, int q) {
        int p_parentIndex = findParentIndex(p);
        int q_parentIndex = findParentIndex(q);
        if (p_parentIndex != q_parentIndex) {
            movement[p] = q_parentIndex;
            size[p_parentIndex]--;
            sum[p_parentIndex] -= p;

            size[q_parentIndex]++;
            sum[q_parentIndex] += p;
        }
    }

    /**
     * Print the size and sum of the set
     * 
     * @param p
     * @param fio
     */
    void print(int p, FastIO fio) {
        int parent = this.findParentIndex(p);
        fio.print(Integer.toString(this.getSize(parent)));
        fio.print(" ");
        fio.println(Long.toString(this.findSum(parent)));
    }

    /**
     * Default toString of parent nodes
     * 
     * @return String
     */
    @Override
    public String toString() {
        return Arrays.toString(parent);
    }

    /**
     * Returns sum array string
     * 
     * @return String
     */
    public String sumString() {
        return Arrays.toString(sum);
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
