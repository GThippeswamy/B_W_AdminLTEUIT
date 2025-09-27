package com.sentinel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormsPage {
    private WebDriver driver;
    private By fileInput = By.cssSelector("input[type='file']");

    public FormsPage(WebDriver driver){
        this.driver = driver;
    }

    public void uploadFile(String filePath){
        WebElement file = driver.findElement(fileInput);
        file.sendKeys(filePath);
    }
}

