# blank-yarn-app

This is a skeleton for Java Yarn application with Spring Boot.
It is based on the [spring yarn example](https://spring.io/guides/gs/yarn-basic/) but have few changes:

- single jar maven build (chooses between client / appmaster / container depending on env variables)
- resource localizer is set up to copy the jar (original example may not work because of this
