
***REMOVED***
import java.io.*;
import java.util.*;

public class humancannonball {
    public final int CANNON_LAUNCH_TIME = 2; // MAGIC NUMBERS!
    public final int RUNNING_RATE = 5; // meter per second

    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        double currX = fio.nextDouble(); // read double
        double currY = fio.nextDouble(); // read double
        double targetX = fio.nextDouble(); // read double
        double targetY = fio.nextDouble(); // read double
        int n = fio.nextInt(); // 0 to 100

        // Need a graphical representation of the cannons location? So is easier to find
        // the nearest cannon?
        for (int i = 0; i < n; i++) {
            double cannonX = fio.nextDouble(); // read double
            double cannonY = fio.nextDouble(); // read double
        }

        fio.println("..."); // print the "..." contents with newline at the end

        fio.close(); // important; always close at the end of the code
    }
}

class doublePair {
    double x, y;

    doublePair(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
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
