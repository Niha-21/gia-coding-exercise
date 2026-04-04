package com.example.q4;

import java.util.Scanner;

public class ReverseInteger {

    /**
     * Reverses the digits of a signed 32-bit integer.
     * Returns 0 if the reversed integer overflows 32-bit signed range.
     *
     * @param x the input integer
     * @return the reversed integer or 0 on overflow
     */
    public int reverse(int x) {
        int reversed = 0;

        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            // Check for overflow before multiplying
            if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (reversed < Integer.MIN_VALUE / 10 || (reversed == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            reversed = reversed * 10 + digit;
        }

        return reversed;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        String input = scanner.nextLine();

        try {
            int x = Integer.parseInt(input);
            ReverseInteger reverser = new ReverseInteger();
            System.out.println("Reversed integer: " + reverser.reverse(x));
        } catch (NumberFormatException e) {
            System.out.println("Input is not a valid 32-bit signed integer.");
        }
    }
}