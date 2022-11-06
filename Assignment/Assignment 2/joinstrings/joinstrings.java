
import java.io.*;
import java.util.*;

public class joinstrings {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int numOfStrings = fio.nextInt(); // read int

        ArrayList<ListNode> strings = new ArrayList<ListNode>();
        strings.ensureCapacity(numOfStrings);

        for (int i = 0; i < numOfStrings; i++) {
            strings.add(new ListNode(fio.nextLine()));
        }
        // fio.println(strings);

        // String operations
        int lastString = 0;
        for (int i = 0; i < numOfStrings - 1; i++) {
            int firstIndex = fio.nextInt() - 1; // read int
            int secondIndex = fio.nextInt() - 1; // read int
            ListNode toModify = strings.get(firstIndex);
            ListNode toAdd = strings.get(secondIndex);
            toModify.tail.next = toAdd;
            toModify.tail = toAdd.tail;

            lastString = firstIndex;
        }
        ListNode print = strings.get(lastString);
        while (print != null) {
            fio.print(print.getItem());
            print = print.next;
        }

        fio.close(); // important; always close at the end of the code
    }
}

class ListNode {
    /* attributes */
    public String item;
    public ListNode next;
    public ListNode tail;

    /* constructors */
    public ListNode(String val) {
        this(val, null);
    }

    public ListNode(String val, ListNode n) {
        item = val;
        next = n;
        tail = this;
    }

    /* get the next ListNode */
    public ListNode getNext() {
        return next;
    }

    /* get the tail ListNode */
    public ListNode getTail() {
        return tail;
    }

    /* get the item of the ListNode */
    public String getItem() {
        return item;
    }

    /* set the item of the ListNode */
    public void setItem(String val) {
        item = val;
    }

    /* set the next reference */
    public void setNext(ListNode n) {
        next = n;
    }

    /* set the tail reference */
    public void setTail(ListNode t) {
        tail = t;
    }

    @Override
    public String toString() {
        String output = item;
        if (this.next != null) {
            // output += this.next.toString();
            output += "next";
        }
        if (this.tail != null) {
            // output += this.tail.toString();
            output += "tail";
        }
        return output;
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
