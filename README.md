# selenide-testng-sample

[Selenide](http://selenide.org/) Integration with [LambdaTest](https://www.lambdatest.com/).

<a href="https://www.automation.lambdatest.com">![LambdaTest Logo](https://www.lambdatest.com/static/images/logo.svg)</a>

<a href="http://selenide.org/"><img src ="http://selenide.org/images/selenide-logo-big.png" height = "110"></a>

## Environment Setup 
1. Global Dependencies
   
   For Windows
   You can download Java for Windows from http://www.java.com/en/download/manual.jsp 
   Run the installer and follow the setup wizard to install Java.
   
   For Linux
   ```
   sudo apt-get install openjdk-8-jre
   ```
   For Mac
   Java should already be present on Mac OS X by default
   
   Install Maven from https://maven.apache.org/install.html
   
2. Lambdatest Credentials
    * Set LambdaTest username and access key in environment variables. It can be obtained from [LambdaTest dashboard](https://automation.lambdatest.com/)    
    example:
    - For linux/mac
    ```
    export LT_USERNAME="YOUR_USERNAME"
    export LT_ACCESS_KEY="YOUR ACCESS KEY"
    
    ```
    - For Windows
    ```
    set LT_USERNAME="YOUR_USERNAME"
    set LT_ACCESS_KEY="YOUR ACCESS KEY"
    
    ```
3. Setup

* Clone the repo
* Install dependencies `mvn compile`
* Update `*.conf.json` files inside the `src/test/resources/conf` directory with your [LambdaTest Username and Access Key](https://accounts.lambdatest.com/profile)

4. Running your tests

- To run a single test, run `mvn test -P single`
- To run a full suite of tests, run `mvn test -P suite`
- To run parallel tests, run `mvn test -P parallel`

 Want to calculate that how many parallel sessions you need by using our [Parallel Test Calculator](https://www.lambdatest.com/concurrency-calculator?ref=github)

#####  Routing traffic through your local machine
- Set tunnel value to `true` in test capabilities
> OS specific instructions to download and setup tunnel binary can be found at the following links.
>    - [Windows](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Windows)
>    - [Mac](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+MacOS)
>    - [Linux](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Linux)

### Important Note:
Some Safari & IE browsers, doesn't support automatic resolution of the URL string "localhost". Therefore if you test on URLs like "http://localhost/" or "http://localhost:8080" etc, you would get an error in these browsers. A possible solution is to use "localhost.lambdatest.com" or replace the string "localhost" with machine IP address. For example if you wanted to test "http://localhost/dashboard" or, and your machine IP is 192.168.2.6 you can instead test on "http://192.168.2.6/dashboard" or "http://localhost.lambdatest.com/dashboard".

## About LambdaTest
[LambdaTest](https://www.lambdatest.com/) is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. All test data generated during testing including Selenium command logs, screenshots generated in testing, video logs, selenium logs, network logs, console logs, and metadata logs can be extracted using [LambdaTest automation APIs](https://www.lambdatest.com/support/docs/api-doc/). This data can then be used for creating custom reports.

### Resources

##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)
##### [Selenide Documentation](https://selenide.org/documentation.html)
