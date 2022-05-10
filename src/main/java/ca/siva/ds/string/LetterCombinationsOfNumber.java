package ca.siva.ds.string;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode: Q 17
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 */
public class LetterCombinationsOfNumber {
    public List<String> letterCombinations(String digits) {
        String[] x = new String[]{
                "",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz",
        };


        List<String> sb = new ArrayList<String>();

        for (char ch : digits.toCharArray()) {
            sb.add(x[ch - '0']);
        }

        List<String> result = new ArrayList<>();

        helper(
                sb,
                result,
                0,
                digits.length(),
                ""
        );

        return result;

    }


    public void helper(List<String> subSeqString,
                       List<String> result,
                       int startIdx,
                       int maxLimit,
                       String currStr) {

        if (maxLimit == 0 || subSeqString.size() == 0) return;

        if (startIdx == maxLimit) {
            result.add(currStr);
            return;
        }
        String currString = subSeqString.get(startIdx);


        for (int idx = 0; idx < subSeqString.get(startIdx).length(); ++idx) {
            helper(
                    subSeqString,
                    result,
                    startIdx + 1,
                    maxLimit,
                    currStr + subSeqString.get(startIdx).charAt(idx)
            );
        }

    }

}
