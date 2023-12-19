package com.gdn.qa.training;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Assert;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void waitForEnableCondition(WebDriver driver,WebElement e, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(e));
    }
    public static  void implicitWait(WebDriver driver, int time){
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public static void main( String[] args ) throws InterruptedException
    {
        Home home = new Home();


        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.yatra.com/");
        driver.manage().window().maximize();
        WebElement bus = driver.findElement(By.xpath("//a[@id=\"booking_engine_buses\"]"));
        bus.click();
//        implicitWait(driver, 10);
        Thread.sleep(1000);
        WebElement clickFromCity = driver.findElement(By.id("BE_bus_from_station"));

        clickFromCity.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name=\"bus_leaving\"]")).sendKeys("Coimbatore");

        Thread.sleep(2000);
        WebElement clickFrom = driver.findElement(By.xpath("//div[@class=\"ac_results origin_ac\"]/ul/div/div/div/li"));
        clickFrom.click();
//

        WebElement clickToCity = driver.findElement(By.id("BE_bus_to_station"));
        clickToCity.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name=\"bus_dest\"]")).sendKeys("Bangalore");
        Thread.sleep(400);
        driver.findElement(By.xpath("//div[@class=\"ac_results dest_ac\"]/ul/div/div/div/li")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("BE_bus_depart_date")).click();
        Thread.sleep(1000);
         WebElement date = driver.findElement(By.xpath("//td[@data-date=\"28/12/2023\"]"));
         home.clickButton(date);
        Thread.sleep(1000);

        WebElement searchButton = driver.findElement(By.xpath("//input[@id=\"BE_bus_search_btn\"]"));
        home.clickButton(searchButton);
        Thread.sleep(1000);

        WebElement nightDeparture = driver.findElement(By.xpath("//div[@class=\"flex cp xs1 hide-md arr\"]"));
        home.clickButton(nightDeparture);
        Thread.sleep(1000);

        WebElement busTypeCheckBox = driver.findElement(By.xpath("//span[text()=\"Filters\"]//following::div/span"));
        home.clickButton(busTypeCheckBox);
        Thread.sleep(1000);

        WebElement sleeperCheckBox = driver.findElement(By.xpath("//span[text()=\"AC Sleeper\"]"));
        home.clickButton(sleeperCheckBox);

        WebElement applyFilters = driver.findElement(By.xpath("//div[@class=\"layout filter-buttons row\"]/button[@class=\"btn-red mrI10 v-btn theme--light\"]"));
        home.clickButton(applyFilters);
        Thread.sleep(1000);
        WebElement searchBuses = driver.findElement(By.xpath("//div[@class=\"srp-body\"]/child::div[position()=4]/div/child::div[position()=6]/button"));
        home.clickButton(searchBuses);
        Thread.sleep(1000);

        System.out.println("Lower deck");
        List<WebElement> listOfSeatsFirstColumn = driver.findElements(By.xpath("//div[@id=\"tab-lower\"]/div/div/div[@class=\"seat-body\"]/child::ul[position()=3]/li"));
//        int size = listOfSeatsFirstColumn.size();
        List<WebElement> listOfSeatsSecondColumn = driver.findElements(By.xpath("//div[@id=\"tab-lower\"]/div/div/div[@class=\"seat-body\"]/child::ul[position()=5]/li"));
        List<WebElement> listOfSeatsThirdColumn = driver.findElements(By.xpath("//div[@id=\"tab-lower\"]/div/div/div[@class=\"seat-body\"]/child::ul[position()=6]/li"));

        boolean previous = false;String chosen= "";

            for (WebElement element : listOfSeatsFirstColumn) {
                String className = element.getAttribute("class");
                String snum = element.getAttribute("snum");
                if (!className.contains("reserved") && snum != null) {
                    if (previous == true) {
                        element.click();
                        System.out.println(className + " " + snum + "ch");
                        break;
                    } else {
                        previous = true;
                    }
                } else previous = false;

            }
            if(previous == false) {
                for (WebElement element : listOfSeatsSecondColumn) {
                    String className = element.getAttribute("class");
                    String snum = element.getAttribute("snum");
                    if (!className.contains("reserved") && snum != null) {
                        if (previous == true) {
                            element.click();
                            System.out.println(className + " " + snum + "ch");
                            break;
                        } else {
                            previous = true;
                        }
                    } else previous = false;
                }
            }
        if(previous == false) {
            for (WebElement element : listOfSeatsThirdColumn) {
                String className = element.getAttribute("class");
                String snum = element.getAttribute("snum");
                if (!className.contains("reserved") && snum != null) {
                    if (previous == true) {
                        element.click();
                        System.out.println(className + " " + snum + "ch");
                        break;
                    } else {
                        previous = true;
                    }
                } else previous = false;
            }
        }

        System.out.println(driver.getTitle());
        String cost = driver.findElement(By.xpath("//ul[@class=\"amount\"]/li/span")).getText();
        System.out.println(cost);

//        driver.quit();


    }
}
