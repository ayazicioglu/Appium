package tests;

import Pages.KiwiPage;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Driver;

import java.time.Duration;

public class KiwiTest {
    // uygulamanin yuklendigi dogrulanir
// uygulamanin basariyla acildigi dogrulanir
// misafir olarak devam et e tiklanir
// ardinda gelecek olan 3 adimada yesil butona basilarak devam edilir
// Trip type,one way olarak secilir
// kalkis ulkesi secenegine tiklanir ve default olan ulke kaldirilir
// kalkis yapilacak ulke/sehir girilir ve sec e tiklanir
// varis ulkesi secenegine tiklanir ve gidilecek ulke girilir
// gidis tarihi mayis ayinin 21 i olarak secilir ve set date e tiklanir
// search butonuna tiklanir
// en  ucuz ve aktarmasiz filtrelemeleri yapilir
// gelen bilet fiyati kaydedilir ve kullanicin telefonuna sms olarak gonderilir
    AndroidDriver<AndroidElement> driver=Driver.getAndroidDriver();

    KiwiPage page=new KiwiPage();
    TouchAction action=new TouchAction<>(Driver.getAndroidDriver());
    @Test
    public void kiwiTest() {
        // uygulamanin yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.skypicker.main"),"uygulama yuklenemedi");

        // uygulamanin basariyla acildigi dogrulanir
        String yaziTextActual= page.asAGuest.getText();
        String yaziTextExpected="Continue as a guest";
        Assert.assertEquals(yaziTextActual,yaziTextExpected,"uygulama basarili bir sekilde baslatilamadi");

        // misafir olarak devam et e tiklanir
        page.asAGuest.click();

        // ardinda gelecek olan 3 adimada yesil butona basilarak devam edilir
        page.ucButtonTiklama(0,3,527,2038,500);
        //driver.findElementByXPath("//*[@text='Don’t allow']").click();
        driver.findElementByXPath("//*[@text='Skip']").click();
       // driver.findElementByXPath("(//*[@class='android.widget.Button'])[2]").click();

        // Trip type,one way olarak secilir
        driver.findElementByXPath("//*[@text='Return']").click();
        driver.findElementByXPath("//*[@text='One way']").click();

        // kalkis ulkesi secenegine tiklanir ve default olan ulke kaldirilir
        driver.findElementByXPath("//*[@text='From:']").click();
        driver.findElementByXPath("(//*[@class='android.widget.Button'])[1]").click();


        // kalkis yapilacak ulke/sehir girilir ve sec e tiklanir

        driver.getKeyboard().sendKeys("antalya");
        page.bekle(2);
        driver.findElementByXPath("(//*[@class='android.widget.Button'])[2]").click();
        driver.findElementByXPath("(//*[@class='android.widget.Button'])[9]").click();

        // varis ulkesi secenegine tiklanir ve gidilecek ulke girilir
        driver.findElementByXPath("(//*[@text='Anywhere'])[1]").click();
        page.bekle(2);
        driver.getKeyboard().sendKeys("poland");
        page.bekle(2);
        driver.findElementByXPath("(//*[@class='android.widget.Button'])[2]").click();
        driver.findElementByXPath("//*[@text='Choose']").click();

       // driver.findElementByXPath("(//*[@class='android.widget.Button'])[10]").click();

        // gidis tarihi mayis ayinin 21 i olarak secilir ve set date e tiklanir
        driver.findElementByXPath("(//*[@text='Anytime'])[2]").click();
        page.bekle(2);
        action.press(PointOption.point(441,1679)).            //Baslangic koordinatlarini belirle
                waitAction(WaitOptions.waitOptions(Duration.ofMillis(0))).// bekleme suresini belirle
                moveTo(PointOption.point(445,154)).            //Bitis koordinatlarini belirle
                release().perform();
        page.bekle(2);
        action.press(PointOption.point(111,890)).release().perform();
        page.bekle(2);
        driver.findElementByXPath("//*[@text='Set date']").click();

        // search butonuna tiklanir
        driver.findElementByXPath("(//*[@text='Search'])[1]").click();

        // en  ucuz ve aktarmasiz filtrelemeleri yapilir
        driver.findElementByXPath("//*[@text='Popular']").click();
        driver.findElementByXPath("//*[@text='Cheapest']").click();
        driver.findElementByXPath("//*[@text='Stops']").click();
        driver.findElementByXPath("//*[@text='Nonstop']").click();

        // gelen bilet fiyati kaydedilir ve kullanicin telefonuna sms olarak gonderilir
        String mobilFiyat=driver.findElementByXPath("//*[@resource-id='com.skypicker.main:id/price']").getText();
        driver.sendSMS("+905537981146",mobilFiyat);

    }
    //****************************************************************************************************************************//
    @Test
    public void webTest() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driverWEB = new ChromeDriver();
        driverWEB.manage().window().maximize();
        // driverWEB.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //// https://www.kiwi.com/en/ sayfasina gidin
        driverWEB.get("https://www.kiwi.com/en/");

