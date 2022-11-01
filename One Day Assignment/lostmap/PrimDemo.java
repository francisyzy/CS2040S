import java.util.*;
import java.io.*;

public class PrimDemo {
  public static ArrayList<ArrayList<Edges>> AdjList;
  public static ArrayList<Boolean> taken;
  public static PriorityQueue<Edges> pq;

  public static void process(int vtx) {
    System.out.println(">> At vertex " + vtx);
    taken.set(vtx, true);
    for (int j = 0; j < AdjList.get(vtx).size(); j++) {
      Edges v = AdjList.get(vtx).get(j);
      // System.out.println(taken.get(v.left()));
      if (!taken.get(v.left())) {
        pq.offer(new Edges(v.left(), v.right(), v.weight(), true)); // we sort by weight then by adjacent vertex
        System.out.println(">> Inserting (" + v.left() + ", " + v.right() + ", W" + v.weight() + ") to priority queue");
      } else {
        System.out.println(">> Ignoring (" + v.left() + ", " + v.right() + ", W" + v.weight() + ")");
      }
    }
  }

  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {
    /*
     * // Sample graph shown in lecture
     * 5 7
     * 1 2 4
     * 1 3 4
     * 2 3 2
     * 1 4 6
     * 3 4 8
     * 1 5 6
     * 4 5 9
     */

    Scanner sc = new Scanner(System.in);
    int V = sc.nextInt(), E = sc.nextInt();
    AdjList = new ArrayList<ArrayList<Edges>>();

    for (int i = 0; i < V; i++) {
      ArrayList<Edges> Neighbor = new ArrayList<Edges>(); // create vector of Integer pair
      AdjList.add(Neighbor); // store blank vector first
    }

    for (int i = 0; i < E; i++) { // store graph information in Adjacency List
      // we decrease index by 1 to change input to 0-based indexing
      int u = sc.nextInt() - 1, v = sc.nextInt() - 1, w = sc.nextInt();
      AdjList.get(u).add(new Edges(v, u, w)); // bi-directional
      AdjList.get(v).add(new Edges(u, v, w));
      // AdjList.get(u).add(new Edges(u, v, w));
    }
    System.out.println(AdjList);
    

    taken = new ArrayList<Boolean>();
    taken.addAll(Collections.nCopies(V, false));
    pq = new PriorityQueue<Edges>();
    // take any vertex of the graph, for simplicity, vertex 0, to be included in the
    // MST
    process(0);
    int mst_cost = 0;
    ArrayList<Edges> MST = new ArrayList<Edges>();

    while (!pq.isEmpty()) { // we will do this until all V vertices are taken (or E = V-1 edges are taken)
      Edges front = pq.poll();

      if (!taken.get(front.left())) { // we have not connected this vertex yet
        mst_cost += front.weight(); // add the weight of this edge
        MST.add(front);
        System.out.println("Adding edge: (" + front.left() + ", " + front.right() + ", W" + front.weight()
            + "), MST cost now = " + mst_cost);
        process(front.left());
      } else // this vertex has been connected before via some other tree branch
        System.out
            .println("Ignoring edge: (" + front.left() + ", " + front.right() + ", W" + front.weight()
                + "), MST cost now = " + mst_cost);
    }
    System.out.println(MST);
    System.out.printf("Final MST cost %d\n", mst_cost);
  }
}

class Edges implements Comparable<Edges> {
  public Integer _left, _right, _weight;
  public boolean orderByWeight;

  public Edges(Integer f, Integer s, Integer t) {
    _left = f;
    _right = s;
    _weight = t;
    orderByWeight = false;
  }

  public Edges(Integer f, Integer s, Integer t, boolean orderByWeight) {
    _left = f;
    _right = s;
    _weight = t;
    this.orderByWeight = orderByWeight;
  }

  public int compareTo(Edges o) {
    if (orderByWeight) {
      return this.weight() - o.weight();
    }
    if (!this.left().equals(o.left())) {
      return this.left() - o.left();
    } else {
      return this.right() - o.right();
    }
  }

  Integer left() {
    return _left;
  }

  Integer right() {
    return _right;
  }

  Integer weight() {
    return _weight;
  }

  @Override
  public String toString() {
    return "(" + (left() + 1) + ", " + (right() + 1) + ", W" + weight() + ")";
    // return "(" + left() + ", " + right() + ", W" + weight() + ")";
  }
}
