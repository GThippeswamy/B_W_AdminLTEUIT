package com.sentinel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactsPage {
    private WebDriver driver;
    private By searchBox = By.cssSelector("input[type='search']");
    private By tableRows = By.cssSelector("table tbody tr");

    public ContactsPage(WebDriver driver){
        this.driver = driver;
    }

    public void searchUser(String email){
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(email);
    }

    public List<WebElement> getAllRows(){
        return driver.findElements(tableRows);
    }

    // Add this missing method
    public String getCellValue(WebElement row, int cellIndex){
        return row.findElements(By.tagName("td")).get(cellIndex).getText();
    }
}

