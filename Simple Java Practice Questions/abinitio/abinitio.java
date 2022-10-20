
***REMOVED***
import java.io.*;
import java.util.*;

public class abinitio {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int v = fio.nextInt(); // vertices
        int e = fio.nextInt(); // edges
        int q = fio.nextInt(); // queries
        for (int i = 0; i < q; i++) {
            int instruction = fio.nextInt(); // instructions
            int x, y;
            switch (instruction) {
                case 1:

                    break;
                case 2:
                    x = fio.nextInt();
                    y = fio.nextInt();

                    break;
                case 3:
                    x = fio.nextInt();

                    break;
                case 4:
                    x = fio.nextInt();
                    y = fio.nextInt();

                    break;
                case 5:

                    break;
                case 6:

                    break;

                default:
                    System.out.println("Why u here");
                    break;
            }

        }
        fio.println(v); // print the "..." contents with newline at the end
        for (int i = 0; i < v; i++) {
            fio.println("..."); // print the "..." contents with newline at the end
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
