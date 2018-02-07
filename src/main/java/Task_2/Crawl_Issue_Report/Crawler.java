package Task_2.Crawl_Issue_Report;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.opencsv.CSVWriter;

public class Crawler {

	public JIRAIssue CrawlIssueReport() throws InterruptedException {

    	
    	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
		driver.get("https://issues.apache.org/jira/browse/CAMEL-10597"); 
		
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
		
		//description
		String description = driver.findElement(By.id("description-val")).getText();

		List<WebElement> commentsElement = driver.findElements(By.xpath("//*[starts-with(@id,'comment-')]"));
		
		
		
		ArrayList<String> comments = new ArrayList<String>();
		
		for (int i = 0; i < commentsElement.size(); i++) {
			comments.add(commentsElement.get(i).getText());
		}
		
		JIRAIssue issue = new JIRAIssue(type, status, priority, resolution, affectsVersions,
										fixVersions, components, assignee, reporter, 
										description, comments);
		
		driver.quit();
		return issue;
	}
	
	
	public void WritingDataToCSV(JIRAIssue issue) {
		try 
		{    
			Writer writer = Files.newBufferedWriter(Paths.get("data.csv"));

			CSVWriter csvWriter = new CSVWriter(writer,
					CSVWriter.DEFAULT_SEPARATOR,
					CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER,
					CSVWriter.DEFAULT_LINE_END);

			String[] headerRecord = {"type", "status", "priority", "resolution", "affectsVersions",
					"fixVersions", "components", "assignee", "reporter", "description", "comments"};
			csvWriter.writeNext(headerRecord);

			csvWriter.writeNext(new String[]{issue.getType(), issue.getStatus(), issue.getPriority(), issue.getResolution(), 
					issue.getAffectsVersions(),	issue.getFixVersions(), issue.getComponents(), 
					issue.getAssignee(), issue.getReporter(),
					issue.getDescription(), issue.getComments()});

			csvWriter.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
