
***REMOVED***
import java.io.*;
import java.util.*;

public class babynames {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int num = fio.nextInt(); // read int
        while (num != 0) {
            switch (num) {
                case 1:
                    String name = fio.next();
                    int gender = fio.nextInt();
                    break;
                case 2:
                    String removeName = fio.next();

                    break;
                case 3:

                    break;

                default: // case 4
                    break;
            }
            fio.println("..."); // print the "..." contents with newline at the end
            num = fio.nextInt(); // read int
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
