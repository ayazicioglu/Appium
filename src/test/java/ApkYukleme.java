import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ApkYukleme {

    AndroidDriver<MobileElement> driver;
    final String cihazAdi = "Pixel_3a_API_33_x86_64";
    final String platformIsmi = "Android";
    final String version = "13.0";
    final String automation = "UiAutomator2";


    @Test
    public void setupApk() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Pixel_3a_API_33_x86_64");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "13.0");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", "C:\\Users\\ACER\\IdeaProjects\\Appium\\src\\test\\apps\\Apk Bilgisi_2.3.4_apkcombo.com.apk");
        this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        this.driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
    }
}
