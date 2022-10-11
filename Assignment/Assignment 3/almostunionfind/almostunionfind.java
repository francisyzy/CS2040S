***REMOVED***
import java.io.*;
import java.util.*;

public class almostunionfind {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int n = fio.nextInt(); // read int
        int m = fio.nextInt(); // read int

        DisjointUnionSets disjointUnionSets = new DisjointUnionSets(n);
        // int[] display = new int[n + 1];
        // Arrays.setAll(display, i -> i);

        for (int i = 0; i < m; i++) {
            // System.out.println("i: " + i);
            // System.out.println("whole display: " + Arrays.toString(display));
            // System.out.println("whole array : " + disjointUnionSets);
            // System.out.println("whole sum : " + disjointUnionSets.sumString());
            int opCode = fio.nextInt(); // read int
            int p = fio.nextInt(); // read int
            if (opCode == 3) {
                // System.out.println(disjointUnionSets.findParentsum(p));
                // System.out.println(parent[p_parentIndex]);
                fio.print(disjointUnionSets.findParentSize(p));
                fio.print(' ');
                fio.println(disjointUnionSets.findSum(p));
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
    int[] sum, parent;

    // Constructor
    public DisjointUnionSets(int n) {
        n++;
        sum = new int[n];
        parent = new int[n];
        Arrays.setAll(this.parent, i -> -1);
        Arrays.setAll(this.sum, i -> i);
        parent[0] = 0;
    }

    int findSum(int x) {
        int parentIndex = findParentIndex(x);
        return sum[parentIndex];
    }

    int findParentIndex(int x) {
        if (isRoot(x)) { // if I am parent, my own index will be returned
            return x;
        } else {
            int parentIndex = parent[x];
            int oneBefore = parentIndex;
            while (parentIndex > 0) {
                oneBefore = parentIndex;
                parentIndex = parent[parentIndex];
            }
            parent[x] = oneBefore;
            return oneBefore;

            // // Recursively find the representative.
            // int result = findParentIndex(parent[x]);

            // // We cache the result by moving i’s node
            // // directly under the representative of this set
            // parent[x] = result;

            // // And then we return the result
            // return result;
        }
    }

    int findParentSize(int x) {
        return Math.abs(parent[findParentIndex(x)]);
    }

    boolean isRoot(int x) {
        return parent[x] < 0;
    }

    void remove(int x) {
        if (isRoot(x)) {
            return;
        }
        int parentIndex = findParentIndex(x);
        parent[parentIndex]++; // update parent
        sum[parentIndex] -= x; // update parent
        parent[x] = -1; // set itself as root
        sum[x] = x; // reset its own sum
    }

    void union(int p, int q) {
        int p_parentSize = findParentSize(p);
        int q_parentSize = findParentSize(q);
        int p_parentIndex = findParentIndex(p);
        int q_parentIndex = findParentIndex(q);
        if (isRoot(p) && isRoot(q)) { // means both are roots
            // Set P to be the parent now
            parent[p] += parent[q]; // update the number of child nodes
            parent[q] = p; // set parent of q into p
            sum[p] += q;
        } else if (p_parentSize == q_parentSize) { // means both roots are same size
            parent[p_parentIndex] += parent[q_parentIndex]; // update the number of child nodes
            parent[q_parentIndex] = p_parentIndex; // set parent of q into p
            sum[p_parentIndex] += q;
        } else if (p_parentSize > q_parentSize) { // means p union is bigger than q
            parent[p_parentIndex] += parent[q_parentIndex]; // update the number of child nodes
            parent[q_parentIndex] = p_parentIndex; // set parent of q into p
            sum[p_parentIndex] += q;
        } else if (p_parentSize < q_parentSize) { // means q union is bigger than p
            parent[q_parentIndex] += parent[p_parentIndex]; // update the number of child nodes
            parent[p_parentIndex] = q_parentIndex; // set parent of q into p
            sum[q_parentIndex] += p;
        }
    }

    void move(int p, int q) {
        if (findParentIndex(p) == findParentIndex(q)) {
            return;// both parents same. Do nothing
        } else {
            if (isRoot(p) && isRoot(q)) { // means p & q is root
                union(p, q);
                return;
            }

            remove(p);
            union(q, p); // add both tgt
            return;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(parent);
    }

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
