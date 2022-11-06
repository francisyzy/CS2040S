

import java.io.*;
import java.util.*;

public class babynames {
    public static void main(String[] args) {
        FastIO fio = new FastIO(); // create new instance

        Trie boyNames = new Trie();
        Trie girlNames = new Trie();
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
                    // if (genderSuitability == 0) {
                    // totalNum += boyNames.subSet(start, end).size();
                    // totalNum += girlNames.subSet(start, end).size();
                    // } else if (genderSuitability == 1) {
                    // totalNum += boyNames.subSet(start, end).size();
                    // } else if (genderSuitability == 2) {
                    // totalNum += girlNames.subSet(start, end).size();
                    // }
                    System.out.println(boyNames.query(start, end));
                    System.out.println(girlNames.query(start, end));
                    // System.out.println(boyNames.containsNode(start));
                    // System.out.println(girlNames.containsNode(start));
                    // boyNames.print();
                    // girlNames.print();
                    // fio.println(totalNum); // print the "..." contents with newline at the end
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

class TrieNode {
    int[] children = new int[26];
    // private final Map<Character, TrieNode> children = new HashMap<>();
    private boolean endOfWord;

    int[] getChildren() {
        return children;
    }

    boolean isEndOfWord() {
        return endOfWord;
    }

    void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    void print() {
        System.out.println(children);
    }

    // int query(String query) {
    //     // int next = query.charAt(0); // ascii of A
    //     int sum = 0;
    //     for (char l : query.toCharArray()) {
    //         System.out.println(children.get(l));
    //         if (children.get(l) != null) {
    //             sum += children.get(l).isEndOfWord() ? 1 : 0;
    //         }
    //     }
    //     // for (int i = next; i < 26 + 65; i++) {
    //     // System.out.println(children.get(Character.toChars(i)));
    //     // if (children.get(Character.toChars(i)) != null) {
    //     // System.out.println(children.get(Character.toChars(i)).isEndOfWord());
    //     // sum += children.get(Character.toChars(i)).isEndOfWord() ? 1 : 0;
    //     // }
    //     // }
    //     return sum;
    // }

    int query(String query){
        return query(query, 0);
    }

    int query(String query, int index) {
        if (index < query.length() - 1) {
            int next = query.charAt(index) - 'A';
            int sum = 0;
            for (int i = next + 1; i < 26; i++) {
                if (children[i] != 0)
                    sum += children[i];
            }
            if (children[next] != null) {
                sum += children[next].query(query, index + 1);
            }
            return sum;
        } else {
            int next = query.charAt(index) - 'A';
            int sum = 0;
            for (int i = next; i < 26; i++) {
                if (children[i] != null)
                    sum += children[i].numWords;
            }
            return sum;
        }
    }
}

class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void print() {
        root.print();
    }

    void add(String word) {
        TrieNode current = root;

        for (char l : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
        }
        current.setEndOfWord(true);
    }

    boolean remove(String word) {
        return delete(root, word, 0);
    }

    boolean containsNode(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }

    boolean isEmpty() {
        return root == null;
    }

    int query(String start, String end) {
        return root.query(start) - root.query(end);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return false;
            }
            current.setEndOfWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord();

        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
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
