package Task_2.Crawl_Issue_Report;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Crawler {

	public void CrawlIssueReport() throws InterruptedException {
		//System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		//System.setProperty("webdriver.chrome.driver","c://chrome1.exe");
    	WebDriver driver;
    	driver = new HtmlUnitDriver();
		driver.get("https://issues.apache.org/jira/browse/CAMEL-10597");
		//Thread.sleep(5000);  
		
		//details
		String type = driver.findElement(By.id("type-val")).getText();
		String status = driver.findElement(By.id("status-val")).getText();
		String priority = driver.findElement(By.id("priority-val")).getText();
		String resolution = driver.findElement(By.id("resolution-val")).getText();
		String affectsVersions = driver.findElement(By.id("versions-val")).getText();
		String fixVersions = driver.findElement(By.id("fixfor-val")).getText();
		String components = driver.findElement(By.id("components-val")).getText();
		 
		//people
		String assignee = driver.findElement(By.id("assignee-val")).getText();
		String reporter = driver.findElement(By.id("reporter-val")).getText();
		String votes = driver.findElement(By.id("vote-data")).getText();
		String watchers = driver.findElement(By.id("watcher-data")).getText();
		
		String description = driver.findElement(By.id("description-val")).getText();
		
		
		
		driver.quit();
		
		System.out.println("type: "+type+", Status: "+status);
	}
}
