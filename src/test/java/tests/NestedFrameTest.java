
package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NestedFrameTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testNestedFrames() {

        driver.get("https://the-internet.herokuapp.com/nested_frames");

        // Switch to top frame
        driver.switchTo().frame("frame-top");

        // Switch to left frame
        driver.switchTo().frame("frame-left");

        String leftText = driver.findElement(
            org.openqa.selenium.By.tagName("body")
        ).getText();

        Assert.assertEquals(leftText, "LEFT");
        System.out.println("Left Frame Passed!");

        // Switch to right frame using parent frame
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-right");

        String rightText = driver.findElement(
            org.openqa.selenium.By.tagName("body")
        ).getText();

        Assert.assertEquals(rightText, "RIGHT");
        System.out.println("Right Frame Passed!");
        // Switch to middle frame
driver.switchTo().parentFrame();
driver.switchTo().frame("frame-middle");

String middleText = driver.findElement(
    org.openqa.selenium.By.tagName("body")
).getText();

Assert.assertEquals(middleText, "MIDDLE");
System.out.println("Middle Frame Passed!");

        // Return to main page
        driver.switchTo().defaultContent();

        // Switch to bottom frame
        driver.switchTo().frame("frame-bottom");

        String bottomText = driver.findElement(
            org.openqa.selenium.By.tagName("body")
        ).getText();

        Assert.assertEquals(bottomText, "BOTTOM");
        System.out.println("Bottom Frame Passed!");
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
