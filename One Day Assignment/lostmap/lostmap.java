
***REMOVED***
import java.io.*;
import java.util.*;

public class lostmap {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int n = fio.nextInt(); // read int

        int[][] adjMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjMatrix[i][j] = fio.nextInt();
            }
        }
        ArrayList<ArrayList<Edge>> adjList = adjMatrixToList(adjMatrix, n);

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        ArrayList<Boolean> taken = new ArrayList<Boolean>(n * n);
        taken.addAll(Collections.nCopies(n * n, false)); // set all of the paths as not taken yet

        process(0, adjList, taken, pq);
        ArrayList<Edge> MST = new ArrayList<Edge>(n - 1);

        while (!pq.isEmpty()) { // we will do this until all V vertices are taken (or E = V-1 edge are taken)
            Edge front = pq.poll(); // front of the queue
            if (!taken.get(front.left)) { // we have not connected this vertex yet
                MST.add(front); // add the edge into the final MST list
                process(front.left, adjList, taken, pq); // process next
            }
        }
        for (int i = 0; i < n - 1; i++) {
            fio.println(MST.get(i).forPrint());
        }

        fio.close(); // important; always close at the end of the code
    }

    /**
     * With a adjMatrix, & its sizes. Convert it into an adjList
     * 
     * @param adjMatrix
     * @param r
     * @param c
     * @return ArrayList<ArrayList<Edge>>
     */
    static ArrayList<ArrayList<Edge>> adjMatrixToList(int[][] adjMatrix, int n) {
        ArrayList<ArrayList<Edge>> adjList = new ArrayList<ArrayList<Edge>>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<Edge>()); // store blank vector first
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (i < j) {
                    continue;
                }
                adjList.get(i).add(new Edge(j, i, adjMatrix[i][j]));
                adjList.get(j).add(new Edge(i, j, adjMatrix[i][j]));
            }
        }

        return adjList;
    }

    /**
     * Process the current vertex and add more items to be process (if applicable)
     * 
     * @param vtx
     * @param AdjList
     * @param taken
     * @param pq
     */
    static void process(int vtx, ArrayList<ArrayList<Edge>> AdjList, ArrayList<Boolean> taken,
            PriorityQueue<Edge> pq) {
        taken.set(vtx, true);
        for (int j = 0; j < AdjList.get(vtx).size(); j++) {
            Edge v = AdjList.get(vtx).get(j);
            if (!taken.get(v.left)) {
                pq.offer(new Edge(v.left, v.right, v.weight, true)); // we sort by weight then by adjacent vertex
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    public Integer left, right, weight;
    public boolean orderByWeight;

    public Edge(Integer left, Integer right, Integer weight) {
        this.left = left;
        this.right = right;
        this.weight = weight;
        orderByWeight = false;
    }

    public Edge(Integer left, Integer right, Integer weight, boolean orderByWeight) {
        this.left = left;
        this.right = right;
        this.weight = weight;
        this.orderByWeight = orderByWeight;
    }

    @Override
    public int compareTo(Edge o) {
        if (orderByWeight) {
            return this.weight - o.weight;
        }
        if (!this.left.equals(o.left)) {
            return this.left - o.left;
        } else {
            return this.right - o.right;
        }
    }

    @Override
    public String toString() {
        return "(" + (left + 1) + ", " + (right + 1) + ", W" + weight + ")";
    }

    public String forPrint() {
        return (left + 1) + " " + (right + 1);
    }
}

// class lostmap
// psvm
// let n = fio.nextInt
// int[][] adjMatrix = new int[n][n]
// for (let i = 0; i < n; i++)
// for (let j = 0; j < n; j++)
// adjMatrix[i][j] = fio.nextInt

// ArrayList<ArrayList<Edge>> adjList = adjMatrixToList(adjMatrix, n)

// PriorityQueue<Edge> pq = new PriorityQueue<Edge>
// ArrayList<Boolean> taken = new ArrayList<Boolean>
// taken.addAll(Collections.nCopies(n * n, false)) // set all of the paths as
// not taken yet

// process(0, adjList, taken, pq)
// ArrayList<Edge> MST = new ArrayList<Edge>

// while (!pq.isEmpty) // we will do this until all V vertices are taken (or E =
// V-1 edge are taken)
// Edge front = pq.poll; // front of the queue
// if (!taken.get(front.left)) // we have not connected this vertex yet
// MST.add(front) // add the edge into the final MST list
// process(front.left, adjList, taken, pq) // process next

// for (let i = 0; i < n - 1; i++)
// fio.println(MST.get(i));

// /**
// * With a adjMatrix, & its sizes. Convert it into an adjList
// *
// * @param adjMatrix
// * @param r
// * @param c
// * @return ArrayList<ArrayList<Edge>>
// */
// static ArrayList<ArrayList<Edge>> adjMatrixToList(int[][] adjMatrix, let n)
// ArrayList<ArrayList<Edge>> adjList = new ArrayList<ArrayList<Edge>>
// for (let i = 0; i < n; i++)
// adjList.add(new ArrayList<Edge>) // store blank vector first

// for (let i = 0; i < n; i++)
// for (let j = 0; j < n; j++)
// if (i == j)
// continue

// if (i < j)
// continue

// adjList.get(i).add(new Edge(j, i, adjMatrix[i][j]))
// adjList.get(j).add(new Edge(i, j, adjMatrix[i][j]))

// return adjList

// /**
// * Process the current vertex and add more items to be process (if applicable)
// *
// * @param vtx
// * @param AdjList
// * @param taken
// * @param pq
// */
// static void process(let vtx, ArrayList<ArrayList<Edge>> AdjList,
// ArrayList<Boolean> taken,
// PriorityQueue<Edge> pq)
// taken.set(vtx, true)
// for (let j = 0; j < AdjList.get(vtx).size; j++)
// Edge v = AdjList.get(vtx).get(j)
// if (!taken.get(v.left))
// pq.offer(new Edge(v.left, v.right, v.weight, true)) // we sort by weight then
// by adjacent vertex

// class Edge implements Comparable<Edge>
// Integer _left, _right, _weight
// boolean orderByWeight

// Edge(Integer left, Integer right, Integer weight)
// _left = left
// _right = right
// _weight = weight
// orderByWeight = false

// Edge(Integer left, Integer right, Integer weight, boolean orderByWeight)
// _left = left
// _right = right
// _weight = weight
// this.orderByWeight = orderByWeight

// let compareTo(Edge o)
// if (orderByWeight)
// return this.weight - o.weight

// if (!this.left.equals(o.left))
// return this.left - o.left
// else
// return this.right - o.right

// @Override
// String toString
// return "(" + (left + 1) + ", " + (right + 1) + ", W" + weight + ")"

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
