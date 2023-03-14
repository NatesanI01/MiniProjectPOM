package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class PlaceOrderItem extends TestBase{
	
	WebDriverWait wait;
	@FindBy(xpath = "//button[contains(text(),'Place Order' )]")
	WebElement orderBtn;
	
	@FindBy(xpath = "//input[@id='name']")
	WebElement name;
	
	@FindBy(xpath = "//input[@id='country']")
	WebElement country;
	
	@FindBy(xpath = "//input[@id='city']")
	WebElement city;
	
	@FindBy(xpath = "//input[@id='card']")
	WebElement card;
	
	@FindBy(xpath = "//input[@id='month']")
	WebElement month;
	
	@FindBy(xpath = "//input[@id='year']")
	WebElement year;
	
	@FindBy(xpath = "//button[contains(text(),'Purchase')]")
	WebElement purchaseBtn;
	
	@FindBy(xpath = "//button[contains(text(),'OK')]")
	public WebElement successMsg;
	
	@FindBy(xpath = "//tbody")
	List<WebElement> items;
	
	@FindBy(xpath = "(//h2)[3]")
	public WebElement ordermsg;
	
	public PlaceOrderItem() {
		PageFactory.initElements(driver, this);
	}
	
	public void placeOrderItem() {
		extentTest=reports.createTest("Place an Order Test");
		wait=new WebDriverWait(driver,Duration.ofSeconds(100));
		if(items.size()!=0) {
			wait.until(ExpectedConditions.elementToBeClickable(orderBtn));
			orderBtn.click();
			name.sendKeys(prop.getProperty("name"));
			country.sendKeys(prop.getProperty("country"));
			city.sendKeys(prop.getProperty("city"));
			card.sendKeys(prop.getProperty("cardNo"));
			month.sendKeys(prop.getProperty("month"));
			year.sendKeys(prop.getProperty("year"));
			wait.until(ExpectedConditions.elementToBeClickable(purchaseBtn));
			purchaseBtn.click();	
		}	

	}
}
