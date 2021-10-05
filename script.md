* integration tests with external components (PlainNotificationRepositoryTest)
** there are different options (embedded, in-memory, abstractions)
** use the real thing, but comfortably!
* test containers (testcontainers.org)
** tool agnostic
** pure java solution
** ryuk container management (kill zombie-containers)
* demo
** add a container to a plain JUnit test (BasicContainerTest)
** add a container to a Spring Boot test (PlainNotificationRepositoryTest)
** wire container with ApplicationContextInitializer (NotificationRepositoryTest)
* outlook
** specific containers for popular components (their APIs as Java methods)
** JDBC Url Magic (application.properties)
** GUI tests with Selenium, Firefox and recorded videos of test runs