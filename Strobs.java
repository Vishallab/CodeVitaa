
import java.util.*;

public class Strobs {

    //finding the maximum number of substring that be remove fromthe string main
        public static int findMaxRemoveSubstr(String[] substrings, String mainString) {
            //finding all possible order that substring can be removed
            List<List<String>> allPossibleOrders = genAllPermutations(Arrays.asList(substrings));
            int maxSubstrRemove = 0;

            //trying all things orders and then calculate the max number of sub string that can be reomove
            for (List<String> order : allPossibleOrders) {
                String currentString = mainString;
                int substringsRemoved = 0;

                //removing each each substing in the currnt order
                for (String substring : order) {
                    //checking the substring that can be present in the string
                    int index = currentString.indexOf(substring);
                    if (index != -1) {
                        //if substr is found remove it
                        currentString = currentString.substring(0, index) + currentString.substring(index + substring.length());
                        substringsRemoved++; // counter increse
                    }
                }
                //now storign the max number od substring
                maxSubstrRemove = Math.max(maxSubstrRemove, substringsRemoved);
            }

            return maxSubstrRemove;
        }

    //sunction for get all orders of the sbstring
        public static List<List<String>> genAllPermutations(List<String> substrings) {
            List<List<String>> permutations = new ArrayList<>();
            generatePermutationsHelper(substrings, 0, permutations);
            return permutations;
        }

    //recursively helper genrate the permutations of the sub string

        private static void generatePermutationsHelper(List<String> substrings, int startIndex, List<List<String>> permutations) {

            if (startIndex == substrings.size()) {
                permutations.add(new ArrayList<>(substrings));
                return;
            }

            //try each substr at the currnt posion
            for (int i = startIndex; i < substrings.size(); i++) {
                // Swap the current substring with the start position
                Collections.swap(substrings, i, startIndex);

                //generate permutations for the next positio
                generatePermutationsHelper(substrings, startIndex + 1, permutations);

                // Backtrack swapping back the substrings
                Collections.swap(substrings, i, startIndex);
            }
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            int n = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after the number

            String[] substrings = scanner.nextLine().trim().split(" ");

            String mainString = scanner.nextLine().trim();

            // my logic function call
            int result = findMaxRemoveSubstr(substrings, mainString);

            System.out.print(result);

            scanner.close();//closiing the scanner to avoid leaks
        }
    }
