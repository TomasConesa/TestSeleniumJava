package org.example.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage{

    private By nombre = By.id("firstName");
    private By apellido = By.id("lastName");
    private By email = By.id("email");
    private By password = By.id("password");
    private By repassword = By.id("repassword");
    private By btnRegistrarse = By.className("btn-primario");//*[@id="root"]/main/div/form/button
    private By btnCrearCuenta = By.className("btn-secundariO");
    private By gracias = By.className("txt-gracias");
    private By exito = By.className("txt-exito");
    private By mailRegister = By.className("form-feedback");
    private By passwordDis = By.className("small-feedback");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickCrearCuenta() {
        this.click(btnCrearCuenta);
    }

    public void escribirNombre(String name) {
        this.sendText(name, nombre);
    }

    public void escribirApellido(String name) {
        this.sendText(name, apellido);
    }

    public void escribirCorreo(String mail) {
        this.sendText(mail, email);
    }

    public void escribirContraseña(String pass) {
        this.sendText(pass, password);
    }

    public void repetirContraseña(String pass) {
        this.sendText(pass, repassword);
    }

    public void clickRegistrarse() throws InterruptedException {
        this.click(btnRegistrarse);
        Thread.sleep(1000);
    }

    public String cuentaCreadaGracias() {
        String res = this.getText(gracias);
        System.out.println("Resultado Card value: " + res);
        return res;
    }

    public String cuentaCreadaExito() {
        String res = this.getText(exito);
        System.out.println("Resultado Card value: " + res);
        return res;
    }

    public String mailRegistrado() {
        String res = this.getText(mailRegister);
        System.out.println("Resultado Card value: " + res);
        return res;
    }

    public String contraseñaNoCoinciden() {
        String res = this.getText(passwordDis);
        System.out.println("Resultado Card value: " + res);
        return res;
    }
}
