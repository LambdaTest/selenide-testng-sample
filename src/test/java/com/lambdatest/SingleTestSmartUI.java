package com.lambdatest;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class SingleTestSmartUI extends LambdaTestSetupSmartUI {

	@Test
	public void test() throws Exception {

		open("https://lambdatest.github.io/sample-todo-app/");

		$(By.name("li1")).click();
		$(By.name("li2")).click();
		$(By.id("sampletodotext")).setValue("Complete LambdaTest Tutorial.").pressEnter();

		String newElem = $(By.xpath("/html/body/div/div/div/ul/li[6]/span")).getText();
		sleep(2000);

		Assert.assertEquals(newElem, "Complete LambdaTest Tutorial.");

        Map<String, Object> config = new HashMap<>();
        config.put("screenshotName", "<Your Screenshot Name>");
        executeJavaScript("smartui.takeScreenshot", config);

		status = "passed";
	}

}
