import java.util.*;

// barebones implementation of BST

class BSTDemo {
    public static void main(String[] args) throws Exception {
        BST T = new BST(); // an empty BST

        String[] sample = new String[] { "Alice", "Bob", "Charlie", "Eve", "Mallory", "AliceA" };

        // Sample BST as shown in Lecture
        for (int i = 0; i < sample.length; i++) {
            T.insert(sample[i]);
        }
        T.inorder();
        System.out.print("Root Size:");
        System.out.println(T.getRootSize());

        System.out.print("Root Height:");
        System.out.println(T.getRootHeight());

        for (int i = 0; i < sample.length; i++) {
            String thingy = sample[i];
            System.out.print("Searching " + thingy);
            System.out.print(" ");
            System.out.print(T.search(thingy));
            System.out.print(" Height: ");
            System.out.print(T.getHeight(thingy));
            System.out.print(" Size: ");
            System.out.println(T.getSize(thingy));
        }

        System.out.println("Find Min: " + T.findMin()); // 4
        System.out.println("Find Max: " + T.findMax()); // 71

        T.inorder(); // The BST: 4, 5, 6, 7, 15, 23, 50, 71

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

    // public method called to search for a value v.
    // Return v if it is found in the BST otherwise return "".
    // Here the assumption is that "" is never a valid key value.
    public String search(String v) {
        BSTVertex res = search(root, v);
        return res == null ? "" : res.key;
    }

    // helper method to perform search
    public BSTVertex search(BSTVertex T, String v) {
        if (T == null)
            return null; // not found
        else if (T.key == v)
            return T; // found
        else if (T.key.compareTo(v) > 0)
            return search(T.right, v); // search to the right
        else
            return search(T.left, v); // search to the left
    }

    // public method called to search for a size v.
    // Return v if it is found in the BST otherwise return -1.
    // Here the assumption is that -1 is never a valid size value.
    public int getSize(String v) {
        BSTVertex res = search(root, v);
        return res == null ? -1 : (res.left == null ? 0 : res.left.size) + (res.right == null ? 0 : res.right.size) + 1;
    }

    // public method called to get root size
    // Return v if it BST is not null otherwise return -1.
    // Here the assumption is that -1 is never a valid size value.
    public int getRootSize() {
        return (root.left == null ? 0 : root.left.size) + (root.right == null ? 0 : root.right.size) + 1;
    }

    // public method called to search for a size v.
    // Return v if it is found in the BST otherwise return -1.
    // Here the assumption is that -1 is never a valid size value.
    public int getHeight(String v) {
        BSTVertex res = search(root, v);
        return res == null ? -1
                : max(res.left == null ? -1 : res.left.height, res.right == null ? -1 : res.right.height) + 1;
    }

    public int getRootHeight() {
        return max(root.left == null ? -1 : root.left.height, root.right == null ? -1 : root.right.height) + 1;
    }

    // public method called to find Minimum key value in BST
    public String findMin() {
        return findMin(root);
    }

    // helper method to perform findMin
    // Question: What happens if BST is empty?
    public String findMin(BSTVertex T) {
        if (T.left == null)
            return T.key; // this is the min
        else
            return findMin(T.left); // go to the left
    }

    // public method called to find Maximum key value in BST
    public String findMax() {
        return findMax(root);
    }

    // helper method to perform findMax
    // Question: Again, what happens if BST is empty?
    public String findMax(BSTVertex T) {
        if (T.right == null)
            return T.key; // this is the max
        else
            return findMax(T.right); // go to the right
    }

    // public method to find successor to given value v in BST.
    public String successor(String v) {
        BSTVertex vPos = search(root, v);
        return vPos == null ? "" : successor(vPos);
    }

    // helper recursive method to find successor to for a given vertex T in BST
    public String successor(BSTVertex T) {
        if (T.right != null) // this subtree has right subtree
            return findMin(T.right); // the successor is the minimum of right subtree
        else {
            BSTVertex par = T.parent;
            BSTVertex cur = T;
            // if par(ent) is not root and cur(rent) is its right children
            while ((par != null) && (cur == par.right)) {
                cur = par; // continue moving up
                par = cur.parent;
            }
            return par == null ? "" : par.key; // this is the successor of T
        }
    }

    // public method to find predecessor to given value v in BST
    public String predecessor(String v) {
        BSTVertex vPos = search(root, v);
        return vPos == null ? "" : predecessor(vPos);
    }

    // helper recursive method to find predecessor to for a given vertex T in BST
    public String predecessor(BSTVertex T) {
        if (T.left != null) // this subtree has left subtree
            return findMax(T.left); // the predecessor is the maximum of left subtree
        else {
            BSTVertex par = T.parent;
            BSTVertex cur = T;
            // if par(ent) is not root and cur(rent) is its left children
            while ((par != null) && (cur == par.left)) {
                cur = par; // continue moving up
                par = cur.parent;
            }
            return par == null ? "" : par.key; // this is the successor of T
        }
    }

    // public method called to perform inorder traversal
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    // helper method to perform inorder traversal
    public void inorder(BSTVertex T) {
        if (T == null)
            return;
        inorder(T.left); // recursively go to the left
        System.out.print(" "); // visit this BST node
        System.out.print(T.key); // visit this BST node
        inorder(T.right); // recursively go to the right
    }

    // public method called to insert a new key with value v into BST
    public void insert(String v) {
        root = insert(root, v);
    }

    // helper recursive method to perform insertion of new vertex into BST
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

        return balanceTree(T); // return the updated BST
    }

