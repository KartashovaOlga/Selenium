package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
public class AllLinksInMenuTest {

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
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        ArrayList<String> linksList = new ArrayList<>();
        driver.findElements(By.cssSelector("#box-apps-menu a")).stream().forEach(e
                -> linksList.add(e.getAttribute("href")));


        ArrayList<String> innerLinksList = new ArrayList<>();
        for (String link: linksList){
            driver.navigate().to(link);
            assertEquals(1, driver.findElements(By.xpath("//td[@id='content']//h1")).size());

            driver.findElements(By.cssSelector("#box-apps-menu li ul li a")).stream().forEach(e
                    -> innerLinksList.add(e.getAttribute("href")));

            for (String innerLink : innerLinksList){
                driver.navigate().to(innerLink);
                assertEquals(1, driver.findElements(By.xpath("//td[@id='content']//h1")).size());

            }
            innerLinksList.clear();
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
