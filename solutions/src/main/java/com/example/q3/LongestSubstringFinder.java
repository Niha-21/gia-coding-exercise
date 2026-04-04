package com.example.q3;

import java.util.HashMap;
import java.util.Scanner;

public class LongestSubstringFinder {
    private String inputString;

    public LongestSubstringFinder(String s) {
        this.inputString = s;
    }

    /**
     * Calculates the length of the longest substring without repeating characters
     * in the input string.
     * Uses a sliding window approach with a HashMap to track the last seen index of
     * each character.
     * Time Complexity: O(n), Space Complexity: O(min(n, charset size))
     *
     * @return the length of the longest substring without duplicate characters
     */
    public int lengthOfLongestSubstring() {
        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int startIndex = 0;

        for (int currentIndex = 0; currentIndex < inputString.length(); currentIndex++) {
            char currentChar = inputString.charAt(currentIndex);

            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= startIndex) {
                startIndex = charIndexMap.get(currentChar) + 1;
            }

            charIndexMap.put(currentChar, currentIndex);

            maxLength = Math.max(maxLength, currentIndex - startIndex + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String userInput = scanner.nextLine();

        LongestSubstringFinder finder = new LongestSubstringFinder(userInput);
        int result = finder.lengthOfLongestSubstring();

        System.out.println("Length of longest substring without repeating characters: " + result);

        scanner.close();
    }
}