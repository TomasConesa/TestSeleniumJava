import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class testGoogle {

    public WebDriver driver;

    @Test
    public void test1() {
        driver = new ChromeDriver();
        driver.get("https://google.com");

        driver.getTitle(); // "Google"

        // Aceptar cookies si aparecen
        try {
            WebElement agreeBtn = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Accept') or contains(text(),'Accept all')]")));
            agreeBtn.click();
        } catch (TimeoutException ignored) {}

        WebElement searchBox = driver.findElement(By.name("q"));
        //WebElement searchBtn = driver.findElement(By.name("btnK"));

        searchBox.sendKeys("Selenium");
        searchBox.sendKeys(Keys.ENTER);
        //searchBtn.click();

        searchBox = driver.findElement(By.name("q"));
        searchBox.getAttribute("value"); // "Selenium"

        driver.quit();
    }
}
