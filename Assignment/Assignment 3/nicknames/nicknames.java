
***REMOVED***
import java.io.*;
import java.util.*;

public class nicknames {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        BST names = new BST(); // an empty BST

        int numberOfNames = fio.nextInt(); // read int
        for (int i = 0; i < numberOfNames; i++) {
            String line = fio.nextLine(); // read an entire line
            names.add(line);
        }

        int numberOfNickNames = fio.nextInt(); // read int
        HashMap<String, Integer> pastQueries = new HashMap<>(); // Cache previous requests inside HashMap for more speed
        for (int i = 0; i < numberOfNickNames; i++) {
            String line = fio.nextLine(); // read an entire line
            if (pastQueries.get(line) == null) {
                int foundNumberOfNickNames = names.findNumNickname(line);
                pastQueries.put(line, foundNumberOfNickNames);
                fio.println(foundNumberOfNickNames);
            } else {
                fio.println(pastQueries.get(line));
            }

        }

        fio.close(); // important; always close at the end of the code
    }
}

// Every vertex in this BST is a Java Class
class BSTVertex {
    // all these attributes remain public to slightly simplify the code
    public BSTVertex parent, left, right;
    public String key;
    public int height; // will be used in lecture on AVL
    public int size; // will be used in lecture on AVL

    BSTVertex(String v) {
        key = v;
        parent = left = right = null;
        height = -1;
        size = 0;
    }
}

// This is just a sample implementation
// There are other ways to implement BST concepts...
class BST {
    public BSTVertex root;

    public BST() {
        root = null;
    }

    /**
     * public method called to get the height of vertex.
     * Return v if it is found in the BST otherwise return -1.
     * Here the assumption is that -1 is for height less things.
     * 
     * @param v
     * @return int
     */
    public int getHeight(BSTVertex v) {
        return v == null ? -1 : v.height;
    }

    /**
     * public method to perform nickName traversal
     * 
     * @param v
     * @return int
     */
    public int findNumNickname(String v) {
        return findNumNickname(root, v);
    }

    /**
     * helper method to perform nickName traversal
     * 
     * @param T
     * @param v
     * @return int
     */
    public int findNumNickname(BSTVertex T, String v) {
        if (T == null) {
            return 0;
        }
        if (T.key.indexOf(v) == 0) {// Found nickname! Need to check if children are also nick
            return 1 + findNumNickname(T.right, v) + findNumNickname(T.left, v);
        }
        if (T.key.compareTo(v) > 0) { // search to the right
            return findNumNickname(T.right, v);
        } else { // search to the left
            return findNumNickname(T.left, v);
        }
    }

    /**
     * alias method for insert
     * 
     * @param v
     */
    public void add(String v) {
        root = insert(root, v);
    }

    /**
     * public method called to insert a new key with value v into BST
     * 
     * @param v
     */
    public void insert(String v) {
        root = insert(root, v);
    }

    /**
     * helper recursive method to perform insertion of new vertex into BST
     * 
     * @param T
     * @param v
     * @return BSTVertex
     */
    public BSTVertex insert(BSTVertex T, String v) {
        if (T == null) {
            return new BSTVertex(v); // insertion point is found
        }

        if (T.key.compareTo(v) > 0) { // search to the right
            T.right = insert(T.right, v);
            T.right.parent = T;
        } else { // search to the left
            T.left = insert(T.left, v);
            T.left.parent = T;
        }
        T.height = max(T.left == null ? -1 : T.left.height, T.right == null ? -1 : T.right.height) + 1;
        T.size = (T.left == null ? 0 : T.left.size) + (T.right == null ? 0 : T.right.size) + 1;

        return balanceTree(T); // return the updated and balanced BST
    }

    /**
     * balances tree and returned balanced tree
     * 
     * @param T
     * @return BSTVertex
     */
    private BSTVertex balanceTree(BSTVertex T) {
        if (bf(T) == 2 && 0 < bf(T.left) && bf(T.left) <= 1) {
            return rotateRight(T);
        } else if (bf(T) == 2 && bf(T.left) == -1) {
            T.left = rotateLeft(T.left);
            return rotateRight(T);
        } else if (bf(T) == -2 && -1 <= bf(T.right) && bf(T.right) < 0) {
            return rotateLeft(T);
        } else if (bf(T) == -2 && bf(T.right) == -1) {
            T.right = rotateRight(T.right);
            return rotateLeft(T);
        } else {
            return T;
        }
    }

    /**
     * get balance factor
     * 
     * @param T
     * @return int
     */
    public static int bf(BSTVertex T) {
        if (T == null) {
            return 0;
        }
        if (T.left != null && T.right != null) {
            return T.left.height - T.right.height;
        } else if (T.left != null) {
            return T.left.height;
        } else if (T.right != null) {
            return T.right.height;
        } else {
            return 0;
        }
    }

    /**
     * get the bigger int
     * 
     * @param left
     * @param right
     * @return int
     */
    public static int max(int left, int right) {
        if (left > right) {
            return left;
        } else if (right > left) {
            return right;
        } else {
            return left;
        }
    }

    /**
     * rotates left
     * 
     * @param T
     * @return BSTVertex
     */
    public BSTVertex rotateLeft(BSTVertex T) {
        BSTVertex w = T.right;
        BSTVertex temp = w.left;
        w.left = T;
        T.right = temp;
        w.height = max(getHeight(w.left), getHeight(w.right)) + 1;
        T.height = max(getHeight(T.left), getHeight(T.right)) + 1;
        return w;
    }

    /**
     * rotates right
     * 
     * @param T
     * @return BSTVertex
     */
    public BSTVertex rotateRight(BSTVertex T) {
        BSTVertex w = T.left;
        BSTVertex temp = w.right;
        w.right = T;
        T.left = temp;
        w.height = max(getHeight(w.left), getHeight(w.right)) + 1;
        T.height = max(getHeight(T.left), getHeight(T.right)) + 1;
        return w;
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
