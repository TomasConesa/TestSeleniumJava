package TestWebPOM;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.POM.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ExtentFactory;

import java.time.Duration;

public class RegisterTest {

    public WebDriver driver;
    public WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/ReporteRegister.html");
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
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setup();
        registerPage.url("https://digital-booking-front.digitalhouse.com/registro");
    }

    @Test
    @Tag("REGISTRO")
    public void RegistroExitoso() throws InterruptedException {
        ExtentTest test = extentReports.createTest("Registro exitoso");
        test.log(Status.INFO, "Comienzo de test de registro");

        RegisterPage registerPage = new RegisterPage(driver, wait);

        registerPage.escribirNombre("Tomas");
        registerPage.escribirApellido("Conesa");
        registerPage.escribirCorreo("toteir000010@gmail.com");
        registerPage.escribirContraseña("test1234");
        registerPage.repetirContraseña("test1234");
        test.log(Status.PASS, "Ingreso todos los datos del registro");

        registerPage.clickRegistrarse();

        if (registerPage.cuentaCreadaGracias().equals("¡Cuenta registrada con éxito!")) {
            test.log(Status.PASS, "Validacion de registro exitoso");
        } else {
            test.log(Status.FAIL, "Fallo el mensaje de registro exitoso");
        }

        if (registerPage.cuentaCreadaExito().equals("Te enviamos un email para confirmar tu cuenta")) {
            test.log(Status.PASS, "Validacion mensaje de mail por el registro exitoso");
        } else {
            test.log(Status.FAIL, "Fallo el mensaje de envio de mail luego del registro exitoso");
        }
    }

    @Test
    @Tag("REGISTRO")
    public void registroFallidoMailRepetido() throws InterruptedException{
        ExtentTest test = extentReports.createTest("Registro fallido por mail repetido");
        test.log(Status.INFO, "Comienzo de test de registro con mail repetido");

        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
           // registerPage.clickCrearCuenta();
           // test.log(Status.PASS, "Ingreso en el formulario de registro");

            registerPage.escribirNombre("Tomas");
            registerPage.escribirApellido("Conesa");
            registerPage.escribirCorreo("toteir000010@gmail.com");
            registerPage.escribirContraseña("test1234");
            registerPage.repetirContraseña("test1234");
            test.log(Status.PASS, "Ingreso todos los datos del registro con un mail ya registrado");

            registerPage.clickRegistrarse();

            if(registerPage.mailRegistrado().equals("Ese email ya se encuentra registrado")) {
                test.log(Status.PASS, "Validacion de mensaje de mail ya registrado");
            } else {
                test.log(Status.FAIL, "Fallo el mensaje de mail ya registrado");
            }
        } catch (Exception error) {
            test.log(Status.FAIL, "Se produjo una excepcion durante la ejecucion del test" + error.getMessage());
        }
    }

    @Test
    @Tag("REGISTRO")
    public void registroFallidoContraseña() throws InterruptedException{
        ExtentTest test = extentReports.createTest("Registro fallido por contraseñas que no coinciden");
        test.log(Status.INFO, "Comienzo de test de registro fallido por contraseñas que no coinciden");

        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            //registerPage.clickCrearCuenta();
            //test.log(Status.PASS, "Ingreso en el formulario de registro");

            registerPage.escribirNombre("Tomas");
            registerPage.escribirApellido("Conesa");
            registerPage.escribirCorreo("toteir0001268@gmail.com");
            registerPage.escribirContraseña("test1234");
            registerPage.repetirContraseña("test123456789");
            test.log(Status.PASS, "Ingreso todos los datos del registro con una contraseña que no coincide");

            registerPage.clickRegistrarse();

            if(registerPage.contraseñaNoCoinciden().equals("Las contraseñas deben ser iguales")) {
                test.log(Status.PASS, "Validacion de mensaje de las contraseñas deben coincidir");
            } else {
                test.log(Status.FAIL, "Fallo el mensaje de que las contraseñas deben coincidir");
            }
        } catch (Exception error) {
            test.log(Status.FAIL, "Se produjo una excepcion durante la ejecucion del test" + error.getMessage());
        }
    }

    @AfterEach
    public void close() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extentReports.flush(); // Crea el reporte en la ruta que definimos
    }


}
