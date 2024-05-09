package AutomationTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Test1 {
	public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        String[] resolutionsDesktop = {"1920x1080", "1366x768", "1536x864"};
        String[] resolutionsMobile = {"360x640", "414x896", "375x667"};

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.getcalley.com/page-sitemap.xml");
        System.out.println("Webpage opened successfully.");

        // XPaths for each webpage link
        String[] webpageXPaths = {
            "//a[contains(text(),'https://www.getcalley.com/')]",
            "//a[contains(text(),'https://www.getcalley.com/calley-call-from-browser/')]",
            "//a[contains(text(),'https://www.getcalley.com/calley-pro-features/')]",
            "//a[contains(text(),'https://www.getcalley.com/best-auto-dialer-app/')]",
            "//a[contains(text(),'https://www.getcalley.com/how-calley-auto-dialer-app-works/')]"
        };

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String dateTimeStr = now.format(formatter);

        // Loop through each webpage link
        for (int i = 0; i < Math.min(webpageXPaths.length, 5); i++) {
            WebElement link = driver.findElement(By.xpath(webpageXPaths[i]));
            String url = link.getText();
            link.click();

            // Capture screenshots for desktop resolutions
            for (String resolution : resolutionsDesktop) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=" + resolution);
                driver.manage().window().setSize(new org.openqa.selenium.Dimension(Integer.parseInt(resolution.split("x")[0]), 
                		Integer.parseInt(resolution.split("x")[1])));

                driver.navigate().refresh();
                System.out.println("Refreshed webpage.");

                new WebDriverWait(driver, 10).until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));

                // Capture screenshot
                Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
                BufferedImage image = screenshot.getImage();
                String filename = "Screenshot_" + dateTimeStr + "_" + i + "_" + resolution + "_Desktop.png";
                File destfile = new File("./Screenshots/" + filename);
                ImageIO.write(image, "PNG", destfile);
                System.out.println("Full-page screenshot saved: " + destfile.getAbsolutePath());
            }

            // Capture screenshots for mobile resolutions
            for (String resolution : resolutionsMobile) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=" + resolution);
                driver.manage().window().setSize(new org.openqa.selenium.Dimension(Integer.parseInt(resolution.split("x")[0]),
                		Integer.parseInt(resolution.split("x")[1])));

                driver.navigate().refresh();
                System.out.println("Refreshed webpage.");

                new WebDriverWait(driver, 10).until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));

                // Capture screenshot
                Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
                BufferedImage image = screenshot.getImage();
                String filename = "Screenshot_" + dateTimeStr + "_" + i + "_" + resolution + "_Mobile.png";
                File destfile = new File("./Screenshots/" + filename);
                ImageIO.write(image, "PNG", destfile);
                System.out.println("Full-page screenshot saved: " + destfile.getAbsolutePath());
            }

            driver.navigate().back(); // Go back to sitemap page
        }

        driver.quit();
    }
}


	

     
  

        






