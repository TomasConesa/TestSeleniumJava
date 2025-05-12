package reportes;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {

    public static ExtentReports getInstance() {
        ExtentReports extentReports = new ExtentReports();

        extentReports.setSystemInfo("OS", "Windows");
        extentReports.setSystemInfo("Navegador", "Chrome");
        extentReports.setSystemInfo("Ambiente", "QA");

        return extentReports;
    }
}
