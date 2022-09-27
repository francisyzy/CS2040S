
***REMOVED***
import java.io.*;
import java.util.*;

public class conformity {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int frosh = fio.nextInt(); // read int

        // int[][] courses = new int[frosh][5];
        // String[] courses = new String[frosh];
        long[] courses = new long[frosh];
        // ArrayList<int> courses = new ArrayList<int>();
        // ArrayList<HashMap<Integer>> courses = new ArrayList<HashMap<Integer>>();
        for (int i = 0; i < frosh; i++) {
            int[] froshie = new int[5];
            for (int j = 0; j < 5; j++) {
                int courseCode = fio.nextInt();
                froshie[j] = courseCode;
            }
            Arrays.sort(froshie);
            //Set capacity to 3 because module code is 3 digits long
            StringBuilder sb = new StringBuilder(3);
            for (int j = 0; j < 5; j++) {
                sb.append(froshie[j]);
            }
            try {

                courses[i] = Long.parseLong(sb.toString());
            } catch (Exception e) {
                // TODO: handle exception
                fio.print(e);
            }

        }

        Arrays.sort(courses);

        ArrayList<Integer> duplicates = new ArrayList<Integer>();

        // HashSet<Long> findDup = new HashSet<Long>();
        // for (Long string : courses) {
        // if(!findDup.add(string)){

        // }
        // }
        int curr = 0;
        int duplicate = 0;
        // fio.println(courses[0]);
        for (int i = 1; i < courses.length; i++) {
            // fio.println(courses[i]);
            if (courses[i] == courses[curr]) {
                duplicate++;
            } else {
                duplicates.add(duplicate);
                duplicate = 0;
                curr++;
            }
        }
        int maxNumberOfDuplicates = Collections.max(duplicates);
        if (maxNumberOfDuplicates == 0) {
            fio.println(frosh);
        } else {
            fio.println(maxNumberOfDuplicates + 1);
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
