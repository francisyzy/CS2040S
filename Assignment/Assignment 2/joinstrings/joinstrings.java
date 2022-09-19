import java.io.*;
import java.util.*;

public class joinstrings {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int numOfStrings = fio.nextInt(); // read int

        // ArrayList<StringBuilder> st rings = new ArrayList<StringBuilder>();
        ArrayList<ListNode> strings = new ArrayList<ListNode>();
        strings.ensureCapacity(numOfStrings);

        // ListNode[] strings = new ListNode[numOfStrings];
        // TailedLinkedList strings = new TailedLinkedList();
        for (int i = 0; i < numOfStrings; i++) {
            // strings.addBack(fio.nextLine());
            // ListNode string = new ListNode(fio.nextLine());
            strings.add(new ListNode(fio.nextLine()));
            // strings[i] = new ListNode(fio.nextLine());
        }
        // fio.println(strings);

        // String operations
        int lastString = 1;
        for (int i = 0; i < numOfStrings - 1; i++) {
            int first = fio.nextInt() - 1; // read int
            int second = fio.nextInt() - 1; // read int
            ListNode toModify = strings.get(first);
            ListNode toAdd = strings.get(second);
            toModify.tail.next = toAdd;
            toModify.tail = toAdd.tail;
            // ListNode toModify = strings[first];
            // toModify.getTail().setNext(strings.get(second));
            // toModify.setTail(strings.get(second).getTail());
            // toModify.getTail().setNext(strings[second]);
            // toModify.setTail(strings[second].getTail());

            // strings.set(first, strings.get(first).append(strings.get(second)));
            // strings.set(second, null);
            lastString = first;
        }
        // ListNode print = strings[lastString];
        ListNode print = strings.get(lastString);
        // fio.println(lastString);
        // fio.println(strings);
        // while (print.getNext() != null) {
        // fio.print(print.getItem());
        // print = print.getNext();
        // }
        // fio.println(print.getItem());
        while (print != null) {
            fio.print(print.getItem());
            print = print.next;
        }
        // fio.println("");

        // for (int i = 0; i < numOfStrings; i++) {
        // if (strings.get(i) != null) {
        // fio.println(strings.get(i));
        // }
        // }
        // strings.removeAll(Collections.singleton(null));
        // strings.removeIf(Objects::isNull);
        // fio.println(strings.get(0));

        // double real = fio.nextDouble(); // read double
        // String token = fio.next(); // read a single token
        // String line = fio.nextLine(); // read an entire line
        // fio.print("..."); // print the "..." contents
        // fio.println("..."); // print the "..." contents with newline at the end

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
