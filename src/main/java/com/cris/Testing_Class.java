package com.cris;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testing_Class {

	  WebDriver driver;

	    // Locators for Costco homepage
	    By searchBox = By.id("search-field");
	    By searchButton = By.cssSelector("button[type='submit']");

	    @BeforeClass
	    @Parameters("browser")
	    public void setUp(String browser) {
	        driver = createDriver(browser);
	        driver.manage().window().maximize();
	        driver.get("https://www.costco.com/");
	    }

	    public WebDriver createDriver(String browser) {
	        switch (browser.toLowerCase()) {
	            case "chrome":
	                WebDriverManager.chromedriver().setup();
	                return new ChromeDriver();
	            case "firefox":
	                WebDriverManager.firefoxdriver().setup();
	                return new FirefoxDriver();
	            case "edge":
	                WebDriverManager.edgedriver().setup();
	                return new EdgeDriver();
	            default:
	                throw new IllegalArgumentException("Unsupported browser: " + browser);
	        }
	    }

	    @Test
	    public void validateHomePageTitle() {
	        String title = driver.getTitle();
	        Assert.assertTrue(title.contains("Costco"), "Title does not contain 'Costco'");
	    }

	    @Test
	    public void searchProductTest() {
	        driver.findElement(searchBox).sendKeys("laptop");
	        driver.findElement(searchButton).click();
	        String title = driver.getTitle();
	        Assert.assertTrue(title.contains("laptop"), "Search did not return results for 'laptop'");
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}
