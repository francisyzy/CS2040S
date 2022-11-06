import java.util.*;
import java.io.*;

public class BellmanFordDemo {
  public static final int CANNON_LAUNCH_TIME = 2; // MAGIC NUMBERS!
  public static final int RUNNING_RATE = 5; // meter per second
  public static final int CANNOT_DISTANCE = 50; // meters
  public static final int source = 0; // starting pos
  public static final double INF = 1000000000.0;
  // public static ArrayList< ArrayList< IntegerPair > > AdjList = new ArrayList<
  // ArrayList< IntegerPair > >();
  public static ArrayList<Double> D = new ArrayList<>();
  public static ArrayList<Integer> p = new ArrayList<Integer>();
  public static int V, E;

  public static void initSSSP(int s) { // initialization phase
    D.clear();
    D.addAll(Collections.nCopies(V, INF)); // use 1B to represent INF
    p.clear();
    p.addAll(Collections.nCopies(V, -1)); // use -1 to represent NULL
    D.set(s, 0.0); // this is what we know so far
  }

  public static void relax(int u, int v, Double w_u_v) {
    if (D.get(u) != INF && D.get(v) > D.get(u) + w_u_v) { // if SP can be shortened
      D.set(v, D.get(u) + w_u_v); // relax this edge
      p.set(v, u); // remember/update the predecessor
    }
  }

  public static void backtrack(int s, int u) {
    if (u == -1 || u == s) {
      System.out.printf("%d", u);
      return;
    }
    backtrack(s, p.get(u));
    System.out.printf(" %d", u);
  }

  public static void main(String[] args) {
    FastIO fio = new FastIO(); // create new instance

    PointOfInterest initial = new PointOfInterest(fio.nextDouble(), fio.nextDouble(), 1);
    PointOfInterest destination = new PointOfInterest(fio.nextDouble(), fio.nextDouble(), 2);
    int n = fio.nextInt(); // 0 to 100
    V = n + 2;

    ArrayList<PointOfInterest> positions = new ArrayList<>(n + 2);
    positions.add(initial);
    positions.add(destination);
    for (int i = 0; i < n; i++) {
      PointOfInterest cannon = new PointOfInterest(fio.nextDouble(), fio.nextDouble(), 3);
      positions.add(cannon);
    }
    ArrayList<IntegerTriple> edgeList = new ArrayList<>();
    for (int i = 0; i < positions.size(); i++) {
      for (int j = 0; j < positions.size(); j++) {
        PointOfInterest p1 = positions.get(i);
        PointOfInterest p2 = positions.get(j);
        double distance = Math.hypot(p1.x - p2.x, p1.y - p2.y);
        double time1 = distance / RUNNING_RATE;
        double time2 = Double.MAX_VALUE;
        if (p1.isCannon()) {
          time2 = CANNON_LAUNCH_TIME + (Math.abs(distance - CANNOT_DISTANCE) / RUNNING_RATE);
        }
        edgeList.add(new IntegerTriple(i, j, Math.min(time1, time2)));
      }
    }

    initSSSP(source);

    for (int i = 0; i < V - 1; i++) // relax all E edges V-1 times, O(V)
      for (IntegerTriple integerTriple : edgeList) {
        relax(integerTriple.first(), integerTriple.second(), integerTriple.third()); // O(1) here
      }


    int end = 1;
    System.out.printf("%.6f\n", D.get(end));
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


class IntegerTriple {
  public Integer _first, _second;
  Double _third;

  public IntegerTriple(Integer f, Integer s, Double t) {
    _first = f;
    _second = s;
    _third = t;
  }

  public int compareTo(IntegerTriple o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else if (!this.second().equals(o.second()))
      return this.second() - o.second();
    else
      return (int) (this.third() - o.third());
  }

  Integer first() {
    return _first;
  }

  Integer second() {
    return _second;
  }

  Double third() {
    return _third;
  }

  public String toString() {
    return first() + " " + second() + " " + third();
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
