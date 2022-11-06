import java.util.*;
import java.io.*;

public class BellmanFordDemo {
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
    /*
     * // standard sample graph
     * 5 7 0
     * 1 4 6
     * 1 3 3
     * 0 1 2
     * 2 4 1
     * 0 2 6
     * 3 4 5
     * 0 3 7
     * 
     * // graph with negative weight cycle
     * 5 5 0
     * 0 1 99
     * 1 2 15
     * 2 1 -42
     * 2 3 10
     * 0 4 -99
     * 
     * // BFS challenge
     * 5 6 0
     * 0 1 2
     * 1 3 3
     * 3 4 2
     * 0 2 9
     * 2 4 9
     * 4 2 1
     * 
     * // has negative weight edge but no negative weight cycle
     * 3 3 0
     * 0 1 -10
     * 1 2 -15
     * 0 2 -20
     */

    Scanner sc = new Scanner(System.in); // Copy paste the examples above to a file and redirect as input to
                                         // BellmanFordDemo.class
    V = sc.nextInt();
    E = sc.nextInt();
    int source = sc.nextInt();

    // AdjList.clear();
    ArrayList<IntegerTriple> edgeList = new ArrayList<>();
    // for (int i = 0; i < V; i++) {
    // ArrayList< IntegerPair > Neighbor = new ArrayList < IntegerPair >();
    // AdjList.add(Neighbor); // add neighbor list to Adjacency List
    // }

    for (int i = 0; i < E; i++) {
      int u = sc.nextInt(), v = sc.nextInt();
      double w = sc.nextDouble();
      // AdjList.get(u).add(new IntegerPair(v, w));
      edgeList.add(new IntegerTriple(u, v, w));
    }

    initSSSP(source);

    // Bellman Ford's routine, implemented using AdjList (note that you can choose
    // to use EdgeList -- similar performance)
    for (int i = 0; i < V - 1; i++) // relax all E edges V-1 times, O(V)
      for (IntegerTriple integerTriple : edgeList) {
        relax(integerTriple.first(), integerTriple.second(), integerTriple.third()); // O(1) here
      }
    // for i = 1 to |V|-1 // O(V) here
    // for each edge(u, v) ∈ E // O(E) here
    // relax(u, v, w(u,v)) // O(1) here
    // for (int i = 0; i < V-1; i++) // relax all E edges V-1 times, O(V)
    // for (int u = 0; u < V; u++) // these two loops = O(E)
    // for (int j = 0; j < edgeList.size(); j++) {
    // IntegerTriple v = edgeList.get(u);
    // relax(u, v.first(), v.second());
    // }
    // for (int j = 0; j < AdjList.get(u).size(); j++) {
    // IntegerPair v = AdjList.get(u).get(j);
    // relax(u, v.first(), v.second());
    // }

    // bonus: negative cycle test
    // boolean negative_cycle_exist = false;
    // for (int u = 0; u < V; u++) // one more pass to check
    // for (int j = 0; j < AdjList.get(u).size(); j++) {
    // IntegerPair v = AdjList.get(u).get(j); // try relaxing this edge one more
    // time
    // if (D.get(u) != INF && D.get(v.first()) > D.get(u) + v.second())
    // negative_cycle_exist = true; // if this is true, then negative cycle exists!
    // }

    // System.out.printf("Negative Cycle Exist? %s\n", negative_cycle_exist ? "Yes"
    // : "No");
    // if (!negative_cycle_exist) {
    int end = 2;
    System.out.printf("SSSP(%d, %d) = %.6f\n", source, end, D.get(end));
    // for (int i = 0; i < V; i++) {
    // System.out.printf("SSSP(%d, %d) = %.6f\n", source, i, D.get(i));
    // if (D.get(i) != INF) {
    // System.out.printf("Path: ");
    // backtrack(source, i);
    // System.out.printf("\n");
    // }
    // System.out.printf("\n");
    // }
    // }
  }
}

class IntegerPair implements Comparable<IntegerPair> {
  Integer _first, _second;

  public IntegerPair(Integer f, Integer s) {
    _first = f;
    _second = s;
  }

  public int compareTo(IntegerPair o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else
      return this.second() - o.second();
  }

  Integer first() {
    return _first;
  }

  Integer second() {
    return _second;
  }
}

class IntegerTriple implements Comparable<IntegerTriple> {
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
