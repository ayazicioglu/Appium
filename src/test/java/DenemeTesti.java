
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

    public class DenemeTesti {
        AndroidDriver<MobileElement> driver;
        final String cihazAdi = "Pixel_3a_API_33_x86_64";
        final String platformIsmi = "Android";
        final String version = "13.0";
        final String automation = "UiAutomator2";

        public DenemeTesti() {
        }

        @BeforeTest
        public void deneme1() throws MalformedURLException {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "Pixel_3a_API_33_x86_64");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "13.0");
            capabilities.setCapability("automationName", "UiAutomator2");
          //  capabilities.setCapability("app", "C:\\Users\\ACER\\IdeaProjects\\Appium\\src\\test\\apps\\Calculator_8.4 (503542421)_Apkpure.apk");
            this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            this.driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
        }

        @Test
        public void carpma(){
            driver.activateApp("com.google.android.calculator");
            driver.findElementByAccessibilityId("8").click();
            driver.findElementByAccessibilityId("0").click();
            driver.findElementByAccessibilityId("multiply").click();
            driver.findElementByAccessibilityId("2").click();
            driver.findElementByAccessibilityId("0").click();
            String actualSonuc=driver.findElementById("com.google.android.calculator:id/result_preview").getText();
            System.out.println("actualSonuc = " + actualSonuc);
            Assert.assertEquals(Integer.parseInt(actualSonuc),1600);
        }

        @Test
        public void toplama(){
            driver.activateApp("com.google.android.calculator");
        }
    }