        //// Cookies i reddedin
        driverWEB.findElement(By.xpath("//*[text()='Reject all']")).click();

        //// Sag ust kisimdan dil ve para birimi secenegini Turkiye ve TL olarak secin
        driverWEB.findElement(By.xpath("//*[@data-test='RegionalSettingsButton']")).click();
        WebElement dropDownLanguageElement = driverWEB.findElement(By.xpath("//select[@data-test='LanguageSelect']"));
        Select dilSecimi = new Select(dropDownLanguageElement);
        dilSecimi.selectByVisibleText("Türkçe");
        WebElement dropDownCurrencyElement = driverWEB.findElement(By.xpath("//select[@data-test='CurrencySelect']"));
        Select paraTuru = new Select(dropDownCurrencyElement);
        paraTuru.selectByVisibleText("United States dollar - USD");

        driverWEB.findElement(By.xpath("//*[text()='Save & continue']")).click();


        //// Sectiginiz dil ve para birimini dogrulayin
        Assert.assertTrue(driverWEB.findElement(By.xpath("(//img[@alt='Türkçe'])[1]")).isDisplayed());
        Assert.assertTrue(driverWEB.findElement(By.xpath("//*[text()='USD']")).isDisplayed());

        //// Ucus secenegi olarak tek yon secelim
        driverWEB.findElement(By.xpath("(//*[text()='Gidiş Dönüş'])[1]")).click();
        driverWEB.findElement(By.xpath("(//*[text()='Tek Yön'])")).click();
        page.bekle(2);

        //// Kalkis ve varis boxlarini temizleyerek kalkis ve varis ulkesini kendimiz belirleyelim

        driver.findElement(By.xpath("//div[@class='PlacePickerInputPlacestyled__PlacePickerInputClose-sc-esw2vf-1 hZSisY']")).click();
        Actions actions=new Actions(driver);
        actions.sendKeys("İstanbul").perform();
        page.bekle(2);
        driver.findElement(By.xpath("(//*[@aria-label='Add place'])[1]")).click();
        page.bekle(2);
        driver.findElement(By.xpath("//*[@data-test='SearchPlaceField-destination']")).click();
        actions.sendKeys("varsova").perform();
        page.bekle(2);
        driver.findElement(By.xpath("(//div[@data-test='PlacePickerRow-wrapper'])[1]")).click();
        page.bekle(2);

        //// Gidis tarihi kismina erisim saglayarak gidecegimiz gunu secelim ve booking i iptal edelim
        WebElement bookingEl = driver.findElement(By.xpath("//label[@class='Checkbox__StyledLabel-sc-y66hzm-5 fKLfkU']"));
        bookingEl.click();
        driver.findElement(By.xpath("//*[@name='search-outboundDate']")).click();
        page.bekle(10);
        WebElement tarih = driver.findElement(By.xpath("//div[@data-value='2023-05-21']"));
        actions.doubleClick(tarih).perform();
        page.bekle(5);

        driver.findElement(By.xpath("//div[text()='Ara']")).click();
        page.bekle(3);

        //// Sadece aktarmasiz ucuslar olarak filtreleme yapalim ve en ucuz secenegine tiklayalim
        driver.findElement(By.xpath("(//div[@class='Radio__StyledIconContainer-sc-1e6hy4x-1 cQMXav'])[1]")).click();
        page.bekle(3);
        driver.findElement(By.xpath("//span[text()='En ucuz']")).click();
        page.bekle(3);

        //// Filtreleme yaptigimiz en ucuz ucusun fiyatini getirerek 5000 tl den kucuk oldgunu dogurlayalim
        String webFiyatStr = driver.findElement(By.xpath("(//div[@data-test='ResultCardPrice'])[1]")).
                getText().replaceAll("\\D", "");
        int webFiyat = Integer.parseInt(webFiyatStr);
        System.out.println("webFiyat = " + webFiyat);
        Assert.assertTrue(webFiyat < 5000);
    }



}