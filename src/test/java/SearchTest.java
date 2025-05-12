import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.POM.SearchPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ExtentFactory;

import java.time.Duration;

public class SearchTest {

    public WebDriver driver;
    public WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/ReporteSearch.html");
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
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.setup();
        searchPage.url("https://digital-booking-front.digitalhouse.com/");
    }

    @Test
    @Tag("BUSQUEDA")
    @Tag("SMOKE")
    public void BusquedaExitosaUruguay() throws InterruptedException {
        ExtentTest test = extentReports.createTest("Busqueda exitosa en Uruguay");
        test.log(Status.INFO, "Comienzo de nuestro test de busqueda");

        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.escribirBusqueda("Punta del este");

        searchPage.clickBuscar();
        test.log(Status.PASS, "Busqueda en Uruguay realizada exitosamente");

        searchPage.resultadoBusqueda();
        Thread.sleep(3000); // Espera 3 segundos antes de cerrar
        test.log(Status.PASS, "Se valida que la busqueda haya sido correcta");
    }

    @Test
    @Tag("BUSQUEDA")
    @Tag("REGRESSION")
    public void BusquedaExitosaGrecia() throws InterruptedException {
        ExtentTest test = extentReports.createTest("Busqueda exitosa en Grecia");
        test.log(Status.INFO, "Comienzo de nuestro test de busqueda");

        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.escribirBusqueda("Paros");

        searchPage.clickBuscar();
        test.log(Status.PASS, "Busqueda en Grecia realizada exitosamente");

        searchPage.resultadoBusqueda();
        Thread.sleep(3000); // Espera 3 segundos antes de cerrar
        test.log(Status.PASS, "Se valida que la busqueda haya sido correcta");
    }

    @AfterEach
    public void close() {
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extentReports.flush(); // Crea el reporte en la ruta que definimos
    }

      /* @Test
    public void BusquedaExitosa() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.id("ciudad"));
        searchBox.sendKeys("Punta del este");
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(1000);


        WebElement searchButton = driver.findElement(By.className("btn-primario"));
        searchButton.click();
        Thread.sleep(1000);

        //WebElement searchOk = driver.findElement(By.xpath("//*[@id=\"68\"]/div[2]/div/div[1]")); El xpath este depende de que ya hayan fechas seleccionadas
        WebElement searchOk = driver.findElement(By.className("categoria"));
        String busquedaCorrecta = searchOk.getText();
        Thread.sleep(1000);
        System.out.println(busquedaCorrecta);

        driver.quit();
    } */
}