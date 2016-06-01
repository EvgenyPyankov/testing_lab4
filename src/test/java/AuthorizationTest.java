import io.appium.java_client.android.AndroidDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class AuthorizationTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "android_device");
        capabilities.setCapability("activity", "com.twitter.android/com.twitter.android.DispatchActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void wrongPassword() throws InterruptedException {
        driver.findElement(By.id("com.twitter.android:id/log_in")).click();
        driver.findElement(By.id("com.twitter.android:id/login_identifier")).sendKeys("hfd");
        driver.findElement(By.id("com.twitter.android:id/login_password")).sendKeys("hfd");
        driver.findElement(By.id("com.twitter.android:id/login_login")).click();
        Thread.sleep(5000);
        WebElement home = driver.findElement(By.id("com.twitter.android:id/home"));
        assertTrue(home != null);
    }

    @Test
    public void correctPassword() throws InterruptedException {
        WebElement login = driver.findElement(By.id("com.twitter.android:id/login_identifier"));
        WebElement password = driver.findElement(By.id("com.twitter.android:id/login_password"));
        login.clear();
        login.sendKeys("Sleepwalker17@mail.ru");
        password.clear();
        password.sendKeys("17041995");
        driver.findElement(By.id("com.twitter.android:id/login_login")).click();
        Thread.sleep(5000);
        WebElement ok = driver.findElement(By.id("android:id/button1"));
        if (ok != null) ok.click();
        WebElement home = driver.findElement(By.id("com.twitter.android:id/home"));
        assertTrue(home != null);
    }
}
