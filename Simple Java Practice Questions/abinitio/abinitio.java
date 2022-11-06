

import java.io.*;
import java.util.*;

public class abinitio {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int v = fio.nextInt(); // vertices
        int e = fio.nextInt(); // edges
        int q = fio.nextInt(); // queries
        List<IntegerPair> edgeList = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            // Add edge to list (Example: 0<->1 unweighted)
            edgeList.add(new IntegerPair(fio.nextInt(), fio.nextInt()));
        }
        for (int i = 0; i < q; i++) {
            int instruction = fio.nextInt(); // instructions
            int x, y;
            switch (instruction) {
                case 1:
                    // If this integer is 1, no integers follow.
                    // You should add a new vertex labelled V to G. This vertex should not have
                    // edges to or from any other vertex. V – the current size of G – now increases
                    // by 1.
                    v++;
                    break;
                case 2:
                    // If this integer is 2, two integers Xi and Yi (0≤Xi,Yi<V; Xi≠Yi) follow.
                    // You should add a new directed edge connecting vertex Xi to vertex Yi inG. It
                    // is guaranteed that this edge does not currently exist.
                    x = fio.nextInt();
                    y = fio.nextInt();
                    edgeList.add(new IntegerPair(x, y));
                    break;
                case 3:
                    // If this integer is 3, a single integer Xi (0≤Xi<V) follows.
                    // You should delete all the incoming and outgoing edges of Xi from the graph G.
                    x = fio.nextInt();
                    // edgeList.find(x);//find the edge and delete
                    break;
                case 4:
                    // If this integer is 4, two integers Xi and Yi (0≤Xi,Yi<V; Xi≠Yi) follow.

                    // You should remove the directed edge connecting vertex Xi to vertex Yi from G.
                    // It is guaranteed that this edge currently exists.
                    x = fio.nextInt();
                    y = fio.nextInt();
                    edgeList.remove(new IntegerPair(x, y));
                    break;
                case 5:
                    // If this integer is 5, no integers follow.
                    // You should replace G with its transpose G′, defined as follows:For every pair
                    // of vertices (a,b), the edge from a to b exists in G′ if and only a≠b and the
                    // edge from b to a exists in G.
                    break;
                case 6:
                    // If this integer is 6, no integers follow.

                    // You should replace G with its complement G¯, defined as follows:For every
                    // pair of vertices (a,b), the edge from a to b exists in G¯ if and only a≠b and
                    // the edge from a to b does not exist in G.
                    break;

                default:
                    System.out.println("Why u here");
                    break;
            }

        }
        fio.println(v); // On the first line, output a single integer V, the number of vertices in the
                        // graph G.
        for (int i = 0; i < edgeList.size(); i++) {
            // di, the outdegree of vertex i, and
            // hi, the hash of the adjacency list of vertex i, defined as follows. Suppose
            // the vertices in the out-neighbourhood of vertex i are n1<n2<⋯<ndi. Then
            // hi=70⋅n1+71⋅n2+72⋅n3+⋯+7di−1⋅ndiSince hi can be quite large, you should
            // output only the remainder after dividing this number by 109+7.
            fio.println(edgeList.get(i)); // print the "..." contents with newline at the end
        }

        fio.close(); // important; always close at the end of the code
    }
}

class IntegerPair {
    int x, y;

    IntegerPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object in) {
        if (this != in) {
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return x + " " + y;
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
