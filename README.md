# GIA Coding Exercise

This repository contains solutions for 5 Java coding exercises and 2 SQL problems.

Each question is implemented in its own package for clarity.

---

## Setup

1. Clone the repository:
   git clone https://github.com/Niha-21/gia-coding-exercise.git

2. Open the project in IDE 

3. Build the project 

4. Navigate to the required package and run the `main` method

---

## Q1: Schema-Aware Log Processor

- **Package:** `com.example.q1`

### Description
Parses log lines and generates:
- Count of occurrences per log level
- Most recent message per log level

Handles malformed or corrupted input gracefully.

### Supported Log Levels
INFO, WARN, ERROR, DEBUG

### Usage
1. Run `LogProcessor.main()`
2. Enter log lines in console
3. Press Enter on an empty line to finish

### Input Example
```
"2026-03-25 10:00:00 [INFO] Login success"  
"2026-03-25 10:01:00 [ERROR] Connection failed"  
"2026-03-25 10:02:00 [INFO] User logout" 
```

### Output Example
```
Counts: {"INFO": 2, "ERROR": 1}
Latest Messages: {"INFO": "User logout", "ERROR": "Connection failed"} 
```

### Technical Details
- Skips null, empty, or malformed lines
- Lines with unmatched quotes are ignored
- Uses enum for log level validation
- Output is JSON-like for readability

---

## Q2 – Financial Ledger Engine

- **Package:** `com.example.q2`

### Description 
Calculate precise active and legacy balances from a list of financial transactions, handling aging and exclusions.

### Technical Details
- **Precision:** `BigDecimal` is used for all amounts.  
- **Rounding:** Banker's rounding (`RoundingMode.HALF_EVEN`) is applied.  
- **Aging Rules:**  
  - **Active Balance:** Sum of `CREDIT` (+) and `DEBIT` (-) transactions **less than 30 days old**.  
  - **Legacy Balance:** Sum of `CREDIT` (+) and `DEBIT` (-) transactions **30 days or older**.  
- **Exclusions:** Ignore transactions of type `PENDING`.  
- **Flooring:** Negative balances are floored to `0.00`.  

- **Supported Transaction Types:** `CREDIT`, `DEBIT`, `PENDING`

### Usage
1. Run `LedgerEngine.main()`.
2. Enter transaction details as prompted.
3. When asked, enter `Y` to add another transaction or `N` to finish.

### Input Example
```
For timestamp, enter how many days ago the transaction occurred (0 for today).
Transaction ID: 1
Amount: 100.00
Type (CREDIT/DEBIT/PENDING): CREDIT
Days ago (0 for today): 0
Add another transaction? (Y/N): Y
Transaction ID: 2
Amount: 50.00
Type (CREDIT/DEBIT/PENDING): DEBIT
Days ago (0 for today): 40
Add another transaction? (Y/N): N
```

### Output Example
```
Active Balance: 100.00
Legacy Balance: 0.00
```

---

## Q3 – Longest Substring Without Repeating Characters

- **Package:** `com.example.q3`

### Description
Find the length of the longest substring in a given string that contains no repeating characters.

### Technical Details
- **Algorithm:** Sliding window technique using a `HashMap` to track the last seen index of each character.  
- **Time Complexity:** O(n), where n is the length of the input string.  
- **Space Complexity:** O(min(n, charset size)) for storing character indices.  
- **Behavior:**  
  - Repeated characters in the current window shift the start of the substring past the previous occurrence.  
  - Supports all printable characters including letters, digits, symbols, and spaces.  

### Usage
1. Run `LongestSubstringFinder.main()`.
2. Enter the string when prompted.
3. The program outputs the length of the longest substring without duplicate characters.

### Input Example
```
Enter a string: pwwkew
```

### Output Example
```
Length of longest substring without repeating characters: 3
```

---

## Q4 – Reverse Integer

- **Package:** `com.example.q4`

### Description
Given a signed 32-bit integer, reverse its digits.  
If the reversed integer overflows the 32-bit signed integer range `[-2147483648, 2147483647]`, the program returns `0`.

### Technical Details
- **Algorithm:** Extract digits one by one using modulo (`% 10`) and division (`/ 10`), then build the reversed number.  
- **Overflow Handling:** Before multiplying and adding a digit, checks are made to ensure the result stays within the 32-bit signed integer range.  
- **Time Complexity:** O(n), where n is the number of digits in the integer.  
- **Space Complexity:** O(1), uses only a few integer variables.  
- **Behavior:**  
  - Handles negative numbers automatically.  
  - Leading zeros in the reversed number are removed.  
  - Returns `0` for inputs whose reversal exceeds the 32-bit signed range.  

### Usage
1. Run `ReverseInteger.main()`.
2. Enter a signed integer (without commas) when prompted.
3. The program outputs the reversed integer or `0` if it overflows.

### Input Example
```
Enter an integer: -123
```

### Output Example
```    
Reversed integer: -321
```

---