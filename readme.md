## Creating mailgun connection check

### Create java project

Include required dependencies according to [mailgun-documentation](http://mailgun-documentation.readthedocs.io/en/latest/libraries.html#java)

* [UniRest REST client](http://unirest.io/java.html) from [Kong](https://github.com/Kong/unirest-java)

### Creating executable file

Maven project is configured to package the executable jar with all its dependencies.

        mvn clean compile package

### Execute oneliner

Include next line as a nagios command.

        find target/ -name *.jar
                target/apicheck-1.0.jar
                target/apicheck-1.0-jar-with-dependencies.jar
        java -cp target/apicheck-1.0-jar-with-dependencies.jar org.orcid.mailgun.Check
                WARN: No response from API.
