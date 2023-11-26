package PracticeTest.PracticeTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FilterAssignment {
	
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		// WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\NAVKAMAT\\Downloads\\chromedriver-win64 (3)\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.t-mobile.com/cell-phones");
		Thread.sleep(3000);

		//Case 1: Single check
		 selectFilter("Deals", "Free");
		 
		//Case 2: Multiple checks
		 selectFilter("Deals", "Free","New","Special offer");
		 
		 //Case 3: Multiple filter checks
		 selectFilter("Brands","Apple","Nokia","OnePlus");
		 selectFilter("Operating System","Android","iOS","Oxygen");
		 selectFilter("SIM type","eSIM");

		 //case 4: All options check
		 selectFilter("SIM type", "all");
	}

	public static void selectFilter(String label, String... Key) {
		String labelXpath = "//legend[contains(text(),'##')]";
		String filterXpath = "//span[contains(text(),'$$')]//parent::span//preceding-sibling::span[1]";
		String allChkboxXpath = "//legend[contains(text(),'##')]//ancestor::span//parent::mat-expansion-panel-header//following-sibling::div//input[@name='##']//parent::span";
		WebElement ele = null;
		Actions actions = new Actions(driver);
		ele = driver.findElement(By.xpath(labelXpath.replace("##", label)));
		actions.moveToElement(ele).perform();
		ele.click();
		for (String s : Key) {
			if (s.equalsIgnoreCase("all")) {
				List<WebElement> elementlist = driver.findElements(By.xpath(allChkboxXpath.replace("##", label)));
				for (WebElement e : elementlist) {
					actions.moveToElement(e).perform();
					e.click();
				}
				break;
			} else
				ele = driver.findElement(By.xpath(filterXpath.replace("$$", s)));
			actions.moveToElement(ele).perform();
			ele.click();
		}
	}
}