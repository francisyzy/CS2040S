
***REMOVED***
import java.io.*;
import java.util.*;

public class grandpabernie {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        HashMap<String, ArrayList<Integer>> visited = new HashMap<>();
        HashMap<String, Boolean> sorted = new HashMap<>();
        int n = fio.nextInt(); // read int
        while (n > 0) {
            String countryVisited = fio.next();
            int yearVisitedInput = fio.nextInt();
            ArrayList<Integer> yearVisited = visited.get(countryVisited);
            sorted.put(countryVisited, false);
            if (yearVisited == null) {
                ArrayList<Integer> newCountry = new ArrayList<Integer>();
                newCountry.add(yearVisitedInput);
                visited.put(countryVisited, newCountry);
            } else {
                yearVisited.add(yearVisitedInput);
                visited.put(countryVisited, yearVisited);
            }
            n--;
        }
        int q = fio.nextInt(); // read int
        while (q > 0) {
            String countryQuery = fio.next();
            int timeVisited = fio.nextInt();
            if (sorted.get(countryQuery)) {
                ArrayList<Integer> yearVisited = visited.get(countryQuery);
                fio.println(yearVisited.get(timeVisited - 1));

            } else {
                ArrayList<Integer> yearVisited = visited.get(countryQuery);
                yearVisited.sort(null);
                sorted.put(countryQuery, true);
                visited.put(countryQuery, yearVisited);
                fio.println(yearVisited.get(timeVisited - 1));
            }
            q--;
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
