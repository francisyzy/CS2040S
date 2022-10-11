
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
                // System.out.println(disjointUnionSets.findParentsum(p));
                // System.out.println(parent[p_parentIndex]);
                disjointUnionSets.print(p, fio);
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
        if (this.parent == null) { // If I have no parent, I am my own parent
            return this.index;
        }
        return this.parent.index;
    }

    @Override
    public String toString() {
        if (this.parent == null) {
            return "ID: " + index + " PID: noParent";
        }
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
        this.sum[0] = 0;
        this.size[0] = 0;
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
        // System.out.println(x);
        // System.out.println(isRoot(x));
        if (movement[x] == x) {
            if (isRoot(x)) { // if I am parent, my own index will be returned
                return x;
            }

            Node curr = nodes[x];
            boolean amRoot = nodes[x].isRoot();
            while (!amRoot) {// while i am not root
                curr = curr.parent; // check if parent is root
                amRoot = curr.isRoot();
            }
            nodes[x].parent = curr;
            // System.out.println(curr);
            return nodes[x].getParentId();

            // int parentId = nodes[x].getParentId();
            // int oneBefore = 0;
            // // int count = 0;
            // while (parentId != oneBefore) {
            // // while (parentIndex > 0 && count < 10) {
            // System.out.println("x" + x);
            // System.out.println("pid" + parentId);
            // // count++;
            // parentId = nodes[parentId].getParentId();
            // oneBefore = parentId;
            // }
            // // parent[x] = oneBefore;
            // return oneBefore;
            // nodes[x].index = findParentIndex(nodes[x].index);
            // return nodes[x].parent.index;
        } else {
            return movement[x];
        }
    }

    void union(int p, int q) {
        int p_parentIndex = findParentIndex(p);
        int q_parentIndex = findParentIndex(q);
        if (p_parentIndex == q_parentIndex) {
            // System.out.println(p_parentIndex);
            // System.out.println(q_parentIndex);
            // System.out.println("Break a stpid recurrsive");
            return;
        }
        // if (isRoot(p) && isRoot(q)) { // means both are roots
        // // Set P to be the parent now
        // nodes[q].setParent(nodes[p]);
        // size[p]++;
        // sum[p] += q;
        // return;
        // }
        int p_parentSize = getSize(p_parentIndex);
        int q_parentSize = getSize(q_parentIndex);
        // System.out.println("p_size" + p_parentSize);
        // System.out.println("q_size" + q_parentSize);
        if (p_parentSize < q_parentSize) { // means q union is bigger than p
            // q absorbs p nodes
            size[q_parentIndex] += size[p_parentIndex]; // update the number of child nodes
            sum[q_parentIndex] += sum[p_parentIndex]; // update sum
            nodes[p_parentIndex].setParent(nodes[q_parentIndex]);
            nodes[p].setParent(nodes[q_parentIndex]);
        } else { // means p union is bigger than q
            // or both roots are same size
            // p absorbs q nodes
            size[p_parentIndex] += size[q_parentIndex]; // update the number of child nodes
            sum[p_parentIndex] += sum[q_parentIndex]; // update sum
            nodes[q_parentIndex].setParent(nodes[p_parentIndex]);
            nodes[q].setParent(nodes[p_parentIndex]);
        }
    }

    void move(int p, int q) {
        int p_parentIndex = findParentIndex(p);
        int q_parentIndex = findParentIndex(q);
        if (p_parentIndex == q_parentIndex) {
            return;// both parents same. Do nothing
        } else {
            // if (isRoot(p) && isRoot(q)) { // means p & q is root
            // union(p, q);
            // return;
            // }
            if (isRoot(p)) { // If I am going to merge into Q and I am a root, I need to dump into a child?
                // or I just change the representation into one child?

            }
            movement[p] = q_parentIndex;
            size[p_parentIndex]--;
            sum[p_parentIndex] -= p;

            size[q_parentIndex]++;
            sum[q_parentIndex] += p;
        }
    }

    void print(int p, FastIO fio) {
        int parent = this.findParentIndex(p);
        fio.print(this.getSize(parent));
        fio.print(' ');
        fio.println(this.findSum(parent));
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
