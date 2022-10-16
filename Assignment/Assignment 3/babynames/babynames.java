
***REMOVED***
import java.io.*;
import java.util.*;

public class babynames {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        TreeSet<String> boyNames = new TreeSet();
        TreeSet<String> girlNames = new TreeSet();
        int num = fio.nextInt(); // read int
        while (num != 0) {
            switch (num) {
                case 1: // add suggestion
                    String name = fio.next();
                    int gender = fio.nextInt();
                    if (gender == 1) {
                        boyNames.add(name);
                    } else {
                        girlNames.add(name);
                    }
                    break;
                case 2: // remove suggestion
                    String removeName = fio.next();
                    boyNames.remove(removeName);
                    girlNames.remove(removeName);
                    break;
                case 3: // query
                    String start = fio.next();
                    String end = fio.next();

                    int genderSuitability = fio.nextInt();
                    int totalNum = 0;
                    if (genderSuitability == 0) {
                        totalNum += boyNames.subSet(start, end).size();
                        totalNum += girlNames.subSet(start, end).size();
                    } else if (genderSuitability == 1) {
                        totalNum += boyNames.subSet(start, end).size();
                    } else if (genderSuitability == 2) {
                        totalNum += girlNames.subSet(start, end).size();
                    }
                    fio.println(totalNum); // print the "..." contents with newline at the end
                    break;

                default:
                    System.out.println("Why are you here");
                    break;
            }
            num = fio.nextInt(); // read int
        }

        fio.close(); // important; always close at the end of the code
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
