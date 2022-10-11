
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
            // System.out.println("i: " + i);
            // System.out.println("whole display: " + Arrays.toString(display));
            // System.out.println("whole array : " + disjointUnionSets);
            // System.out.println("whole size: " + disjointUnionSets.sizeString());
            // System.out.println("whole sum : " + disjointUnionSets.sumString());
            int opCode = fio.nextInt(); // read int
            int p = fio.nextInt(); // read int
            if (opCode == 3) {
                fio.println(disjointUnionSets.print(p));
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

    long findParentSum(int x) {
        int parentIndex = findParentIndex(x);
        return sum[parentIndex];
    }

    long findSum(int x) {
        return sum[x];
    }

    int findParentIndex(int x) {
        int parentIndex = movement[x];
        while (parentIndex != parent[parentIndex]) {
            parentIndex = parent[parentIndex];
        }
        return parentIndex;
    }

    int findParentSize(int x) {
        return size[findParentIndex(x)];
    }

    int getSize(int x) {
        return size[x];
    }

    int getSize(int x) {
        return size[x];
    }

    boolean isRoot(int x) {
        return nodes[x].isRoot();
    }

    void union(int p, int q) {
        int p_parentIndex = findParentIndex(p);
        int q_parentIndex = findParentIndex(q);
        // System.out.println(p_parentIndex);
        // System.out.println(q_parentIndex);
        if (p_parentIndex != q_parentIndex) {
            // System.out.println("sameParent!");
            parent[p_parentIndex] = q_parentIndex;
            size[q_parentIndex] = size[p_parentIndex] + size[q_parentIndex];
            size[p_parentIndex] = 0;
            sum[q_parentIndex] = sum[p_parentIndex] + sum[q_parentIndex];
            sum[p_parentIndex] = 0;
        }
        // if (p_parentSize < q_parentSize) { // means q union is bigger than p
        // parent[q_parentIndex] += parent[p_parentIndex]; // update the number of child
        // nodes
        // parent[p_parentIndex] = q_parentIndex; // set parent of p's as q's parent
        // parent[p] = q_parentIndex; // set p's parent as q's parent
        // sum[q_parentIndex] += p;
        // } else { // means p union is bigger than q
        // // or both roots are same size
        // parent[p_parentIndex] += parent[q_parentIndex]; // update the number of child
        // nodes
        // parent[q_parentIndex] = p_parentIndex; // set parent of q as p's parent
        // parent[q] = p_parentIndex; // set q's parent as p's parent
        // sum[p_parentIndex] += q;
        // }

    }

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

    // void print(int p) {
    // nt = this.findParentIndex(p);
    // ut.print(this.getSize(parent));
    // ut.print(' ');
    // ut.println(this.findSum(parent));
    // }
    String print(int p) {
        int parent = this.findParentIndex(p);
        return Integer.toString(this.getSize(parent)) + " " + Long.toString(this.findSum(parent));
    }

    @Override
    public String toString() {
        return Arrays.toString(nodes);
    }

    public String sumString() {
        return Arrays.toString(sum);
    }

    public String sizeString() {
        return Arrays.toString(size);
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
