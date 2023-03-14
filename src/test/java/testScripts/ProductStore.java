package testScripts;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import base.TestBase;
import base.Utility;
import pages.AddItemToCart;
import pages.DeleteItemCart;
import pages.LoginPage;
import pages.PlaceOrderItem;

public class ProductStore extends TestBase{
  LoginPage loginpage;
  AddItemToCart cart;
  DeleteItemCart delete;
  PlaceOrderItem placeOrder;
  
  @BeforeTest
  public void setup() {
	  initialize();
  }
  @Test(priority=1)
  public void signInPage() throws InterruptedException{
	  loginpage=new LoginPage();
	  loginpage.loginPage(prop.getProperty("username"),prop.getProperty("pwd"));
	  Assert.assertEquals(loginpage.successMsg.getText(), "Welcome glass");;
  }
  
  @Test(dataProvider="search",priority=2)
  public void addToCart(String catagory,String item) {
	  cart=new AddItemToCart();
	  cart.addCart(catagory, item);
	  boolean flag=false;
	  for(WebElement test:cart.items) {
		  if(test.getText().equalsIgnoreCase(item)) {
			  Assert.assertEquals(test.getText(), item);
			  flag=true;
		  }
	  }
	  Assert.assertTrue(flag);
	  
  }
  
  @DataProvider(name="search")
  public Object[][] getData() throws CsvValidationException, IOException{
	  String path=System.getProperty("user.dir")+"//src//test//resources//testdata//productstore.csv";
	  CSVReader reader=new CSVReader(new FileReader(path));
	  String[] cols;
	  ArrayList<Object> dataList=new ArrayList<Object>();
	  while((cols=reader.readNext())!=null) {
		  Object[] records= {cols[0],cols[1]};
		  dataList.add(records);
	  }
	  return dataList.toArray(new Object[dataList.size()][]);
  }
  
  @Test(priority=3)
  public void deleteItemcart() throws InterruptedException{
	  delete=new DeleteItemCart();
	  cart.cartBtn.click();
	  delete.DeleteItemTest();
	  Assert.assertNotEquals(delete.beforePrice, delete.afterPrice);
  }

  @Test(priority=4)
  public void placeOrder() throws InterruptedException {
	  placeOrder=new PlaceOrderItem();
	  cart.homePage.click();
	  cart.cartBtn.click();
	  placeOrder.placeOrderItem();
	  Assert.assertEquals(placeOrder.ordermsg.getText(), "Thank you for your purchase!");
	  Thread.sleep(1000);
	  placeOrder.successMsg.click();
	  Thread.sleep(1000);
  }
  
  @AfterMethod
  public void finishExtent(ITestResult results) throws IOException {
	  if(ITestResult.FAILURE== results.getStatus()) {
		  extentTest.log(Status.FAIL, results.getThrowable().getMessage());
		  String path=Utility.getScreenshotPath(driver);
		  extentTest.addScreenCaptureFromPath(path);
	  }
  }
  
  @AfterTest
  public void finish(){
	  finial();
	  reports.flush();
  }
}
