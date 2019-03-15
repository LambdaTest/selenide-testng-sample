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
