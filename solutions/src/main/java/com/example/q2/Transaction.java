package com.example.q2;

import java.math.BigDecimal;
import java.time.Instant;

public record Transaction(
    String id,
    BigDecimal amount,
    TransactionType type,
    Instant timestamp) {
}
