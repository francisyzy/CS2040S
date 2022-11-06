
import java.io.*;
import java.util.*;

public class whatdoesthefoxsay {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        int numOfTestCase = fio.nextInt(); // read int
        for (int i = 0; i < numOfTestCase; i++) {
            String[] sounds = fio.nextLine().split(" ");
            String specificAnimalSound = fio.nextLine();
            HashSet<String> animalSoundsSet = new HashSet<>();
            while (true) {
                String[] animalSounds = specificAnimalSound.split(" goes ");
                if (animalSounds.length > 1) {
                    animalSoundsSet.add(animalSounds[1]);
                } else {
                    break;
                }
                specificAnimalSound = fio.nextLine();
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < sounds.length; j++) {
                if (!animalSoundsSet.contains(sounds[j])) {
                    sb.append(sounds[j]);
                    sb.append(' ');
                }
            }
            fio.println(sb.toString());
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
