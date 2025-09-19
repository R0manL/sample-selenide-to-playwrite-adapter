# smartit automated testing.

This document describes architecture, how to setup, execute and analyze results of automated tests execution for smartit project.


## Quick Start
1. Create a new `Project from Version Control`.
2. run mvn install.


### Tests execution environment 
#### Requirements:
- java 17+
- browser: chrome (latest version)
- screen resolution (min): 1920 x 1080
- maven 3.9.6+.


## Framework's architecture
```
├── pom.xml
├── readme.md - current readme file
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── smartit
│   │   │           └── bizon
│   │   │               └── qa
│   │   │                   ├── api - all related to API
│   │   │                   │   ├── data - test data that used for api
│   │   │                   │   └── services - methods that implements business logic 
│   │   │                   ├── configs
│   │   │                   │   └── EnvConfig.java - interface that read and parse *.properties
│   │   │                   ├── db - all related to DB (postgress)
│   │   │                   │   ├── dao - contains queries that map DB tables/columns with java methods
│   │   │                   │   │   └── Xxxxx.java - interface for XXXXX table mapping
│   │   │                   │   ├── data - contains predefined data
│   │   │                   │   └── services - methods that used in tests
│   │   │                   │       ├── DBConnection.java
│   │   │                   │       └── XxxxxDBService.java
│   │   │                   ├── ui - all related to UI
│   │   │                   │   ├── data - predefined data
│   │   │                   │   │   ├── enums
│   │   │                   │   │   │   ├── IngestMode.java
│   │   │                   │   │   │   └── UserRole.java
│   │   │                   │   │   └── pojos - describes test entities
│   │   │                   │   │       ├── Xxxx.java
│   │   │                   │   └── pages - page objects that covers business logic
│   │   │                   │       ├── LoginPage.java
│   │   │                   │       ├── PageBase.java
│   │   │                   │       ├── LeftMenu.java
│   │   │                   └── utils - utility methods
│   │   │                       ├── EmailUtils.java
│   │   │                       └── StringUtils.java
│   │   └── resources
│   │       ├── allure.properties - allure reporting properties
│   │       ├── default.properties - common properties (for all envs), props that used for local (from IDE) execution
│   │       ├── qa-env.properties - qa environment specific properties
│   │       ├── test-env.properties - test environment specific properties
│   │       └── simplelogger.properties - slf4 logger config props
│   └── test - all tests and related data are here
│       ├── java
│       │   └── com
│       │       └── smartit
│       │           └── bizon
│       │               └── qa
│       │                   ├── api - contains API tests
│       │                   └── ui - contains UI tests
│       │                       ├── SmokeTests.java - contains smoke tests
│       │                       ├── RegressionTests - contains regression tests
│       │                       ├── CleanupScripts - contains cleanup script for removing test: products, channel, CAs, etc.
│       │                       ├── UITestBase.java
│       └── resources - test related resources (test assets, content, collateral, onix) files.
└── gitignore - list files / folders that sould be ignored during commit.
```


