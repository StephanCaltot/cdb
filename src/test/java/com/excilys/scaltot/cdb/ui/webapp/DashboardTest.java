package com.excilys.scaltot.cdb.ui.webapp;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.FirefoxDriverManager;

/**
 * @author Caltot Stéphan
 *
 * 22 mars 2017
 */
public class DashboardTest {
  private WebDriver driver;
  private String baseUrl;

  @BeforeClass
  public static void setupClass() {
    FirefoxDriverManager.getInstance().setup();
  }
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/cdb";
  }

  @Test
  public void testDashboard() throws Exception {
    driver.get(baseUrl + "/springcdb");
    driver.findElement(By.id("searchbox")).clear();
    driver.findElement(By.id("searchbox")).sendKeys("apple");
    driver.findElement(By.id("searchsubmit")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("addComputer")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("computerName")).sendKeys("TheSeleniumTest");
    Thread.sleep(1000);
    driver.get(baseUrl + "/springcdb");
    driver.findElement(By.id("searchsubmit")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("Edit")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("deleteSelected")).click();
    Thread.sleep(1000);
    driver.switchTo().alert().dismiss();
    driver.findElement(By.linkText("View")).click();
    Thread.sleep(1000);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
}
