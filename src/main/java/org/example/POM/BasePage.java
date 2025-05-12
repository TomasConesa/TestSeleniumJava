package org.example.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;

    protected BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void setup() {
        driver.manage().window().maximize();
    }

    public void url(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }

    protected WebElement findElement(By locator) {
        return driver.findElement(locator); // En este caso utilizo el find element del driver
    }

    protected void sendText(String inputText, By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        this.findElement(locator).clear(); // Aqui utilizo el creado arriba
        this.findElement(locator).sendKeys(inputText);
    }

    protected void sendKey(CharSequence key, By locator)  {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        this.findElement(locator).sendKeys(key);
    }

    protected void click(By locator)  {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }

    protected String getText(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return this.findElement(locator).getText();
    }
}
