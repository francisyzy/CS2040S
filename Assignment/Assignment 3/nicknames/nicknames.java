
***REMOVED***
import java.io.*;
import java.util.*;

public class nicknames {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance
        // Use AVL Tree

        TreeSet<String> names = new TreeSet<String>();

        int numberOfNames = fio.nextInt(); // read int
        for (int i = 0; i < numberOfNames; i++) {
            String line = fio.nextLine(); // read an entire line
            names.add(line);
        }

        int numberOfNickNames = fio.nextInt(); // read int
        for (int i = 0; i < numberOfNickNames; i++) {
            String line = fio.nextLine(); // read an entire line
            SortedSet<String> found = names.subSet(line,
                    String.format("%-10s", line).replace(' ', 'z'));
            fio.println(found.size()); // print the "..." contents with newline at the end
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
