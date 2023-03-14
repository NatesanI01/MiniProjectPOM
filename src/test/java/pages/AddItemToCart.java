package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class AddItemToCart extends TestBase{
	WebDriverWait wait;
	
	@FindBy(xpath = "//a[contains(text(),'Add to cart')]")
	WebElement addcartBtn;
	
	@FindBy(xpath = "//a[contains(text(),'Home')]")
	public WebElement homePage;
	
	@FindBy(xpath = "//td[2]")
	public List<WebElement> items;
	
	@FindBy(xpath = "//a[contains(text(),'Cart')]")
	public WebElement cartBtn;
	
	public AddItemToCart() {
		PageFactory.initElements(driver, this);
	}
	public void addCart(String catagory,String item) {
		extentTest=reports.createTest("Add Multiple Item To Cart Test");
		wait=new WebDriverWait(driver,Duration.ofSeconds(100));
		homePage.click();
		String catagoryPath="//a[text()='"+catagory+"']";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(catagoryPath)));
		driver.findElement(By.xpath(catagoryPath)).click();
		String searchPath="//a[text()='"+item+"']";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchPath)));
		driver.findElement(By.xpath(searchPath)).click();
		addcartBtn.click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert=driver.switchTo().alert();
		alert.accept();
		cartBtn.click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//td[2]")));
	}

}
