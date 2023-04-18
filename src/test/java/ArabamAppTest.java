import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ArabamAppTest {

    AndroidDriver<AndroidElement> driver;//android cihazların driveri
    final String cihazAdi = "Pixel_3a_API_33_x86_64";
    final String platformIsmi = "Android";
    final String version = "13.0";
    final String automation = "UiAutomator2";


    @BeforeTest
    public void setup() throws MalformedURLException {
            DesiredCapabilities capabilities=new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,cihazAdi);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,platformIsmi);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,version);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,automation);

            capabilities.setCapability("appPackage","com.dogan.arabam");
            // hangi uygulama uzerinde calismak istedigimizi apk infodan

            capabilities.setCapability("appActivity","com.dogan.arabam.presentation.feature.home.HomeActivity"); // uygulamada hangi sayfayi gormek veya baslatmak istedgimiz
            //genelde Main.Activity ya da Homepage.activity olarak apk info icersinde activities kisminda gorulur

            capabilities.setCapability(MobileCapabilityType.NO_RESET,false);
            // eger false kullanirsak uygulama calistiktan sonra yapilacak adimlari gerceklestirir uygulamayi islem bittikten sonra SIFIRLAR
            // eger true olursa uygulama calistiktan sonra yapilacak adimlari gercceklestirir uygulamayi islem bittikten sonra SIFIRLAMAZ

            driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }
    @Test
    public void arabamTest() throws InterruptedException {
        driver.findElementById("com.android.permissioncontroller:id/permission_deny_button").click();

        // Arabam kac para bolumune tiklayalim
        driver.findElementByXPath("//*[@text=\"Arabam kaç para?\"]").click();

        // Aracimin fiyatini merak ediyorum bolumunetiklayalim
        AndroidElement merak=driver.findElementByXPath("//*[@text='Aracımın fiyatını merak ediyorum']");
        merak.click();

        // Wolkswagen markasini secelim
        driver.findElementByXPath("//*[@text='Listelenenler arasında ara']").sendKeys("Vol");
       AndroidElement volkswagen= driver.findElementByXPath("//*[@text='Volkswagen']");
       volkswagen.click();

       // yil secimi yapalim
        driver.findElementByXPath("(//*[@text='Listelenenler arasında ara'])").sendKeys("2000");
        driver.findElementByXPath("(//*[@text='2000'])[2]").click();

        // model secimi yapalim
        AndroidElement polo=driver.findElementByXPath("(//*[@text='Polo'])");
        polo.click();

        // govde tipini secelim
        AndroidElement govde=driver.findElementByXPath("(//*[@text='Hatchback/5'])");
        govde.click();

        // yakit tipini secelim
        AndroidElement benzin=driver.findElementByXPath("(//*[@text='Benzin'])");
        benzin.click();

        // vites tipini secelim
        AndroidElement duzVites=driver.findElementByXPath("(//*[@text='Düz'])");
        duzVites.click();

        // Versiyon secimi yapalim
        driver.findElementByXPath("(//*[@text='Listelenenler arasında ara'])").sendKeys("Polo 1.6");
        AndroidElement versiyon=driver.findElementByXPath("(//*[@text='Polo 1.6  Manuel  75hp  (1994 - 2000)'])");
        versiyon.click();

        // aracin km bilgilerini girelim
        driver.findElementByXPath("(//*[@text='Aracınızın Kilometresi'])").sendKeys("185000");
        AndroidElement devamButon=driver.findElementByXPath("(//*[@text='Devam'])");
        devamButon.click();

        // aracin rengini secelim
        AndroidElement beyazRenk=driver.findElementByXPath("(//*[@text='Beyaz'])");
        beyazRenk.click();

        // opsiyel donanim (varsa) seecelim
        driver.findElementByXPath("(//*[@text='Devam'])").click();

        // degisen bilgisi ekleyerek tramer kaydi belirtelim
        driver.findElementByXPath("(//*[@text='Tramer kaydı yok'])").click();
        driver.findElementByXPath("(//*[@text='Devam'])").click();

        // aracimizin fiyatinin 200.000 tl den fazla oldugunu test edelim
        String actualFiyatStr=driver.findElementById("com.dogan.arabam:id/tvAveragePrice").getText().replaceAll("\\D","");
        int actualFiyat=Integer.parseInt(actualFiyatStr);
        Assert.assertTrue(actualFiyat>200000);

        // uygulamayi kapatalim
        KeyEvent back=new KeyEvent(AndroidKey.BACK);
        driver.pressKey(back);
        driver.pressKey(back);
        Thread.sleep(2000);
        driver.findElementByXPath("(//*[@text='EVET'])").click();


    }
}
