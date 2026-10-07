package com.lambdatest;

import java.io.FileReader;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.codeborne.selenide.WebDriverRunner;

public class LambdaTestSetupSmartUI {
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

        MutableCapabilities capabilities = new MutableCapabilities();
        MutableCapabilities ltOptions = new MutableCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ltOptions.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (ltOptions.getCapability(pair.getKey().toString()) == null) {
                Object value = pair.getValue();
                if (value.toString().equalsIgnoreCase("true") || value.toString().equalsIgnoreCase("false")) {
                    ltOptions.setCapability(pair.getKey().toString(), Boolean.parseBoolean(value.toString()));
                } else {
                    ltOptions.setCapability(pair.getKey().toString(), value.toString());
                }
            }
        }

        ltOptions.setCapability("name", this.getClass().getName());
        ltOptions.setCapability("smartUI.project", "Selenide-Java");

        if(System.getenv("BUILD_NAME")!=null && System.getenv("BUILD_NAME")!=""){
            ltOptions.setCapability("smartUI.build",System.getenv("BUILD_NAME"));
        }

        if (envCapabilities.containsKey("browserName")) {
            capabilities.setCapability("browserName", envCapabilities.get("browserName"));
        }

        capabilities.setCapability("LT:Options", ltOptions);

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        sessionId = driver.getSessionId().toString();

		WebDriverRunner.setWebDriver(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
	    driver.executeScript("lambda-status="+status);
		driver.quit();
	}

}
