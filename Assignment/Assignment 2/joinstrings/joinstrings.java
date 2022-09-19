import java.io.*;
import java.util.*;

public class joinstrings {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int numOfStrings = fio.nextInt(); // read int

        ArrayList<StringBuilder> strings = new ArrayList<StringBuilder>();
        strings.ensureCapacity(numOfStrings);
        for (int i = 0; i < numOfStrings; i++) {
            strings.add(new StringBuilder(fio.nextLine()));
        }

        // String operations
        for (int i = 0; i < numOfStrings - 1; i++) {
            int first = fio.nextInt() - 1; // read int
            int second = fio.nextInt() - 1; // read int
            strings.set(first, strings.get(first).append(strings.get(second)));
            strings.set(second, null);

        }
        // for (int i = 0; i < numOfStrings; i++) {
        // if (strings.get(i) != null) {
        // fio.println(strings.get(i));
        // }
        // }
        // strings.removeAll(Collections.singleton(null));
        strings.removeIf(Objects::isNull);
        fio.println(strings.get(0));

        // double real = fio.nextDouble(); // read double
        // String token = fio.next(); // read a single token
        // String line = fio.nextLine(); // read an entire line
        // fio.print("..."); // print the "..." contents
        // fio.println("..."); // print the "..." contents with newline at the end

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
