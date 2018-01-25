## Creating mailgun connection check

### Create java project

Include required dependencies according to [mailgun-documentation](http://mailgun-documentation.readthedocs.io/en/latest/libraries.html#java)

* [UniRest REST client](http://unirest.io/java.html) from [Kong](https://github.com/Kong/unirest-java)

### Execute oneliner

Include next line as a nagios command.

        java -cp /home/jperez/mailgunapicheck.jar org.orcid.mailgun.Check
        WARN: No response from API.