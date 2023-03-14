package pages;


import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class DeleteItemCart extends TestBase {
	WebDriverWait wait;
	public String beforePrice;
	public String afterPrice;
	@FindBy(xpath="(//td[4]//a)[1]")
	WebElement deleteBtn;
	
	@FindBy(id = "totalp")
	public WebElement rate;
	
	public DeleteItemCart() {
		PageFactory.initElements(driver, this);
	}
	
	public void DeleteItemTest() throws InterruptedException {
		extentTest=reports.createTest("Delete an Item in Cart Test");
		wait=new WebDriverWait(driver,Duration.ofSeconds(100));
		beforePrice=rate.getText();
		System.out.println(beforePrice);
		deleteBtn.click();
		Thread.sleep(3000);
		afterPrice=rate.getText();
		System.out.println(afterPrice);
	}

}
