import java.io.*;
import java.util.*;

public class sortofsorting {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance
        int students = fio.nextInt(); // read int
        // While there is student coming in
        while (students != 0) {
            ArrayList<student> studentList = new ArrayList<student>();
            studentList.ensureCapacity(students);
            // Get all student names for that class
            for (int i = 0; i < students; i++) {
                studentList.add(new student(fio.next()));
            }
            // Java sort is stable
            studentList.sort((s1, s2) -> s1.compare(s1, s2));
            for (student student : (studentList)) {
                fio.println(student);
            }

            fio.println("");
            // Get next batch of student
            students = fio.nextInt();
        }

        fio.close(); // important; always close at the end of the code
    }
}

class student implements Comparator<student> {
    String name;

    student(String name) {
        this.name = name;
    }

    // the overridden compare method to sort the first two characters only
    @Override
    public int compare(student s1, student s2) {
        return s1.name.substring(0, 2).compareTo(s2.name.substring(0, 2));
    }

    @Override
    public String toString() {
        return this.name;
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
