package AutomationTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Functionaltest {

	public static void main(String[] args) throws IOException, InterruptedException {
		 WebDriverManager.chromedriver().setup();
		 WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://demo.dealsdray.com/");
	        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("prexo.mis@dealsdray.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("prexo.mis@dealsdray.com");
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			WebDriverWait wait = new WebDriverWait(driver, 10);
		    WebElement ordersElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[@class=' MuiBox-root css-psy1y7']")));
		    ordersElement.click();
		    WebElement bulkorders = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@align='right']")));
		    bulkorders.click();
		    // Input file path and upload
		 // Input file path and upload
		    WebDriverWait wait1 = new WebDriverWait(driver, 10);
		    WebElement fileInput = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='file']")));
		    fileInput.sendKeys("C:\\Users\\anshi\\Downloads\\demo-data.xlsx"); // Replace with actual file path

	        // Click on "Import" button after selecting the file
	        WebElement importButton = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Import')]")));
	        importButton.click();
	     // Click the "Validate" button
	        WebElement validateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Validate')]")));
	        validateButton.click();
	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	        alert.accept();

	        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
            BufferedImage image = screenshot.getImage();
            String filename = "file.png";
            File destfile = new File("/AutomationTest/file" + filename);
            ImageIO.write(image, "PNG", destfile);
            System.out.println("Full-page screenshot saved: " + destfile.getAbsolutePath());
            driver.quit();
        }
	        
	   
}
	
	
			
	


