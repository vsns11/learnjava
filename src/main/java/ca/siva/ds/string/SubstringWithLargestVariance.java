package ca.siva.ds.string;

// Leetcode: 2272
public class SubstringWithLargestVariance {
    class Solution {

        // Time Complexity:- O(26*26*N) -> Generalized to O(N)
        // Space Complexity:- O(26) -> Generalized to O(1)
        public int largestVariance(String s) {
            // Null/empty check for input String
            if (s == null || s.length() == 0) return 0;

            // Init int array and increment char counts per s
            int[] frequency = new int[26];
            for (int i = 0; i < s.length(); i++)
            {
                frequency[s.charAt(i) - 'a']++;
            }

            int max = 0;

            // Loop through every possible pair of chars in the initial string (charA, charB),
            // comparing them to find max variance in substrings
            for (int charA = 0; charA < 26; charA++)        // Adds O(26) to time complexity
            {
                for (int charB = 0; charB < 26; charB++)    // Adds O(26) to time complexity
                {
                    int remainA = frequency[charA];
                    int remainB = frequency[charB];

                    // When i and j are equal or either or both i and j are 0, continue to next iteration of loop
                    if (charA == charB || remainA == 0 || remainB == 0)
                    {
                        continue;
                    }

                    // Custom Kadanes Algorithm - Dynamic Programming custom implementation to find the substring
                    // with the max variance between the frequency of charA and charB
                    int charAFreq = 0;
                    int charBFreq = 0;

                    // Compare charA and charB by iterating the string s and comparing the count of charA with charB
                    for (int k = 0; k < s.length(); k++)    // Adds O(N) to time complexity
                    {
                        int currChar = s.charAt(k) - 'a';   // Get the current char in the string

                        // Current char in String is charB
                        if (currChar == charB)
                        {
                            charBFreq++;                    // Increment frequency for charB
                        }
                        else if (currChar == charA)
                        {
                            charAFreq++;                    // Increment frequency of charA
                            remainA--;                      // Decrement charA's remaining in the array
                        }

                        // Assuming charAFreq is larger than charBFreq, get max variance
                        // if (charAFreq > 0)
                        if (charBFreq > charAFreq && charAFreq != 0)
                        {

                            max = Math.max(max, charBFreq - charAFreq);
                        }

                        // charBFreq should always be larger than charAFreq
                        // Reset the frequency counts for charA and charB so charB frequency can be maximized as long as
                        // there's at least 1 charA remaining on the right side of the pointer
                        if (charBFreq < charAFreq && remainA >= 1)
                        {
                            charBFreq = 0;
                            charAFreq = 0;
                        }
                    }
                }
            }

            return max;
        }
    }
}
