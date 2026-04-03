package com.example.q1;

import java.util.*;

public class LogProcessor {

    /**
     * Processes a batch of raw log lines and generates a LogReport.
     * - Skips null, empty, or malformed lines.
     * - Counts occurrences of each valid log level.
     * - Stores the most recent message per log level.
     *
     * @param rawLogs List of log lines
     * @return LogReport containing counts and latest messages
     */
    public LogReport processBatch(List<String> rawLogs) {
        if (rawLogs == null)
            rawLogs = Collections.emptyList();

        Map<String, Long> counts = new HashMap<>();
        Map<String, String> latestMessages = new HashMap<>();

        for (String line : rawLogs) {
            if (line == null || line.isBlank())
                continue;

            // Strip surrounding quotes if present, skip line if only one quote
            line = stripQuotes(line);
            if (line == null || line.isBlank())
                continue;

            // Find the log level enclosed in [LEVEL]
            int start = line.indexOf('[');
            int end = line.indexOf(']');
            if (start == -1 || end == -1 || end <= start + 1)
                continue;

            String levelStr = line.substring(start + 1, end).trim();
            String message = line.substring(end + 1).trim();

            // Convert string to LogLevel enum, skip if invalid
            LogLevel level;
            try {
                level = LogLevel.valueOf(levelStr);
            } catch (IllegalArgumentException e) {
                continue; // skip invalid levels
            }

            // Aggregate counts and latest message
            counts.put(level.name(), counts.getOrDefault(level.name(), 0L) + 1);
            latestMessages.put(level.name(), message);
        }

        return new LogReport(counts, latestMessages);
    }

    /**
     * Strips surrounding quotes from a line if both start and end quotes exist.
     * If only one quote exists, the line is considered invalid and returns null.
     *
     * @param line Input log line
     * @return Line without quotes, or null if invalid
     */
    private static String stripQuotes(String line) {
        line = line.trim();
        boolean startsWithQuote = line.startsWith("\"");
        boolean endsWithQuote = line.endsWith("\"");

        // XOR: true if only one of start/end quote exists
        if (startsWithQuote ^ endsWithQuote) {
            return null; // invalid line
        }

        // Remove both quotes if present
        if (startsWithQuote && endsWithQuote && line.length() > 1) {
            line = line.substring(1, line.length() - 1);
        }
        return line;
    }

    /**
     * Converts a map to a JSON-like string with quotes around keys and string
     * values.
     *
     * @param map Map to convert
     * @return JSON-like string representation
     */
    public static String toJson(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (var entry : map.entrySet()) {
            if (!first)
                sb.append(", ");
            sb.append("\"").append(entry.getKey()).append("\": ");
            if (entry.getValue() instanceof String) {
                sb.append("\"").append(entry.getValue()).append("\"");
            } else {
                sb.append(entry.getValue());
            }
            first = false;
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Main method to read log lines from user input, process them, and print
     * results.
     * Input ends when the user enters an empty line.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> logs = new ArrayList<>();

        System.out.println("Enter log lines (empty line to finish):");

        while (true) {
            String input = scanner.nextLine();
            if (input.isBlank())
                break;
            logs.add(input);
        }

        scanner.close();

        LogProcessor lp = new LogProcessor();
        LogReport report = lp.processBatch(logs);

        // Print counts and latest messages in JSON-like format
        System.out.println("\nCounts: " + toJson(report.counts()));
        System.out.println("Latest Messages: " + toJson(report.latestMessages()));
    }

    
}