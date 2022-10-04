
***REMOVED***
import java.io.*;
import java.util.*;

public class workstations {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int n = fio.nextInt(); // read int
        int m = fio.nextInt(); // read int

        PriorityQueue<researcher> researchers = new PriorityQueue<researcher>();
        PriorityQueue<Integer> spareComputer = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) {
            int a = fio.nextInt(); // read int
            int s = fio.nextInt(); // read int
            researcher researcher = new researcher(a, s);
            researchers.add(researcher);
        }
        // int unlocks = 0;
        int saved = 0;

        for (int i = 0; i < n; i++) {
            researcher researcher = researchers.poll();
            if (spareComputer.isEmpty()) {
                // All computers in use
                // unlocks++;
                spareComputer.add(researcher.leavesAfter());
            } else {
                int computerAvailabilityChecker = spareComputer.peek();
                // Check if computer is currently in use
                boolean pcNotInUse = computerAvailabilityChecker <= researcher.arrives;
                // Check if computer is not expired
                boolean pcNotExpired = researcher.arrives <= (computerAvailabilityChecker + m);
                // if pc is expired, remove it and check availability again
                while (!pcNotExpired && !spareComputer.isEmpty()) {
                    spareComputer.poll();
                    // Catch run time error
                    if (!spareComputer.isEmpty()) {
                        computerAvailabilityChecker = spareComputer.peek();
                        pcNotInUse = computerAvailabilityChecker <= researcher.arrives;
                        pcNotExpired = researcher.arrives <= (computerAvailabilityChecker + m);
                    }
                }

                if (pcNotExpired && pcNotInUse) {
                    // Saved an unlock
                    saved++;
                    // remove the computer from the available ones
                    spareComputer.poll();
                    // add the computer back in the pile of available computers after use
                    spareComputer.add(researcher.leavesAfter());
                } else {
                    // Have to unlock a new PC for researcher to use
                    // unlocks++;
                    spareComputer.add(researcher.leavesAfter());
                }
            }
        }
        // fio.println(n - unlocks);
        fio.println(saved);

        fio.close(); // important; always close at the end of the code
    }
}

class researcher implements Comparable<researcher> {
    int arrives;
    int stays;

    researcher(int a, int s) {
        this.arrives = a;
        this.stays = s;
    }

    int leavesAfter() {
        return arrives + stays;
    }

    int locksAt(int lockMins) {
        return leavesAfter() + lockMins;
    }

    @Override
    public int compareTo(researcher incoming) {
        return this.arrives - incoming.arrives;
    }

    @Override
    public String toString() {
        return Integer.toString(arrives);
    }
}

// public class workstations 
//     P S V M
//         n = fio.nextInt
//         m = fio.nextInt

//         PriorityQueue researchers = new PriorityQueue
//         PriorityQueue spareComputer = new PriorityQueue
//         for (i = 0; i < n; i++) 
//             a = fio.nextInt
//             s = fio.nextInt
//             researchers.add(researcher(a, s))
        
//         // unlocks = 0
//         saved = 0

//         for (i = 0; i < n; i++) 
//             researcher = researchers.poll
//             if (spareComputer.isEmpty) 
//                 // All computers in use
//                 // unlocks++
//                 spareComputer.add(researcher.leavesAfter)
//              else 
//                 computerAvailabilityChecker = spareComputer.peek
//                 // Check if computer is currently in use
//                 bool pcNotInUse = computerAvailabilityChecker <= researcher.arrives
//                 // Check if computer is not expired
//                 bool pcNotExpired = researcher.arrives <= (computerAvailabilityChecker + m)
//                 // if pc is expired, remove it and check availability again
//                 while (!pcNotExpired && !spareComputer.isEmpty) 
//                     spareComputer.poll
//                     // Catch run time error
//                     if (!spareComputer.isEmpty) 
//                         computerAvailabilityChecker = spareComputer.peek
//                         pcNotInUse = computerAvailabilityChecker <= researcher.arrives
//                         pcNotExpired = researcher.arrives <= (computerAvailabilityChecker + m)

//                 if (pcNotExpired && pcNotInUse) 
//                     // Saved an unlock
//                     saved++
//                     // remove the computer from the available ones
//                     spareComputer.poll
//                     // add the computer back in the pile of available computers after use
//                     spareComputer.add(researcher.leavesAfter)
//                  else 
//                     // Have to unlock a new PC for researcher to use
//                     // unlocks++
//                     spareComputer.add(researcher.leavesAfter)
        
//         // fio.println(n - unlocks)
//         fio.println(saved)


// class researcher implements 
//     arrives
//     stays

//     researcher(a, s) 
//         this.arrives = a
//         this.stays = s
    

//     leavesAfter 
//         return arrives + stays
    

//     locksAt(lockMins) 
//         return leavesAfter + lockMins
    

//     @Override
//     public compareTo(researcher incoming) 
//         return this.arrives - incoming.arrives
    

//     @Override
//     public String toString 
//         return Integer.toString(arrives)
    



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
