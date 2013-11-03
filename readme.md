The containing project is an experiment at utilizing Geb / Spock / Gradle to perform functional testing against a website.  It is based on the work provided by:

https://github.com/geb/geb-example-gradle

The following is from the readme file from the above mentioned project and will work with this experiment:

The build is setup to work with HTMLUnit, FireFox and Chrome. Have a look at the `build.gradle` and the `src/test/resources/GebConfig.groovy` files.

The following commands will launch the tests with the individual browsers:

    ./gradlew htmlunitTest
    ./gradlew chromeTest
    ./gradlew firefoxTest

To run with all, you can run:

    ./gradlew test
