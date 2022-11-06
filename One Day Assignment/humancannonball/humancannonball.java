
***REMOVED***
import java.io.*;
import java.util.*;

public class humancannonball {
    public static final int CANNON_LAUNCH_TIME = 2; // MAGIC NUMBERS!
    public static final int RUNNING_RATE = 5; // meter per second
    public static final int CANNOT_DISTANCE = 50; // meters

    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        PointOfInterest initial = new PointOfInterest(fio.nextDouble(), fio.nextDouble(), 1);
        PointOfInterest destination = new PointOfInterest(fio.nextDouble(), fio.nextDouble(), 2);
        final int n = fio.nextInt(); // 0 to 100
        final int points = n + 2; // one extra start and end positions

        ArrayList<PointOfInterest> positions = new ArrayList<>(points);
        positions.add(initial);
        positions.add(destination);
        for (int i = 0; i < n; i++) {
            PointOfInterest cannon = new PointOfInterest(fio.nextDouble(), fio.nextDouble(), 3);
            positions.add(cannon);
        }
        // Weights are all in time
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            for (int j = 0; j < positions.size(); j++) {
                PointOfInterest p1 = positions.get(i);
                PointOfInterest p2 = positions.get(j);
                double distance = Math.hypot(p1.x - p2.x, p1.y - p2.y);
                double time1 = distance / RUNNING_RATE;
                double time2 = p1.isCannon()
                        ? CANNON_LAUNCH_TIME + (Math.abs(distance - CANNOT_DISTANCE) / RUNNING_RATE)
                        : Double.MAX_VALUE;
                edgeList.add(new Edge(i, j, Math.min(time1, time2)));
            }
        }
        ArrayList<Double> dist = new ArrayList<>(Collections.nCopies(points, Double.MAX_VALUE));
        dist.set(0, 0.0);

        ArrayList<Integer> predecessor = new ArrayList<Integer>();
        predecessor.addAll(Collections.nCopies(points, -1)); // use -1 to represent NULL

        for (int i = 0; i < points - 1; i++) // relax all E edges V-1 times, O(V)
            for (Edge edge : edgeList) {
                relax(edge.left, edge.right, edge.weight, dist, predecessor); // O(1) here
            }

        fio.println(String.format("%.6f", dist.get(1))); // print the distance from 0 to 1
        // 0 = starting and 1 = ending positions
        fio.close(); // important; always close at the end of the code
    }

    static void relax(int u, int v, double w_u_v, ArrayList<Double> dist, ArrayList<Integer> predecessor) {
        if (dist.get(u) != Double.MAX_VALUE && dist.get(v) > dist.get(u) + w_u_v) { // if SP can be shortened
            dist.set(v, dist.get(u) + w_u_v); // relax this edge
            predecessor.set(v, u); // remember/update the predecessor
        }
    }

}

class Edge {
    int left, right;
    double weight;

    public Edge(int left, int right, double weight) {
        this.left = left;
        this.right = right;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + (left) + ", " + (right) + ", W" + weight + ")";
    }
}

class PointOfInterest {
    double x, y;
    int type; // 1 = start 2 = end 3 = cannon

    PointOfInterest(double x, double y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    boolean isCannon() {
        return this.type == 3;
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
