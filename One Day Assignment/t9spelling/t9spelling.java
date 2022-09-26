***REMOVED***

import java.util.Scanner;

public class t9spelling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int noOfCase = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < noOfCase; i++) {
            StringBuilder t9 = new StringBuilder(": ");
            String input = sc.nextLine();
            // System.out.println(input);
            for (int j = 0; j < input.length(); j++) {
                String numChar;
                switch (input.charAt(j)) {
                    case 'a':
                        numChar = "2";
                        break;
                    case 'b':
                        numChar = "22";
                        break;
                    case 'c':
                        numChar = "222";
                        break;
                    case 'd':
                        numChar = "3";
                        break;
                    case 'e':
                        numChar = "33";
                        break;
                    case 'f':
                        numChar = "333";
                        break;
                    case 'g':
                        numChar = "4";
                        break;
                    case 'h':
                        numChar = "44";
                        break;
                    case 'i':
                        numChar = "444";
                        break;
                    case 'j':
                        numChar = "5";
                        break;
                    case 'k':
                        numChar = "55";
                        break;
                    case 'l':
                        numChar = "555";
                        break;
                    case 'm':
                        numChar = "6";
                        break;
                    case 'n':
                        numChar = "66";
                        break;
                    case 'o':
                        numChar = "666";
                        break;
                    case 'p':
                        numChar = "7";
                        break;
                    case 'q':
                        numChar = "77";
                        break;
                    case 'r':
                        numChar = "777";
                        break;
                    case 's':
                        numChar = "7777";
                        break;
                    case 't':
                        numChar = "8";
                        break;
                    case 'u':
                        numChar = "88";
                        break;
                    case 'v':
                        numChar = "888";
                        break;
                    case 'w':
                        numChar = "9";
                        break;
                    case 'x':
                        numChar = "99";
                        break;
                    case 'y':
                        numChar = "999";
                        break;
                    case 'z':
                        numChar = "9999";
                        break;

                    default:
                        numChar = "0";
                        break;
                }
                if (t9.charAt(t9.length() - 1) == numChar.charAt(0)) {
                    t9.append(" ");
                    t9.append(numChar);
                } else {
                    t9.append(numChar);
                }
            }
            // System.out.println(t9.toString());

            int caseNo = i + 1;

            System.out.println("Case #" + caseNo + t9.toString());
        }
        sc.close();
    }
}

// Scanner sc
// int noOfCase = sc read int
// sc.nextLine() //move cursor
// for (int i = 0, i < noOfCase, i++) {
//     StringBuilder t9 = new StringBuilder(": ")
//     input = sc.nextLine()
//     for (int j = 0 j < input.length() j++) {
//         String numChar
//         switch (input.charAt(j)) {
//             case 'a':
//                 numChar = "2"
//                 break
//             case 'b':
//                 numChar = "22"
//                 break
//             case 'c':
//                     ... for the rest of the chars

//             default:
//                 numChar = "0"
//                 break
//         }
//         if (t9.charAt(t9.length() - 1) == numChar.charAt(0)) {
//             t9.append(" ")
//             t9.append(numChar)
//         } else {
//             t9.append(numChar)
//         }
//     }

//     caseNo = i + 1

//     print("Case #" + caseNo + t9)
// }
// sc.close()