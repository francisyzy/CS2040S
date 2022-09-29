***REMOVED***
import java.io.*;
import java.util.*;

public class boatparts {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int numOfBoatParts = fio.nextInt(); // read int
        int numOfBoatingDays = fio.nextInt(); // read int
        HashSet<String> boatParts = new HashSet<>();
        int paradox = 0;
        for (int i = 0; i < numOfBoatingDays; i++) {
            boatParts.add(fio.nextLine());
            if (numOfBoatParts == boatParts.size() && paradox == 0) {
                paradox = i + 1;
            }
        }
        if (paradox == 0) {
            fio.println("paradox avoided");
        } else {
            fio.println(paradox);

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
