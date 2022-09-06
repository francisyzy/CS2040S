import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class sortofsorting {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance
        int students = fio.nextInt(); // read int
        while (students != 0) {
            ArrayList<String> studentList = new ArrayList<String>();
            for (int i = 0; i < students; i++) {
                studentList.add(fio.next());
            }
            for (String student : radix(studentList)) {
                fio.println(student);
            }
            fio.println("");
            students = fio.nextInt();
        }
        // double real = fio.nextDouble(); // read double
        // String token = fio.next(); // read a single token
        // String line = fio.nextLine(); // read an entire line
        // fio.print(...); // print the ... contents
        // fio.println(...); // print the ... contents with newline at the end

        fio.close(); // important; always close at the end of the code
    }

    static ArrayList<String> radix(ArrayList<String> array) {
        List<String>[] buckets = (List<String>[]) Array.newInstance(List.class, 27);
        for (int i = 0; i < 2; i++) {

        }
        return array;
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
