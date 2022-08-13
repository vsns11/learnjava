package ca.siva.ds.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Leetcode: 30
public class SubStringWithConcatenationOfAllWords {
    //Approach 1
    //Given n as the length of s, a as the length of words, and b as the length of each word:
    //Leetcode: Time: O(n * a * b - a *b ^2), Space: O(a)
    class Solution1 {

        public boolean IsBothSubString(Map<String, Integer> counter, String s, int idx, int totalWords, int totalWordsLength, int eachWordLength) {
            Map<String, Integer> cnt = new HashMap<>(counter);
            int wordCntr = 0;
            for (int i = idx; i < idx + totalWordsLength; i += eachWordLength) {
                String w = s.substring(i, i + eachWordLength);

                if (cnt.get(w) != null && cnt.get(w) > 0) {
                    cnt.put(w, cnt.get(w) - 1);
                    wordCntr++;
                } else {
                    break;
                }

            }

            return wordCntr == totalWords;

        }

        public List<Integer> findSubstring(String s, String[] words) {
            Map<String, Integer> cnt = new HashMap<>();
            for (String w: words) {
                cnt.put(w, cnt.getOrDefault(w, 0) + 1);
            }

            List<Integer> result = new ArrayList<>();
            int totalWordsLength = words.length * words[0].length();
            int totalWords = words.length;
            int eachWordLength = words[0].length();

            for (int i = 0; i < s.length() - totalWordsLength + 1; ++i) {
                if (IsBothSubString(cnt, s, i, totalWords, totalWordsLength, eachWordLength) ) {
                    result.add(i);
                }
            }
            return result;
        }
    }

    //Approach 2, efficient
    // Time: O(a+n⋅b), Space: O(a + b)
    class Solution2 {
        private HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
        private int n;
        private int wordLength;
        private int substringSize;
        private int k;

        private void slidingWindow(int left, String s, List<Integer> answer) {
            HashMap<String, Integer> wordsFound = new HashMap<>();
            int wordsUsed = 0;
            boolean excessWord = false;

            // Do the same iteration pattern as the previous approach - iterate
            // word_length at a time, and at each iteration we focus on one word
            for (int right = left; right <= n - wordLength; right += wordLength) {

                String sub = s.substring(right, right + wordLength);
                if (!wordCount.containsKey(sub)) {
                    // Mismatched word - reset the window
                    wordsFound.clear();
                    wordsUsed = 0;
                    excessWord = false;
                    left = right + wordLength;
                } else {
                    // If we reached max window size or have an excess word
                    while (right - left == substringSize || excessWord) {
                        String leftmostWord = s.substring(left, left + wordLength);
                        left += wordLength;
                        wordsFound.put(leftmostWord, wordsFound.get(leftmostWord) - 1);

                        if (wordsFound.get(leftmostWord) >= wordCount.get(leftmostWord)) {
                            // This word was an excess word
                            excessWord = false;
                        } else {
                            // Otherwise we actually needed it
                            wordsUsed--;
                        }
                    }

                    // Keep track of how many times this word occurs in the window
                    wordsFound.put(sub, wordsFound.getOrDefault(sub, 0) + 1);
                    if (wordsFound.get(sub) <= wordCount.get(sub)) {
                        wordsUsed++;
                    } else {
                        // Found too many instances already
                        excessWord = true;
                    }

                    if (wordsUsed == k && !excessWord) {
                        // Found a valid substring
                        answer.add(left);
                    }
                }
            }
        }

        public List<Integer> findSubstring(String s, String[] words) {
            n = s.length();
            k = words.length;
            wordLength = words[0].length();
            substringSize = wordLength * k;

            for (String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }

            List<Integer> answer = new ArrayList<>();
            for (int i = 0; i < wordLength; i++) {
                slidingWindow(i, s, answer);
            }

            return answer;
        }
    }

}
