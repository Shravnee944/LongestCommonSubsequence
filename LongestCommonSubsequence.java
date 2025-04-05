// Java program to compute and reconstruct the Longest Common Subsequence (LCS)
public class LongestCommonSubsequence {

    // Function to calculate the length of the LCS
    public static int findLCSLength(String s1, String s2) {
        int m = s1.length(); // Get length of first string
        int n = s2.length(); // Get length of second string

        // DP table to store the LCS length for substrings
        int[][] dp = new int[m + 1][n + 1];

        // Building the DP table iteratively
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match, add 1 to the previous diagonal value
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // Otherwise, take the maximum of left or top cell
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Return the LCS length which is stored in the bottom-right cell
        return dp[m][n];
    }

    // Function to reconstruct the actual LCS string
    public static String getLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Fill the DP table as in the previous method
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Backtracking to construct the LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;

        // Traverse the DP table from bottom-right to top-left
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                // If characters match, add to LCS and move diagonally
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                // Move up if the value above is greater
                i--;
            } else {
                // Otherwise, move left
                j--;
            }
        }

        // Reverse the constructed LCS since it was built backwards
        return lcs.reverse().toString();
    }

    // Main method to test the program
    public static void main(String[] args) {
        // Sample input strings
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        // Compute the LCS length
        int lcsLength = findLCSLength(s1, s2);
        System.out.println("Length of LCS: " + lcsLength);

        // Compute and display the LCS itself
        String lcs = getLCS(s1, s2);
        System.out.println("LCS: " + lcs);
    }
}
