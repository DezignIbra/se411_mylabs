# Lab 06 logging demonstrations

Run commands from this folder. Select exactly one provider profile per run. The default is `log4j`. Maven profiles let the lab stages be repeated without repeatedly editing dependencies.

## Stage 1 SLF4J API without a provider

```bash
mvn -Pno-provider clean package exec:java
```

Expected warning: `No SLF4J providers were found.` Application logging is discarded. Activating this profile disables the default Log4j profile.

## Stage 2 Logback

```bash
mvn -Plogback clean package exec:java
```

Expected: console messages for start/end and the intentionally rejected operations. For DEBUG-level messages:

```bash
mvn -Plogback clean package exec:java -Dlab.log.level=DEBUG
```

## Stage 3 Log4j-style file logging

```bash
mvn clean package exec:java
```

The POM keeps the handout's exact dependency `org.slf4j:slf4j-log4j12:2.0.16`. Maven relocates it to `slf4j-reload4j:2.0.16`; this is expected, not a build error. See the [published POM](https://central.sonatype.com/artifact/org.slf4j/slf4j-log4j12/2.0.16) and [SLF4J manual](https://www.slf4j.org/manual.html).

Open `logs/App/log4j/log.out`. File logging does not send application messages to the console. The file is replaced at each run so old DEBUG lines cannot confuse later ERROR-only comparisons.

Edit the first setting in `src/main/resources/log4j.properties`, then rerun `mvn clean package exec:java` for each level:

| Setting | Application events visible |
| --- | --- |
| `log4j.rootLogger=ERROR, LOG1` | Withdrawal and deposit failures |
| `log4j.rootLogger=WARN, LOG1` | Failures and custom exception creation |
| `log4j.rootLogger=INFO, LOG1` | Above plus application start/end |
| `log4j.rootLogger=DEBUG, LOG1` | Above plus wallet creation and successful deposit/withdrawal |

The handout's early INFO-only example is silent at ERROR. This completed application intentionally raises exceptions, so its ERROR messages remain visible at ERROR level.

Restore INFO before your final commit. Each failure includes a stack trace; continuation lines are part of the same log event.

## Event mapping

| Event | Level | Class |
| --- | --- | --- |
| Application starts and ends | INFO | App |
| Wallet account created | DEBUG | WalletAccount |
| Successful deposit and withdrawal | DEBUG | WalletAccount |
| InsufficientFundsException created | WARN | InsufficientFundsException |
| Thrown exception caught at application boundary | ERROR | App |

The starter package spelling `edu.spu.se411.lab06_logging` is preserved, including in the Maven main-class configuration.

Read [LOGGING_REVIEW.md](LOGGING_REVIEW.md) for the AI evaluation and applied improvements.
