package org.example.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage{

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private By searchBox = (By.id("ciudad"));

    private By searchButton = (By.className("btn-primario"));

    private By searchOk = By.className("categoria");


    public void escribirBusqueda(String ciudad) {
        this.sendText(ciudad, searchBox);
        this.sendKey(Keys.ENTER, searchBox);
    }

    public void clickBuscar() {
        this.click(searchButton);
    }

    public String resultadoBusqueda() throws InterruptedException {
        Thread.sleep(2000);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(searchOk));
        String resp = this.getText(searchOk);
        System.out.println("Resultado de la busqueda: " + resp);
        return resp;

    }
}
