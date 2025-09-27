package com.sentinel.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AdminLTEUITest {

    WebDriver driver;
    String baseUrl = "https://adminlte.io/themes/v3";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @Test
    public void verifySidebarMenu() {
        WebElement sidebar = driver.findElement(By.cssSelector("aside.main-sidebar"));
        Assert.assertTrue(sidebar.isDisplayed(), "Sidebar menu is visible");
        System.out.println("Sidebar menu verified.");
    }

    @Test
    public void verifyNavbar() {
        WebElement navbar = driver.findElement(By.cssSelector("nav.main-header"));
        Assert.assertTrue(navbar.isDisplayed(), "Navbar is visible");
        System.out.println("Navbar verified.");
    }

    @Test
    public void verifyDashboardContent() {
        // The homepage content wrapper
        WebElement content = driver.findElement(By.cssSelector("div.content-wrapper"));
        Assert.assertTrue(content.isDisplayed(), "Dashboard content is visible");

        // Optional: verify specific text inside
        WebElement welcomeText = content.findElement(By.tagName("h1"));
        Assert.assertTrue(welcomeText.getText().length() > 0, "Dashboard has a heading");
        System.out.println("Dashboard content verified.");
    }

    @AfterClass
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
