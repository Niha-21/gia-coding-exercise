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
"2026-03-25 10:00:00 [INFO] Login success"  
"2026-03-25 10:01:00 [ERROR] Connection failed"  
"2026-03-25 10:02:00 [INFO] User logout" 

### Output Example
Counts: {"INFO": 2, "ERROR": 1} 
Latest Messages: {"INFO": "User logout", "ERROR": "Connection failed"} 

### Notes
- Skips null, empty, or malformed lines
- Lines with unmatched quotes are ignored
- Uses enum for log level validation
- Output is JSON-like for readability

---