package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StickersTest {

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

        List<WebElement> linksList = driver
                .findElements(By.cssSelector(".product"));
        List<WebElement> stickers;

        for(WebElement element : linksList){

            stickers  = element.findElements(By.cssSelector(".sticker"));

            assertEquals(1, stickers.size());
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
