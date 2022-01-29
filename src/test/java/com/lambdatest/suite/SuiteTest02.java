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

        open("http://www.google.com");

        $(By.name("q")).setValue("LambdaTest Automation").pressEnter();

        sleep(2000);

        Assert.assertEquals(title(), "LambdaTest Automation - Google Search");
        status = "passed";
    }

}