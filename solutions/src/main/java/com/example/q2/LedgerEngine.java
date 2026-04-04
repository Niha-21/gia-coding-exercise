package com.example.q2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LedgerEngine {

    /**
     * Summarizes a list of transactions into active and legacy balances.
     * - Active: transactions < 30 days old
     * - Legacy: transactions >= 30 days old
     * - Ignore PENDING transactions
     * - Use BigDecimal with HALF_EVEN rounding
     * - Floor negative balances to zero
     */
    public Summary summarize(List<Transaction> transactions) {
        if (transactions == null)
            transactions = List.of();

        BigDecimal active = BigDecimal.ZERO;
        BigDecimal legacy = BigDecimal.ZERO;
        Instant now = Instant.now();

        for (Transaction tx : transactions) {
            if (tx == null || tx.type() == TransactionType.PENDING)
                continue;

            BigDecimal amt = tx.amount().setScale(2, RoundingMode.HALF_EVEN);
            boolean isActive = tx.timestamp().isAfter(now.minus(30, ChronoUnit.DAYS));

            switch (tx.type()) {
                case CREDIT -> {
                    if (isActive)
                        active = active.add(amt);
                    else
                        legacy = legacy.add(amt);
                }
                case DEBIT -> {
                    if (isActive)
                        active = active.subtract(amt);
                    else
                        legacy = legacy.subtract(amt);
                }
                default -> {
                } // PENDING already skipped
            }
        }

        // Floor negative balances
        if (active.compareTo(BigDecimal.ZERO) < 0)
            active = BigDecimal.ZERO;
        if (legacy.compareTo(BigDecimal.ZERO) < 0)
            legacy = BigDecimal.ZERO;

        return new Summary(active, legacy);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Transaction> transactions = new ArrayList<>();

        System.out.println("For timestamp, enter how many days ago the transaction occurred (0 for today).");

        while (true) {
            System.out.print("Transaction ID: ");
            String id = scanner.nextLine();

            System.out.print("Amount: ");
            BigDecimal amount = new BigDecimal(scanner.nextLine());

            System.out.print("Type (CREDIT/DEBIT/PENDING): ");
            TransactionType type = TransactionType.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Days ago (0 for today): ");
            long daysAgo = Long.parseLong(scanner.nextLine());
            Instant timestamp = Instant.now().minus(daysAgo, ChronoUnit.DAYS);

            transactions.add(new Transaction(id, amount, type, timestamp));

            // Ask user if they want to continue
            System.out.print("Add another transaction? (Y/N): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("Y")) {
                break;
            }
        }

        scanner.close();

        LedgerEngine engine = new LedgerEngine();
        Summary summary = engine.summarize(transactions);

        System.out.println("Active Balance: " + summary.activeBalance().setScale(2, RoundingMode.HALF_EVEN));
        System.out.println("Legacy Balance: " + summary.legacyBalance().setScale(2, RoundingMode.HALF_EVEN));

    }

}