    private BSTVertex balanceTree(BSTVertex T) {

        if (bf(T) == 2 && 0 <= bf(T.left) && bf(T.left) <= 1) {
            // System.out.println("Need to rotate right");
            return rotateRight(T);
        } else if (bf(T) == 2 && bf(T.left) == -1) {
            // System.out.println("Need left then right");
            rotateLeft(T.left);
            return rotateRight(T);
        } else if (bf(T) == -2 && -1 <= bf(T.right) && bf(T.right) <= 0) {
            // System.out.println("Need to rotate right");
            return rotateLeft(T);
        } else if (bf(T) == -2 && bf(T.right) == -1) {
            // System.out.println("Need right then left");
            rotateRight(T.right);
            return rotateLeft(T);
        } else {
            // System.out.println("Tree is is balanced");
            return T;
        }
    }

    // Balance factor
    public static int bf(BSTVertex T) {
        if (T.left != null & T.right != null) {
            return T.left.height - T.right.height;
        } else if (T.left != null) {
            return T.left.height;
        } else if (T.right != null) {
            return T.right.height;
        } else {
            return 0;
        }
    }

    public static int max(int left, int right) {
        if (left > right) {
            return left;
        } else if (right > left) {
            return right;
        } else {
            return left;
        }
    }

    // public method to delete a vertex containing key with value v from BST
    public void delete(String v) {
        root = delete(root, v);
    }

    // helper recursive method to perform deletion
    public BSTVertex delete(BSTVertex T, String v) {
        if (T == null)
            return T; // cannot find the item to be deleted

        if (T.key.compareTo(v) > 0) // search to the right
            T.right = delete(T.right, v);
        else if (T.key.compareTo(v) < 0) // search to the left
            T.left = delete(T.left, v);
        else { // this is the node to be deleted
            if (T.left == null && T.right == null) // this is a leaf
                T = null; // simply erase this node
            else if (T.left == null && T.right != null) { // only one child at right
                T.right.parent = T.parent;
                T = T.right; // bypass T
            } else if (T.left != null && T.right == null) { // only one child at left
                T.left.parent = T.parent;
                T = T.left; // bypass T
            } else { // has two children, find successor
                String successorV = successor(v);
                T.key = successorV; // replace this key with the successor's key
                T.right = delete(T.right, successorV); // delete the old successorV
            }
        }

        return T; // return the updated BST
    }

    public BSTVertex rotateLeft(BSTVertex T) {
        BSTVertex w = T.right;
        w.parent = T.parent;
        T.parent = w;
        T.right = w.left;
        if (w.left != null) {
            w.left.parent = T;
        }
        w.left = T;
        return w;
    }

    public BSTVertex rotateRight(BSTVertex T) {
        BSTVertex w = T.left;
        w.parent = T.parent;
        T.parent = w;
        T.left = w.right;
        if (w.right != null) {
            w.right.parent = T;
        }
        w.right = T;
        return w;
    }
}
