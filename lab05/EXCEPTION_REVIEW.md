# Exception handling review

Reviewer: Codex. This is not a GitHub Copilot review. The Copilot-specific lab step remains to be completed by the student.

## Findings applied

- `InvalidAgeException` and `InsufficientFundsException` are checked exceptions in a dedicated exceptions package.
- Age 17 fails and age 18 passes. The caller handles the expected validation failure.
- A withdrawal exceeding the wallet balance throws the specific custom exception.
- The wallet validates the amount and available funds before changing wallet or simulated bank balances.
- Nonpositive withdrawal amounts are rejected with `IllegalArgumentException`; missing values are rejected explicitly.
- Money uses `BigDecimal` constructed from strings to avoid binary floating-point rounding.
- Domain classes throw exceptions; the application boundary catches them and communicates the result. Exceptions are not silently swallowed.
- Tests cover the age boundary, exact decimal transfers, full-balance withdrawal, insufficient funds, and invalid amounts.

## Scope

This is an in-memory teaching simulation, not a connection to a real bank. Concurrency, persistent transactions and bank integration are outside the handout's requirements.

## Actual GitHub Copilot review

Pending. Record the real review date, findings, accepted changes and test results here after completing that required step.
