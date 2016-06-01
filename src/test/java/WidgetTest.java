import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class WidgetTest {
    AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "android_device");
        capabilities.setCapability("unicodeKeyboard", true);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void setWidget() throws InterruptedException {
        driver.pressKeyCode(AndroidKeyCode.HOME);
        Thread.sleep(1000);
        TouchAction action = new TouchAction((MobileDriver) driver);
        action.longPress(500, 800).perform();
        driver.findElement(By.id("com.android.launcher3:id/widget_button")).click();
        List<AndroidElement> widgets;

        String name = "Twitter (large)";
        for (int i = 0; i < 5; i++) {
            widgets = driver.findElements(By.id("com.android.launcher3:id/widget_name"));
            for (AndroidElement element : widgets) {
                if (name.equals(element.getText())) {
                    Point position = element.getCenter();
                    int x = position.getX();
                    int y = position.getY();
                    action.longPress(x, y).moveTo(x - 100, y - 300).release().perform();
                    Thread.sleep(1000);
                    driver.findElement(By.id("com.twitter.android:id/done")).click();
                    return;
                }
            }
            driver.swipe(800, 800, 200, 800, 500);
        }
        WebElement widget = driver.findElement(By.id("android.widget.LinearLayout"));
        assertTrue(widget != null);




    }

}


