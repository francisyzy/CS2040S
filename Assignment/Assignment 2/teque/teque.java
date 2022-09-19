***REMOVED***
import java.io.*;
import java.util.*;

public class teque {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int noOfOperations = fio.nextInt(); // read int

        FakeCircularArrayList front = new FakeCircularArrayList(noOfOperations * 2);
        FakeCircularArrayList back = new FakeCircularArrayList(noOfOperations * 2);
        for (int i = 0; i < noOfOperations; i++) {
            String operation = fio.next(); // read till next token of space bar
            int number = fio.nextInt(); // Get the number to push into or get
            // Fancy if else statement
            switch (operation) {
                case "push_front":
                    front.addFront(number);
                    // Check if both "ArrayList" are balanced
                    if (front.size() > back.size() + 1) {
                        // Rebalance it if its not balanced
                        back.addFront(front.removeBack());
                    }
                    break;
                case "push_middle":
                    // Check which array to push middle values into
                    if (front.size() < back.size() + 1) {
                        front.addBack(number);
                    } else {
                        back.addFront(number);
                    }
                    break;
                case "push_back":
                    back.addBack(number);
                    // Check if both "ArrayList" are balanced
                    if (back.size() > front.size()) {
                        // Rebalance it if its not balanced
                        front.addBack(back.removeFront());
                    }
                    break;
                default: // get operation
                    // Check if the front is bigger than the queried number
                    // If so, means number belong in front arr
                    if (front.size() > number) {
                        fio.println(front.get(number));
                    } else {
                        // Remove the len of front arr to get the correct index
                        int index = number - front.size();
                        fio.println(back.get(index));
                    }
                    break;
            }

        }

        fio.close(); // important; always close at the end of the code
    }

}

class FakeCircularArrayList {
    Integer[] items;
    int firstIndex;
    int lastIndex;
    int size = 0;

    FakeCircularArrayList(int totalCapacity) {
        items = new Integer[totalCapacity];
        // Set the indexes to be in the middle to not overflow
        firstIndex = totalCapacity / 2;
        lastIndex = totalCapacity / 2;
    }

    public int size() {
        return size;
    }

    public void addFront(Integer num) {
        if (this.size() == 0) {
            items[firstIndex] = num;
        } else {
            items[--firstIndex] = num;
        }
        size++;
    }

    public void addBack(Integer num) {
        if (size == 0) {
            items[lastIndex] = num;
        } else {
            items[++lastIndex] = num;
        }
        size++;
    }

    //To get the actual index of the item
    public Integer get(int index) {
        return items[index + firstIndex];
    }

    public Integer removeFront() {
        size--;
        Integer num = items[firstIndex];
        items[firstIndex] = null;
        if (this.size() != 0) {
            firstIndex++;
        }
        return num;
    }

    public Integer removeBack() {
        size--;
        Integer num = items[lastIndex];
        items[lastIndex] = null;
        if (this.size() != 0) {
            lastIndex--;
        }
        return num;
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
