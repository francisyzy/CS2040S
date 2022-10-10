***REMOVED***
import java.io.*;
import java.util.*;

public class almostunionfind {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int n = fio.nextInt(); // read int
        int m = fio.nextInt(); // read int

        int[] collections = new int[n + 1]; // 1 index arr

        // System.out.println("original int array : " + Arrays.toString(collections));

        Arrays.setAll(collections, i -> -1);
        collections[0] = 0;// less confusion

        // System.out.println("array.setAll(int) output: " +
        // Arrays.toString(collections));

        for (int i = 0; i < m; i++) {
            System.out.println("array.setAll(int) output: " + Arrays.toString(collections));
            int opCode = fio.nextInt(); // read int
            int p = fio.nextInt(); // read int
            System.out.println("p: " + p);
            int p_parentIndex = collections[p];
            int p_parentValue = -1;
            System.out.println("p_parentIndex: " + p_parentIndex);
            System.out.println("p_parentValue: " + p_parentValue);
            while (p_parentIndex > 0) {
            p_parentIndex = collections[p_parentIndex];
            p_parentValue = collections[p_parentIndex];
            }
            if (opCode == 3) {
                // if (p_parentIndex < 0) { // means p is at the root already
                // p_parentIndex = p;
                // }
                // fio.println(collections[p_parentIndex]); // print the "..." contents with
                // newline at the end
            } else {
                // int p_parentIndex = collections[p];
                // int p_parentValue = -1;
                // System.out.println("p_parentIndex: " + p_parentIndex);
                // System.out.println("p_parentValue: " + p_parentValue);
                // while (p_parentIndex > 0) {
                //     p_parentIndex = collections[p_parentValue];
                //     p_parentValue = collections[p_parentIndex];
                // }
                int q = fio.nextInt(); // read int

                int q_parentIndex = collections[q];
                int q_parentValue = -1;
                System.out.println("q_parentIndex: " + q_parentIndex);
                System.out.println("q_parentValue: " + q_parentValue);
                while (q_parentIndex > 0) {
                    q_parentIndex = collections[q_parentIndex];
                    q_parentValue = collections[q_parentIndex];
                }
                if (opCode == 1) { // union
                    if (p_parentValue < 0 && q_parentValue < 0) { // means both are roots
                        collections[p] += collections[q]; // update the number of child nodes
                        collections[q] = p; // set parent of q into p
                    } else if (p_parentValue == q_parentValue) { // means both roots are same size
                        collections[p_parentIndex] += collections[q_parentIndex]; // update the number of child nodes
                        collections[q_parentIndex] = p_parentIndex; // set parent of q into p
                    } else if (p_parentValue > q_parentValue) { // means p union is bigger than q
                        collections[p_parentIndex] += collections[q_parentIndex]; // update the number of child nodes
                        collections[q_parentIndex] = p_parentIndex; // set parent of q into p
                    } else if (p_parentValue < q_parentValue) { // means q union is bigger than p
                        collections[q_parentIndex] += collections[p_parentIndex]; // update the number of child nodes
                        collections[p_parentIndex] = q_parentIndex; // set parent of q into p
                    }

                }
                if (opCode == 2) { // move
                    if (p_parentIndex == q_parentIndex) {
                        // Do nothing because they are the same set already
                    } else {
                        if (p_parentIndex < 0) { // means p is at the root already
                            p_parentIndex = p;
                        }
                        if (q_parentIndex < 0) { // means q is at the root already
                            q_parentIndex = q;
                        }
                        // update set sizes
                        collections[q_parentIndex]--;
                        collections[p_parentIndex]++;
                        // move set pointer
                        collections[p] = q;
                    }
                }
            }
        }

        fio.close(); // important; always close at the end of the code
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
