import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.POM.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ExtentFactory;

import java.time.Duration;

public class LoginTest {

    public WebDriver driver;
    public WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/ReporteLogin.html");
    static ExtentReports extentReports;

    @BeforeAll
    public static void createReport() {
        extentReports = ExtentFactory.getInstance();
        extentReports.attachReporter(info);
    }

    @BeforeEach
    public void preconditions() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.setup();
        loginPage.url("https://digital-booking-front.digitalhouse.com/login");
    }

    @Test
    @Tag("LOGIN")
    public void loginExitoso() {
        ExtentTest test = extentReports.createTest("Login exitoso");
        test.log(Status.INFO, "Comienzo de nuestro test de login");

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirCorreo("toteiro@gmail.com");
        loginPage.escribirContraseña("test123");
        test.log(Status.PASS, "Se cargaron los datos validos del login");
        loginPage.clickLogin();

        // loginPage.bienvenida();
        // loginPage.nombreUsuario();
        // Valido que el resultado sea el esperado
        Assertions.assertEquals(loginPage.bienvenida(), "Hola,");
        Assertions.assertEquals(loginPage.nombreUsuario(), "Tomas Conesa");
        test.log(Status.PASS, "Login exitoso");
    }

    @Test
    @Tag("LOGIN")
    public void loginDatosVacios() {
        ExtentTest test = extentReports.createTest("Login con los campos sin completar - Fallido");
        test.log(Status.INFO, "Comienzo de nuestro test de login vacio");

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirCorreo("");
        loginPage.escribirContraseña("");
        test.log(Status.PASS, "No se agregan datos a los campos del login");
        loginPage.clickLogin();

        loginPage.correoObligatorio();
        loginPage.contraseñaObligatoria();
        test.log(Status.PASS, "Validacion del mensaje de campos obligatorios");
    }

    @Test
    @Tag("LOGIN")
    public void loginCorreoInvalido() {
        ExtentTest test = extentReports.createTest("Login con correo invalido - Fallido");
        test.log(Status.INFO, "Comienzo de nuestro test de login con correo invalido");

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirCorreo("toteiro");
        loginPage.escribirContraseña("test123");
        test.log(Status.PASS, "Se agregan datos a los campos con correo invalido");
        loginPage.clickLogin();

        loginPage.correoInvalido();
        test.log(Status.PASS, "Validacion del mensaje de correo invalido");
    }

    @Test
    @Tag("LOGIN")
    public void loginContraseñaCorta() {
        ExtentTest test = extentReports.createTest("Login con contraseña menor a 6 caracteres - Fallido");
        test.log(Status.INFO, "Comienzo de nuestro test de login con contraseña invalida");

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirCorreo("toteiro@gmail.com");
        loginPage.escribirContraseña("te");
        test.log(Status.PASS, "Se agregan datos a los campos con contraseña invalida");
        loginPage.clickLogin();

        loginPage.contraseñaCorta();
        test.log(Status.PASS, "Validacion del mensaje de contraseña tiene que ser mayor 6 caracteres");
    }

    @Test
    @Tag("LOGIN")
    public void loginCredencialesInvalidas() {
        ExtentTest test = extentReports.createTest("Login con contraseña erronea - Fallido");
        test.log(Status.INFO, "Comienzo de nuestro test de login con contraseña erronea");

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirCorreo("toteiro@gmail.com");
        loginPage.escribirContraseña("test123456789");
        test.log(Status.PASS, "Se agregan datos a los campos con contraseña erronea");
        loginPage.clickLogin();

        loginPage.credenciales();
        test.log(Status.PASS, "Validacion del mensaje sus credenciales son invalidas");
    }

    @AfterEach
    public void close() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extentReports.flush(); // Crea el reporte en la ruta que definimos
    }
}