## 3d party libs
- owner - used to simplify work with *.properties, more [info](http://owner.aeonbits.org/docs/welcome/)
- lombok - used to simplify builder classes generation for Users, BusinessUnits, etc., more [info](https://projectlombok.org/features/Builder)
- jdbi 3 - used for simple connection to DB, [more info](https://jdbi.org/#_maven_setup)


## Setup
All properties are stored in */properties files located in /src/main/resources folder.
default.properties - contains all general props that used for local execution (from IDE).
qa-env.properties, test-env.properties - contains ONLY (in addition to general props) environment specific properties;

### IDE
 - lombok. Install the plugin;
 

## Execution 
To run all test with chrome from commandline:
```
mvn test
```

### Parameters
For test execution we can use mix of: [junit5](https://junit.org/junit5/docs/current/user-guide/), [selenide](https://selenide.org/javadoc/current/com/codeborne/selenide/Configuration.html) or parameters defined in /resource/*.properties files.

| Parameter              | Values                          | Description                                                                                      |
|------------------------|---------------------------------|--------------------------------------------------------------------------------------------------|
| -Dgroups               | [group's name separated by ','] | execute tests (e.g ui, api, smoke) marked with specific group name, example: @Tag ("ui)          |
| -Dlang                 | EN / DE                         | run tests using English/German language (on UI).                                                 |
| -Dremote.web.driver.url | url                             | path to the remote Selenoid instance (e.g. -Dremote.web.driver.url=http://localhost:4444/wd/hub).|
| -Dthreads              | 1, 2, 3, 4                      | Number of parallel threads for execution. Note. Max number = number of test users.               |

####Examples
- execute tests from one class:
```
mvn clean test -Dtest=LoginPageTests
```
- execute smoke tests from a local machine on remove instance (e.g. Selenoid): 
```
mvn clean test -Dremote.web.driver.url=http://10.20.5.7:4444/wd/hub -Dgroups=smoke
```

### Test groups
!Note. Since we have different test types and applications, 'groups' must be defined according to the pattern: [ui/api]&[smartbox/collapp]&[any other subgroups] 

- ui - set of GUI tests
- api - set of API tests
- cockpit - e.g. set of cockpit tests

Defining groups in *-groups*, *-excludegroups*, *-includedgroups* we can flexibly define which tests we want to execute:
```
mvn clean test -Dgroups=ui -Dexcludegroups=login -Dincludedgroups=watermark
```

Execute only tests that match both tags: 
```
mvn clean test -Dgroups="smoke&analytics"
```



## Reporting

### slf4j
slf4j + [simpleLogger](http://www.slf4j.org/api/org/slf4j/impl/SimpleLogger.html) has used for the console debug logging. 
Logging level can be set in /test/java/resource/simplelogger.properties file.


## CI/CD
* [AUTO-TESTS-UI-STORYBOOK-MAIN](https://dev.azure.com/smartit-gmbh/smartit/_build?definitionId=325) - UI storybook tests.
* [AUTO-TESTS-UI-STORYBOOK-DEVELOP](https://dev.azure.com/smartit-gmbh/smartit/_build?definitionId=384) - UI storybook tests (development branch).
* [AUTO-TESTS-UI-SMOKE-MAIN](https://dev.azure.com/smartit-gmbh/smartit/_build?definitionId=385) - UI smoke tests. Quick tests that only navigate thorough all pages and check if any errors on the page appears.
* [AUTO-TESTS-API-REGRESSION-MAIN](https://dev.azure.com/smartit-gmbh/smartit/_build?definitionId=350) - API regression tests.


## Test environment:
Automation team required at least one VM that host docker containers and execution results:
 - selenoid - for remote tests execution;
 - selenoid-ui - visualize remote tests execution progress;
 - video-recorder - record video of remote tests execution;
 - chrome-browser (for each execution thread) - contains chrome browser that used for remote tests execution.

Requirements:
 - RAM = 16GB
 - SSH = 16GB+


### Test data
- Test group with name: `TEST_AUTOMATION` must be created under `Artem Denysovv user, visible for `autom_test01` user;
- `autom_test01` user must have admin role to access API;

### JUnit Tags
- for owner: email, e.g. @Tag("rl@test")
- for features: [text without spaces], e.g.  @Tag("wizard_rules")

#### CI/CD
- Create a new VM with ubuntu 24.04
- install nodejs 20+
- install java JDK 17+
- install maven 3.8+

For running tests in headed version:
- install xvfb X server


### Pre / post conditions
Pre/post conditions for Junit5 has implemented using [custom listener](https://junit.org/junit5/docs/current/user-guide/#launcher-api-listeners-custom)
Sample project is [here](https://github.com/khmarbaise/junit-jupiter-listener/blob/main/src/main/java/io/soebes/extension/MyOwnListener.java).


### Known issues
### EOL conversion issue (Collapp tests)
*.csv templates for Collapp tests (located in /resouurces/collapp/) must have windows style (CRLF) end-of-line (EOL).
Git that used in Azure may convert automatically EOL into linux style, and tests will fail. To prevent git from conversion, disable it with:
```
git config --global core.autocrlf false
```

### Playwright missing labs warning
If you get a warning when run azure pipeline about playwright missed libs. Install read this:
https://github.com/microsoft/playwright/issues/30368