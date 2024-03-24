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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CountriesSortingTest {

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
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> countriesList = driver
                .findElements(By.cssSelector("tr.row"));

        List<String> countriesNamesList = new ArrayList<>();
        List<String> notOneZoneList = new ArrayList<>();
        String timeZone;
        WebElement element;

        for(WebElement country : countriesList){
            element  = country.findElement(By.cssSelector("td:nth-child(5)"));
            countriesNamesList.add(element.getAttribute("textContent"));

            timeZone = country.findElement(By.cssSelector("td:nth-child(6)")).getAttribute("textContent");
            if(!timeZone.equals("0")){
                notOneZoneList.add(element.findElement(By.cssSelector("a")).getAttribute("href"));
            }
        }
        assertTrue(isSorted(countriesNamesList));

        if(!notOneZoneList.isEmpty()){

            List<WebElement> zoneElements = new ArrayList<>();
            List<String> zoneString = new ArrayList<>();
            for (String zone:notOneZoneList){
                driver.navigate().to(zone);

                zoneElements =  driver.findElements(By.cssSelector("table[id=table-zones] td:nth-child(3) input[type=hidden]"));

               for (WebElement zoneElement :zoneElements){
                   zoneString.add(zoneElement.getAttribute("value"));
               }

               assertTrue(isSorted(zoneString));
               zoneString.clear();
               zoneElements.clear();
            }
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
