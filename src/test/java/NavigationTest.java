import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NavigationTest {
    AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "android_device");
        capabilities.setCapability("activity", "com.twitter.android/com.twitter.android.MainActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void messages() {
        WebElement ok = driver.findElement(By.id("android:id/button1"));
        if (ok != null) ok.click();
        driver.findElement(By.id("com.twitter.android:id/toolbar_dms")).click();
        WebElement message = driver.findElement(By.id("com.twitter.android:id/menu_compose_dm"));
        assertTrue(message != null);
    }


}
