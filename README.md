# Run Your Selenide Scripts on LambdaTest Selenium Grid

[Selenide](http://selenide.org/) Integration with [LambdaTest](https://www.lambdatest.com/).

<a href="https://www.automation.lambdatest.com">![LambdaTest Logo](https://www.lambdatest.com/static/images/logo.svg)</a>

<a href="http://selenide.org/"><img src ="http://selenide.org/images/selenide-logo-big.png" height = "110"></a>

## LambdaTest with Selenide Automation Framework : 

Selenide automation framework acts as a wrapper of Selenium WebDriver to help you write crisp and concise UI tests in Java. Selenide automatically takes care of browser shutdown, handling timeouts, test debugging, and StaleElement Exception, so you only focus on improving your business logic.

LambdaTest integration with Selenide automation framework will help you pace your test automation effort even further. Using our on-cloud Selenium Grid you will be able to run your automation script on more than 2000 real browsers & browser versions running across numerous devices & operating systems.

## Prerequisites for running tests using Selenide automation framework :

## Environment Setup 

### 1. Java Installation
   
#### For Windows :
   
   You can download Java for Windows from [here](http://www.java.com/en/download/manual.jsp)
   
   Run the installer and follow the setup wizard to install Java.
   
   create a new JAVA_HOME environment variable and set variable value as path value to JDK folder.
   
   #### This is Windows environment variable location :
   Control Panel > All Control Panel Items > System > Advanced system settings > Environment Variables
   
   ![altext](https://github.com/keshavissar001/selenide-testng-sample/blob/keshavissar001-patch-1/Img1.png)
   
#### For Linux :
   
   use this command :
   ```
   sudo apt-get install openjdk-8-jre
   ```
#### For Mac
   
   Java should already be present on Mac OS X by default.
   
   ### 2. Maven Installation
   
   Install Maven from [here](https://maven.apache.org/install.html)
   
### 3. LambdaTest Authentication Credentials
   
  Make sure you have your LambdaTest credentials with you to run test automation scripts with Jest on LambdaTest Selenium Grid. You can obtain these credentials from the [LambdaTest Automation Dashboard](https://automation.lambdatest.com/) or through [LambdaTest Profile](https://accounts.lambdatest.com/detail/profile).
  Set LambdaTest Username and Access Key in environment variables.
* For Linux/macOS:
 `export LT_USERNAME="YOUR_USERNAME"
  export LT_ACCESS_KEY="YOUR ACCESS KEY"`

* For Windows:
 `set LT_USERNAME="YOUR_USERNAME"
  set LT_ACCESS_KEY="YOUR ACCESS KEY"`

## Setting Up The First Project For Selenide Automation Testing 

   **Step 1:** Clone this [GitHub repository for Selenide framework](https://github.com/LambdaTest/selenide-testng-sample). To clone the file, run the below command in your terminal or command prompt:
   
   `git clone https://github.com/LambdaTest/selenide-testng-sample.git` 
 
   **Step 2:** After cloning, you'll have a zip file downloaded in your system. Right click on the zip file and extract files in your desired location.
   
   **Step 3:** Open terminal or command prompt and bring the pointer to the same folder where you extracted the cloned repository.  
	![altext](https://github.com/keshavissar001/images/blob/master/SetPath.png)

  **Step 4:** Under the folder **“selenide-testng-sample-master”**, add your LambdaTest `username` and `accessKey` to the `src/test/resources/conf` in “selenide-testng-sample-master”
 [For Lambdatest Credentials, Go to Lambdatest Profile Page](https://accounts.lambdatest.com/profile) 
  
**Note:** The *conf.json* files will help you to specify the configurations/capabilities over which you wish your Selenide test scripts to run. It is necessary to put your LambdaTest authentication credentials i.e. ***your LambdaTest Access Key*** & ***your LambdaTest Username*** in *conf.json* file to run your Selenide script over LambdaTest Selenium Grid. 

Here is **single.conf.json** file to setup mandatory details to run at LambdaTest. We're running our first script over Windows 10 and Google Chrome 72 as specified in the environment and we're also passing few capabilities to specify the build name and whether we want network logs, console logs, and more for our test execution.

```

{
	"server": "hub.lambdatest.com",
	
	"user": "YOUR_USERNAME",      //put Your User Name here
	
	"key": "YOUR_ACCESS_KEY",     //put Your Access Key here

	"capabilities": {
		"build": "Java Selenide Single",
		"visual": true,
		"network": true,
		"console": true,
		"tunnel": false
	},

	"environments": {
		"chrome": {
		    "platform": "windows 10",
			"browserName": "chrome",
			"version":"72"
		}
	}
}

```
**Note:** You can easily find these capabilities using the LambdaTest Desired Capabilities Generator.

![LambdaTest Desired Capabilities Generator](https://www.lambdatest.com/blog/wp-content/uploads/2019/11/Lambdatest_capability_generator.png)

   
## Running Your First Selenium Automation Test Over LambdaTest Selenium Grid

**Note:** Make sure to install the mandatory Selenium dependencies for Maven by running the below command :

    mvn compile 
  
![altext](https://github.com/keshavissar001/images/blob/master/mvnCompile.png)

Let’s start with a simple Selenium Remote Webdriver test first. This Selenide script below tests whether the expected title is same as that of given page.

This is **LambdaTestSetup.java** file that is executed before and after every method for setting up the remote webdriver and desired capabilities :

```

package com.lambdatest;

import java.io.FileReader;

import java.net.URL;

import java.util.Iterator;

import java.util.Map;

import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;

import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Parameters;

import com.codeborne.selenide.WebDriverRunner;

public class LambdaTestSetup {
	public RemoteWebDriver driver;
	public String status="failed";

	public static String username;
	public static String accessKey;
	public static String sessionId;

	@BeforeMethod(alwaysRun = true)
	@Parameters(value = { "config", "environment" })
	public void setUp(String config_file, String environment) throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/conf/" + config_file));
		JSONObject envs = (JSONObject) config.get("environments");

		DesiredCapabilities capabilities = new DesiredCapabilities();

		Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
		Iterator it = envCapabilities.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
		}

		Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
		it = commonCapabilities.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (capabilities.getCapability(pair.getKey().toString()) == null) {
				capabilities.setCapability(pair.getKey().toString(),
						(pair.getValue().toString().equalsIgnoreCase("true")
								|| (pair.getValue().toString().equalsIgnoreCase("false"))
										? Boolean.parseBoolean(pair.getValue().toString())
										: pair.getValue().toString()));
			}
		}
		capabilities.setCapability("name", this.getClass().getName());

		username = System.getenv("LT_USERNAME");
		if (username == null) {
			username = (String) config.get("user");
		}

		accessKey = System.getenv("LT_ACCESS_KEY");
		if (accessKey == null) {
			accessKey = (String) config.get("key");
		}

		driver = new RemoteWebDriver(
				new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		sessionId = driver.getSessionId().toString();

		WebDriverRunner.setWebDriver(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
	    driver.executeScript("lambda-status="+status);
		driver.quit();
	}

}

```

Now, here is the sample test file **SingleTest.java file** which is to be executed for the automation test through LambdaTest Selenium Grid.

```

package com.lambdatest;

import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Selenide.open;

import static com.codeborne.selenide.Selenide.sleep;

import static com.codeborne.selenide.Selenide.title;

import org.openqa.selenium.By;

import org.testng.Assert;

import org.testng.annotations.Test;

public class SingleTest extends LambdaTestSetup {

	@Test
	public void test() throws Exception {

		open("http://www.google.co.uk");

		$(By.name("q")).setValue("LambdaTest").pressEnter();

		sleep(2000);

		Assert.assertEquals(title(), "LambdaTest - Google Search");

		status = "passed";
	}

}

```

Next, let us look into the XML file **single.testng.xml** that is used to run the Selenide test over LambdaTest Selenium Grid.

```

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Single">
	<test name="SingleTest">
		<parameter name="config" value="single.conf.json"/>
		<parameter name="environment" value="chrome"/>
		<classes>
			<class name="com.lambdatest.SingleTest"/>
		</classes>
	</test>
</suite>

```

### Execute Single Selenide Test Over LambdaTest Selenium Grid

You would need to execute the below command in your terminal/cmd :

```
   mvn test -P single
```
      
This is the screenshot of the output :

   ![altext](https://github.com/keshavissar001/images/blob/master/singleTestResult.png)

## Executing Parallel Tests In Selenide Automation Framework

One of the most important features of LambdaTest Selenium grid is the ability to run your test cases in parallel. What that means is that if you have more than one concurrent session, you can run your test cases on more than one machine at a time, which greatly cuts down your test times. To put it in perspective, if you have 100 test cases each with an average run time of 1 minute, without parallel testing it would take 100 minutes to execute. However, with 2 concurrent sessions, you can run 2 test cases in parallel at a time and can cut down the build’s test time to 50 minutes. With four concurrent sessions, it would cut down to 25 minutes. With eight, well you got the picture.

Here is **parallel.conf.json** file to setup mandatory details to run at LambdaTest. You would need to put your LambdaTest authentication credentials (Access key & Username) :

```

{
	"server": "hub.lambdatest.com",
	
	"user": "YOUR_USERNAME",      //put Your User Name here
	
	"key": "YOUR_ACCESS_KEY",     //put Your Access Key here

	"capabilities": {
		"build": "Java Selenide Parallel",
		"visual": true,
		"network": true,
		"console": true,
		"tunnel": false
	},

	"environments": {
		"chrome": {
		    "platform": "windows 10",
			"browserName": "chrome",
			"version":"72"
		},
		"firefox": {
			"platform": "windows 8.1",
			"browserName": "firefox",
			"version":"65"
		},
		"safari": {
			"platform": "macOS Mojave",
			"browserName": "safari",
			"version":"12"
		}
	}
}

```

This is parallel.testng.xml file that is used to run the test :

```

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Parallel" thread-count="3" parallel="tests">
	<test name="SingleTestChrome">
    <parameter name="config" value="parallel.conf.json"/>
    <parameter name="environment" value="chrome"/>
    <classes>
      <class name="com.lambdatest.SingleTest"/>
    </classes>
	</test>

	<test name="SingleTestFirefox">
    <parameter name="config" value="parallel.conf.json"/>
    <parameter name="environment" value="firefox"/>
    <classes>
      <class name="com.lambdatest.SingleTest"/>
    </classes>
	</test>

	<test name="SingleTestSafari">
    <parameter name="config" value="parallel.conf.json"/>
    <parameter name="environment" value="safari"/>
    <classes>
      <class name="com.lambdatest.SingleTest"/>
    </classes>
	</test>
</suite>

```

### Execute Selenide Tests In Parallel Over LambdaTest Selenium Grid

You would need to execute the below command in your terminal/cmd :

```
   mvn test -P parallel
```
   

This is the screenshot of the output :

![altext](https://github.com/keshavissar001/images/blob/master/ParallelResult.png)

## Executing Test Suite In Selenide Automation Framework

Similarly, if you wish to run your entire Java Selenide test suite then you can use the below **suite.conf.json** and specify your desired capabilities along with your LambdaTest authentification credentials.
```

{
	"server": "hub.lambdatest.com",
	
	"user": "YOUR_USERNAME",      //put Your User Name here
	
	"key": "YOUR_ACCESS_KEY",     //put Your Access Key here

	"capabilities": {
		"build": "Java Selenide Suite",
		"visual": true,
		"network": true,
		"console": true,
		"tunnel": false
	},

	"environments": {
		"chrome": {
			"platform": "windows 10",
			"browserName": "chrome",
			"version":"72"
		},
		"firefox": {
			"platform": "windows 8.1",
			"browserName": "firefox",
			"version":"65"
		},
		"safari": {
			"platform": "macOS Mojave",
			"browserName": "safari",
			"version":"12"
		},
		"edge": {
			"platform": "windows 10",
			"browserName": "MicrosoftEdge",
			"version":"18"
		}
	}
}

```

Now, here are the files in the Selenide test suites which will all be executed over LambdaTest Selenium Grid Cloud.

#### SuiteTest01.java

```

package com.lambdatest.suite;

import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Selenide.open;

import static com.codeborne.selenide.Selenide.sleep;

import static com.codeborne.selenide.Selenide.title;

import org.openqa.selenium.By;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.lambdatest.LambdaTestSetup;

public class SuiteTest01 extends LambdaTestSetup {

	private String status="failed";
	
	@Test
	public void test() throws Exception {

		open("http://www.google.co.uk");

		$(By.name("q")).setValue("LambdaTest").pressEnter();

		sleep(2000);

		Assert.assertEquals(title(), "LambdaTest - Google Search");
		status="passed";
	}

	
}

```

#### SuiteTest02.java

```

package com.lambdatest.suite; 

import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Selenide.open;

import static com.codeborne.selenide.Selenide.sleep;

import static com.codeborne.selenide.Selenide.title;

import org.openqa.selenium.By;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.lambdatest.LambdaTestSetup;

public class SuiteTest02 extends LambdaTestSetup {
  
   @Test
    public void test() throws Exception {

	   open("http://www.google.co.uk");

        $(By.name("q")).setValue("LambdaTest Automation").pressEnter();

        sleep(2000);

        Assert.assertEquals(title(), "LambdaTest Automation - Google Search");
        status="passed";
    }
   
}

```

#### SuiteTest03.java

```

package com.lambdatest.suite;

import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Selenide.open;

import static com.codeborne.selenide.Selenide.sleep;

import static com.codeborne.selenide.Selenide.title;

import org.openqa.selenium.By;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.lambdatest.LambdaTestSetup;

public class SuiteTest03 extends LambdaTestSetup {

    @Test
    public void test() throws Exception {

    	open("http://www.google.co.uk");

        $(By.name("q")).setValue("LambdaTest Live Testing").pressEnter();

        sleep(2000);

        Assert.assertEquals(title(), "LambdaTest Live Testing - Google Search");
        status="passed";
       }
   
}

```

#### SuiteTest04.java

```

package com.lambdatest.suite;

import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Selenide.open;

import static com.codeborne.selenide.Selenide.sleep;

import static com.codeborne.selenide.Selenide.title;

import org.openqa.selenium.By;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.lambdatest.LambdaTestSetup;

public class SuiteTest04 extends LambdaTestSetup {
	
    @Test
    public void test() throws Exception {

    	open("http://www.google.co.uk");

        $(By.name("q")).setValue("LambdaTest Pricing").pressEnter();

        sleep(2000);

        Assert.assertEquals(title(), "LambdaTest Pricing - Google Search");
        status="passed";
       }
  
}

```

This is **suite.testng.xml** file that is used to run the test :

```

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Pool" parallel="tests">
    <test name="1" thread-count="3" parallel="classes">
        <parameter name="config" value="suite.conf.json"/>
        <parameter name="environment" value="chrome"/>
        <classes>
            <class name="com.lambdatest.suite.SuiteTest01"/>
            <class name="com.lambdatest.suite.SuiteTest02"/>
            <class name="com.lambdatest.suite.SuiteTest03"/>
            <class name="com.lambdatest.suite.SuiteTest04"/>
        </classes>
    </test>

    <test name="2" thread-count="3" parallel="classes">
        <parameter name="config" value="suite.conf.json"/>
        <parameter name="environment" value="firefox"/>
        <classes>
            <class name="com.lambdatest.suite.SuiteTest01"/>
            <class name="com.lambdatest.suite.SuiteTest02"/>
            <class name="com.lambdatest.suite.SuiteTest03"/>
            <class name="com.lambdatest.suite.SuiteTest04"/>
        </classes>
    </test>

    <test name="3" thread-count="3" parallel="classes">
        <parameter name="config" value="suite.conf.json"/>
        <parameter name="environment" value="firefox"/>
        <classes>
            <class name="com.lambdatest.suite.SuiteTest01"/>
            <class name="com.lambdatest.suite.SuiteTest02"/>
            <class name="com.lambdatest.suite.SuiteTest03"/>
            <class name="com.lambdatest.suite.SuiteTest04"/>
        </classes>
    </test>

    <test name="4" thread-count="3" parallel="classes">
        <parameter name="config" value="suite.conf.json"/>
        <parameter name="environment" value="firefox"/>
        <classes>
            <class name="com.lambdatest.suite.SuiteTest01"/>
            <class name="com.lambdatest.suite.SuiteTest02"/>
            <class name="com.lambdatest.suite.SuiteTest03"/>
            <class name="com.lambdatest.suite.SuiteTest04"/>
        </classes>
    </test>
</suite>

```

### Execute The Selenide Test Suite Over LambdaTest Selenium Grid Cloud

You would need to execute the below command in your terminal/cmd :

```
   mvn test -P suite
```

These are the screenshot of the output :
   
![altext](https://github.com/keshavissar001/images/blob/master/SuiteResult1%20(2).png)

![altext](https://github.com/keshavissar001/images/blob/master/SuitrResult2.png)

 Want to calculate that how many parallel sessions you need by using our [Parallel Test Calculator](https://www.lambdatest.com/concurrency-calculator?ref=github)
 
 
Below we see a screenshot that depicts our Selenide code is running over different browsers i.e Chrome, Firefox and Safari on the LambdaTest Selenium Grid Platform. The results of the test script execution along with the logs can be accessed from the LambdaTest Automation dashboard.

![altext](https://github.com/keshavissar001/images/blob/master/AutomationLogs.png)

##  Testing Locally Hosted or Privately Hosted Projects

To help you perform cross browser testing of your locally stored web pages, LambdaTest provides an SSH(Secure Shell) tunnel connection with the name Lambda Tunnel. With Lambda Tunnel, you can test your locally hosted files before you make them live over the internet. You could even perform cross browser testing from different IP addresses belonging to various geographic locations. You can also use LambdaTest Tunnel to test web-apps and websites that are permissible inside your corporate firewall.

* Set tunnel value to True in test capabilities
> OS specific instructions to download and setup tunnel binary can be found at the following links.
>    - [Windows](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Windows)
>    - [Mac](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+MacOS)
>    - [Linux](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Linux)

After setting tunnel you can also see the active tunnel in our LambdaTest dashboard:


![tn](https://github.com/Apoorvlt/test/blob/master/tn.PNG)

### Important Note:
Some Safari & IE browsers, doesn't support automatic resolution of the URL string "localhost". Therefore if you test on URLs like "http://localhost/" or "http://localhost:8080" etc, you would get an error in these browsers. A possible solution is to use "localhost.lambdatest.com" or replace the string "localhost" with machine IP address. For example if you wanted to test "http://localhost/dashboard" or, and your machine IP is 192.168.2.6 you can instead test on "http://192.168.2.6/dashboard" or "http://localhost.lambdatest.com/dashboard".

## About LambdaTest
[LambdaTest](https://www.lambdatest.com/) is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. LambdaTest supports all programming languages and frameworks that are supported with Selenium, and have easy integrations with all popular CI/CD platforms. It's a perfect solution to bring your [selenium automation testing](https://www.lambdatest.com/selenium-automation) to cloud based infrastructure that not only helps you increase your test coverage over multiple desktop and mobile browsers, but also allows you to cut down your test execution time by running tests on parallel.

### Resources

##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)
##### [Selenide Documentation](https://selenide.org/documentation.html)
