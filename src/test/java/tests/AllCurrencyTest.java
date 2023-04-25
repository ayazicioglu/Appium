package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Driver;
import utils.ReusableMethods;

import java.io.File;
import java.io.IOException;

public class AllCurrencyTest {
    // all currency uygulamasinin yuklendigi dogulanir
    // uygulamanin acildigi dogrulanir
    // cevirmek istedigimiz para birimi zloty olarak secilir
    // cevirelecek olan para birimi Tl olarak secilir
    // cevrilen tutar screenShot olarak kaydedilir
    // Ardindan zloty nin tl karsiligi olan tl degeri kaydedilir
    // bu islem dolar tl, sweden kron-tl, Japon yeni- tl olarak tekrarlanir ve kullaniciya sms olarak bildirilir
    AndroidDriver<AndroidElement> driver= Driver.getAndroidDriver();

    @Test
    public void currencyTest() throws IOException {
        // all currency uygulamasinin yuklendigi dogulanir
        Assert.assertTrue(driver.isAppInstalled("com.smartwho.SmartAllCurrencyConverter"));

        // uygulamanin acildigi dogrulanir
        driver.findElementByXPath("//*[@text='Allow']").click(); //notification kabul ederek geçildi
        String anaSayfaApp=driver.findElementByXPath("//*[@text='CURRENCY CONVERTER']").getText();
        Assert.assertEquals(anaSayfaApp,"CURRENCY CONVERTER","uygulama basarılı sekilde baslatilamadı");


        // cevirmek istedigimiz para birimi zloty olarak secilir
       AndroidElement ilkKategori= driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/SpinnerCurrencyA");
       ilkKategori.click();
        ReusableMethods.scrollWithUiScrollable("PLN");

        // cevirelecek olan para birimi Tl olarak secilir
        AndroidElement ikinciKategori=driver.findElement(By.id("com.smartwho.SmartAllCurrencyConverter:id/SpinnerCurrencyB"));
        ikinciKategori.click();
        ReusableMethods.scrollWithUiScrollable("TRY");
        // 1000 zloti tutar girelim
        driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/b1").click();
        driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/b5").click();
        driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/b0").click();
        driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/b0").click();

        // cevrilen tutar screenShot olarak kaydedilir
        File ekranFoto=driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(ekranFoto,new File("paraSonuc.jpeg"));

        ReusableMethods.getScreenshot("y108");

        // Ardindan zloty nin tl karsiligi olan tl degeri kaydedilir
        String sonucPara=driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/EditTextCurrencyB").getText();

        // bu islem dolar tl, sweden kron-tl, Japon yeni- tl olarak tekrarlanir ve kullaniciya sms olarak bildirilir
        ilkKategori.click();
        ReusableMethods.scrollWithUiScrollable("USD");

        ikinciKategori.click();
        ReusableMethods.scrollWithUiScrollable("TRY");
        driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/b1").click();
        driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/b5").click();
        driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/b0").click();
        driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/b0").click();

        ReusableMethods.getScreenshot("usdTry");
    }
}
