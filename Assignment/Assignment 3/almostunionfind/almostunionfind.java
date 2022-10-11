
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
                int parent = disjointUnionSets.findParentIndex(p);
                fio.print(disjointUnionSets.getSize(parent));
                fio.print(' ');
                fio.println(disjointUnionSets.findSum(parent));
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

class Node {
    public int index;
    public Node parent;

    public Node(int i) {
        this.index = i;
    }

    public Node(int i, Node parent) {
        this.index = i;
        this.parent = parent;
    }

    void setParent(Node parent) {
        this.parent = parent;
    }

    boolean isRoot() {
        return this.parent == null;
    }

    int getParentId() {
        return this.parent.index;
    }

    @Override
    public String toString() {
        return "ID: " + index + " PID: " + parent.toString();
    }
}

class DisjointUnionSets {
    int[] sum, movement, size;
    Node[] nodes;

    // Constructor
    public DisjointUnionSets(int n) {
        n++;
        this.sum = new int[n];
        this.movement = new int[n];
        this.size = new int[n];
        this.nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            this.nodes[i] = new Node(i);
            this.movement[i] = i;
            this.sum[i] = i;
            this.size[i] = 1;
        }
    }

    int findParentSum(int x) {
        int parentIndex = findParentIndex(x);
        return sum[parentIndex];
    }

    int findSum(int x) {
        return sum[x];
    }

    int getSize(int x) {
        return size[x];
    }

    boolean isRoot(int x) {
        return nodes[x].isRoot();
    }

    int findParentIndex(int x) {
        if (isRoot(x)) { // if I am parent, my own index will be returned
            return x;
        } else if (movement[x] == x) {
            Node parent = nodes[x].parent;
            while (parent.parent != null) {
                nodes[x].parent = parent.parent;
            }
            return nodes[x].parent.index;
            // return findParentIndex(nodes[x].getParentId());
        } else {
            return movement[x];
        }
    }

    void union(int p, int q) {
        if (isRoot(p) && isRoot(q)) { // means both are roots
            // Set P to be the parent now
            nodes[q].setParent(nodes[p]);
            size[p]++;
            sum[p] += q;
            return;
        }
        int p_parentIndex = findParentIndex(p);
        int q_parentIndex = findParentIndex(q);
        if (p_parentIndex == q_parentIndex) {
            System.out.println(p_parentIndex);
            System.out.println(q_parentIndex);
            return;
        }
        int p_parentSize = getSize(p_parentIndex);
        int q_parentSize = getSize(q_parentIndex);
        if (p_parentSize < q_parentSize) { // means q union is bigger than p
            size[q_parentIndex] += size[p_parentIndex]; // update the number of child nodes
            sum[q_parentIndex] += sum[p_parentIndex];
            nodes[q_parentIndex].setParent(nodes[p_parentIndex]);
            nodes[q].setParent(nodes[p_parentIndex]);
        } else { // means p union is bigger than q
            // or both roots are same size
            size[p_parentIndex] += size[q_parentIndex]; // update the number of child nodes
            sum[p_parentIndex] += sum[q_parentIndex];
            nodes[p_parentIndex].setParent(nodes[q_parentIndex]);
            nodes[p].setParent(nodes[q_parentIndex]);
        }
    }

    void move(int p, int q) {
        int p_parentIndex = findParentIndex(p);
        int q_parentIndex = findParentIndex(q);
        if (p_parentIndex == q_parentIndex) {
            return;// both parents same. Do nothing
        } else {
            if (isRoot(p) && isRoot(q)) { // means p & q is root
                union(p, q);
                return;
            }
            movement[p] = q_parentIndex;
            size[p_parentIndex]--;
            sum[p_parentIndex] -= p;

            size[q_parentIndex]++;
            sum[q_parentIndex] += p;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(nodes);
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
