# Selenide-TestNG-Selenium-Sample
![Java](https://opengraph.githubassets.com/f96dfa788d8b4c91ef7da019ce236c7c88212eb5a750f2fdcaf9ac3b7f99044e/LambdaTest/selenide-testng-sample)

### Prerequisites
1. Install and set environment variable for java.
    * Windows - https://www.oracle.com/java/technologies/downloads/
    * Linux - ```  sudo apt-get install openjdk-8-jre  ```
    * MacOS - Java should already be present on Mac OS X by default.
2 Install and set environment varibale for Maven.
    * Windows - https://maven.apache.org/install.html
    * Linux/ MacOS -  [Homebrew](http://brew.sh/) (Easier)
    ```
     install maven
    ```
    
### Run your First Test
1. Clone the Selenide-testng-Sample repository. 
```
git clone https://github.com/LambdaTest/selenide-testng-sample
```
2. Next get into Selenide-testng-Sample folder, and import Lamabdatest Credentials. You can get these from lambdatest automation dashboard.
   <p align="center">
   <b>For Linux/macOS:</b>:
 
```
export LT_USERNAME="YOUR_USERNAME"
export LT_ACCESS_KEY="YOUR ACCESS KEY"
```
<p align="center">
   <b>For Windows:</b>

```
set LT_USERNAME="YOUR_USERNAME"
set LT_ACCESS_KEY="YOUR ACCESS KEY"
```
Step 3. Make sure to install the mandatory Selenium dependencies for Maven by running the below command.
```
mvn compile
```
Step 3. Run your first single test.
```
mvn verify -P single
```

### Result of the Test
You can see the test running on LambdaTest [Automation Dashboard](https://automation.lambdatest.com/build)
![altext](https://github.com/LambdaTest/selenide-testng-sample/blob/master/tutorial-images/singleTestResult.png)

### Run Parallel Test
One of the most important features of LambdaTest Selenium grid is the ability to run your test cases in parallel. To run parallel test.
```
mvn test -P parallel
```
#### Result of the Parallel Test
![altext](https://github.com/LambdaTest/selenide-testng-sample/blob/master/tutorial-images/ParallelResult.png)

### Executing Test Suite In Selenide Automation Framework
Run your entire Java Selenide test suite by running the below command.
```
mvn test -P suite
```
#### Result of the Testsuite
![altext](https://github.com/LambdaTest/selenide-testng-sample/blob/master/tutorial-images/SuiteResult1%20(2).png)

![altext](https://github.com/LambdaTest/selenide-testng-sample/blob/master/tutorial-images/SuitrResult2.png)

##  Testing Locally Hosted or Privately Hosted Projects

To help you perform cross browser testing of your locally stored web pages, LambdaTest provides an SSH(Secure Shell) tunnel connection with the name Lambda Tunnel. With Lambda Tunnel, you can test your locally hosted files before you make them live over the internet. You could even perform cross browser testing from different IP addresses belonging to various geographic locations. You can also use LambdaTest Tunnel to test web-apps and websites that are permissible inside your corporate firewall.

* Set tunnel value to True in test capabilities
> OS specific instructions to download and setup tunnel binary can be found at the following links.
>    - [Windows](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Windows)
>    - [Mac](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+MacOS)
>    - [Linux](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Linux)

After setting tunnel you can also see the active tunnel in our LambdaTest dashboard:


![tunnel active](https://github.com/LambdaTest/Robot-Selenium-Sample/blob/master/tutorial-images/tn.PNG)

### Important Note:
Some Safari & IE browsers, doesn't support automatic resolution of the URL string "localhost". Therefore if you test on URLs like "http://localhost/" or "http://localhost:8080" etc, you would get an error in these browsers. A possible solution is to use "localhost.lambdatest.com" or replace the string "localhost" with machine IP address. For example if you wanted to test "http://localhost/dashboard" or, and your machine IP is 192.168.2.6 you can instead test on "http://192.168.2.6/dashboard" or "http://localhost.lambdatest.com/dashboard".

## About LambdaTest
[LambdaTest](https://www.lambdatest.com/) is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. LambdaTest supports all programming languages and frameworks that are supported with Selenium, and have easy integrations with all popular CI/CD platforms. It's a perfect solution to bring your [selenium automation testing](https://www.lambdatest.com/selenium-automation) to cloud based infrastructure that not only helps you increase your test coverage over multiple desktop and mobile browsers, but also allows you to cut down your test execution time by running tests on parallel.

### Resources

##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)
##### [Selenide Documentation](https://selenide.org/documentation.html)

