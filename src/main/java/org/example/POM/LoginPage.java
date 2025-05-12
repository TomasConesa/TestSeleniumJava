package org.example.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private By mail = (By.id("email"));

    private By password = (By.id("password"));

    private By btnLogin = By.className("btn-primario");

    private By hello = By.className("txt-hola");

    private By name = By.className("txt-nombre");

    private By mailRequired = By.className("small-feedback");

    private By passwordRequired = By.className("small-feedback");

    private By passwordShort = By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/small");

    private By invalidMail = By.xpath("//*[@id=\"root\"]/main/div/form/div[1]/small");

    private By credentials = By.className("form-feedback");



    public void escribirCorreo(String correo) {
        this.sendText(correo, mail);

    }

    public void escribirContraseña(String contraseña) {
        this.sendText(contraseña, password);
    }

    public void clickLogin() {
        this.click(btnLogin);
    }

    public String bienvenida() {
        System.out.println("MENSAJE DE SALUDO: " + this.getText(hello));
        return this.getText(hello);
    }

    public String nombreUsuario() {
        System.out.println("NOMBRE DE USUARIO: " + this.getText(name));
        return this.getText(name);
    }

    public String correoObligatorio() {
        System.out.println("MENSAJE DE ERROR DEL CORREO: " + this.getText(mailRequired));
        return this.getText(mailRequired);
    }

    public String contraseñaObligatoria() {
        System.out.println("MENSAJE DE ERROR DE LA CONTRASEÑA: " + this.getText(passwordRequired));
        return this.getText(passwordRequired);
    }

    public String contraseñaCorta() {
        System.out.println("MENSAJE DE ERROR: " + this.getText(passwordShort));
        return this.getText(passwordShort);
    }

    public String correoInvalido() {
        System.out.println("MENSAJE DE ERROR: " + this.getText(invalidMail));
        return this.getText(invalidMail);
    }

    public String credenciales() {
        System.out.println("MENSAJE DE ERROR: " + this.getText(credentials));
        return this.getText(credentials);
    }
}


