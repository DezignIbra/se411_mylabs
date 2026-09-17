# AI review of the logging strategy

Reviewer: Codex. This review and the implemented changes address the handout's request to evaluate logging with AI and apply recommendations.

## Evaluation and applied improvements

1. Use a separate `private static final Logger` per class so each event identifies its source.
2. Preserve the assignment's level mapping: lifecycle INFO, successful wallet activity DEBUG, custom exception creation WARN, caught failures ERROR.
3. Log a failure stack trace once at the application boundary. Wallet operations throw their exceptions instead of logging and rethrowing the same stack trace.
4. Avoid logging account balances, amounts, account identifiers or other financial details. Parameterized logging records the operation name without concatenation.
5. Put application-end logging in a `finally` block so it runs after handled failures.
6. Reject non-finite values, invalid amounts and overflow before mutation so invalid operations cannot produce success logs.
7. Select one SLF4J provider at a time with Maven profiles. Keep the no-provider stage available for the required warning demonstration.
8. Configure timestamp, level, thread and class in file output. Ignore generated logs in Git. Replace the demo log on each run for clear level comparisons.

## Assignment constraints and production considerations

Logging in an exception constructor is usually avoided because creating an exception does not necessarily mean it will be thrown, and callers may also log it. This lab explicitly requires a WARN when `InsufficientFundsException` is created, so that message is retained. The ERROR stack trace is emitted only at the handling boundary. An expected business rejection might be WARN rather than ERROR in production, but the assignment explicitly asks for ERROR.

The starter uses double balances; its public API is preserved here. For production money, use decimal values, as demonstrated in Lab 05. A production service would also need correlation identifiers, retention and rotation; replacing a file on each run is only for this exercise.

Maven resolves the handout's Log4j coordinate to reload4j. The Logback profile is an alternative demonstration; do not activate both profiles together.
