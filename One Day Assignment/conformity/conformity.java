
***REMOVED***
import java.io.*;
import java.util.*;

public class conformity {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int frosh = fio.nextInt(); // read number of frosh student's course list
        int NUMBER_OF_COURSE = 5;
        int LEN_COURSE_CODE = 3;

        HashMap<Long, Integer> courseOptions = new HashMap<Long, Integer>();
        for (int i = 0; i < frosh; i++) {
            // A student's combination
            int[] froshie = new int[NUMBER_OF_COURSE];
            for (int j = 0; j < NUMBER_OF_COURSE; j++) {
                int courseCode = fio.nextInt();
                froshie[j] = courseCode;
            }
            Arrays.sort(froshie);
            // Set as module code is 3 digits long multiply by number of courses taken
            StringBuilder sb = new StringBuilder(LEN_COURSE_CODE * NUMBER_OF_COURSE);
            // Stringify it to make it one big chunk of text
            for (int j = 0; j < NUMBER_OF_COURSE; j++) {
                sb.append(froshie[j]);
            }
            // Change it back into a number to sort
            long courseOption = Long.parseLong(sb.toString());
            // Get number of students that is in that unique course combination
            int numberOfStudents = courseOptions.containsKey(courseOption) ? courseOptions.get(courseOption) : 0;
            if (numberOfStudents == 0) { // If no students means its a unique course combination, set it to 1 student
                courseOptions.put(courseOption, 1);
            } else { // Update the course's enrolment info
                courseOptions.remove(courseOption);
                courseOptions.put(courseOption, ++numberOfStudents);
            }
        }
        // Get a collection of student enrolment
        int mostStudents = Collections.max(courseOptions.values());
        // Count the most popular courses by looping thru the set of course
        int popularCourses = 0;
        // Find the courses that has the same number as the most popular course
        for (Integer n : courseOptions.values()) {
            if (n == mostStudents) {
                popularCourses++;
            }
        }
        fio.println(popularCourses * mostStudents);

        // // Retry to get a faster timing by breaking the for loop to save n cycles.
        // // Get a collection of student enrolment
        // Collection<Integer> studentEnrolment = courseOptions.values();
        // // Find the course with most students
        // int mostStudents = Collections.max(studentEnrolment);
        // // Make array to sort
        // Integer[] studentEnrolmentArray = new Integer[studentEnrolment.size()];
        // studentEnrolmentArray = studentEnrolment.toArray(studentEnrolmentArray);
        // Arrays.sort(studentEnrolmentArray);

        // // Get number of courses that is the most popular
        // int popularCourses = 0;
        // for (int i = studentEnrolmentArray.length - 1; i >= 0; i--) {
        // if (studentEnrolmentArray[i] == mostStudents) {
        // popularCourses++;
        // // break to save some cycles
        // } else {
        // break;
        // }
        // }
        // // End up it is still not faster
        // fio.println(popularCourses * mostStudents);

        fio.close(); // important; always close at the end of the code
    }
}

// class conformity 
//     psvm 
//         FastIO fio = FastIO

//         int frosh = fio.nextInt

//         HashMap<long, int> courseOptions = HashMap<long, int>
//         for (int i = 0; i < frosh; i++) 
//             int[] froshie = int[5]
//             for (int j = 0; j < 5; j++) 
//                 int courseCode = fio.nextInt
//                 froshie[j] = courseCode
            
//             Arrays.sort(froshie)
//             StringBuilder sb = StringBuilder()
//             for (int j = 0; j < 5; j++) 
//                 sb.append(froshie[j])
            
//             long courseOption = Long.parseLong(sb.toString)
//             int numberOfStudents = courseOptions.containsKey(courseOption) ? courseOptions.get(courseOption) : 0
//             if (numberOfStudents == 0) 
//                 courseOptions.put(courseOption, 1)
//              else 
//                 courseOptions.remove(courseOption)
//                 courseOptions.put(courseOption, ++numberOfStudents)
            
        
//         int mostStudents = Collections.max(courseOptions.values)
//         int popularCourses = 0
//         for (int n in courseOptions.values) 
//             if (n == mostStudents) 
//                 popularCourses++
            
        
//         fio.println(popularCourses * mostStudents)


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
