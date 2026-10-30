
package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IFrameTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testIFrame() {

        driver.get("https://the-internet.herokuapp.com/iframe");

        driver.switchTo().frame("mce_0_ifr");

        WebElement editor = driver.findElement(By.id("tinymce"));

        String text = editor.getText();

        Assert.assertTrue(
            text.contains("Your content goes here."),
            "iFrame text verification failed"
        );

        System.out.println("iFrame Test Passed!");

        driver.switchTo().defaultContent();
    }

   @AfterMethod
public void tearDown() {
    if (driver != null) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.quit();
    }
}
}
