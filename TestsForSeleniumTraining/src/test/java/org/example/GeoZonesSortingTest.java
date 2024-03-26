package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class GeoZonesSortingTest {

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
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> countriesList = driver
                .findElements(By.cssSelector("tr.row td:nth-child(3) a"));

        ArrayList<String> countryLinks = new ArrayList<>();
        countriesList.stream().forEach(e -> countryLinks.add(e.getAttribute("href")));

        List<String> zones = new ArrayList<>();
        List<Select> selectList = new ArrayList<>();

        for(String country : countryLinks){

            driver.navigate().to(country);
            driver.findElements(By.cssSelector("select[name*=zone_code]")).stream().forEach(e
                    -> selectList.add(new Select(e)));

            selectList.stream().forEach(select -> zones.add(select.getFirstSelectedOption().getAttribute("text")));
            assertTrue(isSorted(zones));
            zones.clear();
            selectList.clear();
        }

    }

    public boolean isSorted(List<String> stringList){
        ArrayList<String> sortedList = new ArrayList<>(stringList);
        Collections.sort(sortedList);

        for (int i=0; i<stringList.size(); i++){
            if(!stringList.get(i).equals(sortedList.get(i))){
                return false;
            }
        }
        return true;
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
