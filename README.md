# SE411 lab solutions

This repository contains the Java solutions for the supplied Labs 02, 03, 05 and 06. Each lab is a separate Maven project that can be imported into Eclipse. Java 17 or newer is required. Lab 04 was not included in this task.

| Folder | Topic | Main work |
| --- | --- | --- |
| lab02 | Generics | PrintableList, NumberBox, typed pipeline, wildcards |
| lab03 | JUnit | Three tests for the supplied Stack class and a deliberate failure demonstration |
| lab05 | Exceptions | Age validation and wallet-to-bank transfer simulation |
| lab06 | SLF4J | Logging providers, levels, file output and logging review |

From the repository root, run all tests and package all projects:

```bash
mvn clean verify
```

Run a lab application from its own folder:

```bash
cd lab02
mvn clean package exec:java
```

Use the same command in `lab05` or `lab06`. Lab 03 is a testing exercise: use `mvn clean test`.

Read [START_HERE.md](START_HERE.md) for Eclipse import, lab demonstrations and Git submission steps. Read [VALIDATION.md](VALIDATION.md) for the recorded verification results.

## Chapter 01 Git

The first chapter is an introduction to Git.
