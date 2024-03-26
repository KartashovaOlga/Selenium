package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewUserRegistrationTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        System.setProperty("webdriver.chrome.driver", "D:\\DEVELOPMENT\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 11);
    }

    @Test
    public void SimpleTest(){
        driver.navigate().to("http://localhost/litecart");
        driver.findElement(By.cssSelector("form[name=login_form] a")).click();

        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys("FirstName");
        driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys("LastName");
        driver.findElement(By.cssSelector("input[name=address1]")).sendKeys("MilkyWay The Earth");
        driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys("32541");
        driver.findElement(By.cssSelector("input[name=city]")).sendKeys("Omsk");
        driver.findElement(By.cssSelector("select[name=country_code]")).sendKeys("United States");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[name=zone_code]")));
        driver.findElement(By.cssSelector("select[name=zone_code]")).sendKeys("Idaho");

        String email = "test_email" +  System.currentTimeMillis() + "@mail.ru";
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email);

        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys(Keys.HOME + "+79111100800");

        String password = "1234Qwerty";
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(password);

        driver.findElement(By.cssSelector("button[name=create_account]")).click();

        driver.findElement(By.cssSelector("div[id=box-account] .list-vertical li:nth-child(4) a")).click();

        driver.findElement(By.cssSelector("form[name=login_form] input[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("form[name=login_form] input[name=password]")).sendKeys(password);

        driver.findElement(By.cssSelector("form[name=login_form] button[name=login]")).click();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
