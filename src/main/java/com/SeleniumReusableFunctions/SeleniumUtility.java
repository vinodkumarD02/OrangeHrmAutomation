package com.SeleniumReusableFunctions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BaseClass.Library;

public class SeleniumUtility extends Library {
	WebDriver driver ;
	Library library;
	
	 public SeleniumUtility(WebDriver driver)
	 {
		 this.driver =driver;
		 
	 }
	 
	public   WebElement waitForElementClickable(WebElement element)
	{

	WebDriverWait wait = new WebDriverWait(driver, 30);
	return wait.until(ExpectedConditions.elementToBeClickable(element));
	
	}

	public   WebElement waitTillElementVisiblity(WebElement element) 
	{
	WebDriverWait wait = new WebDriverWait(driver, 30);
	return wait.until(ExpectedConditions.visibilityOf(element));
	
	}

	public void snapshot(String fileName)
	{
		String	path="src\\test\\resources\\Screenshots\\";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(path+fileName+System.currentTimeMillis()+"png");
		try {
			FileUtils.copyFile(src, dest);
			logger.info("ScreenShot taken in name of "+fileName);
		} catch (IOException e) {
			logger.info("Error in taking ScreenShots");
		}
	}

	public   void pressKeyDown(WebElement element) {
	element.sendKeys(Keys.DOWN);
	}

	public void pressKeyEnter(WebElement element) {
	element.sendKeys(Keys.ENTER);
	}

	public   void pressKeyUp(WebElement element) {
	element.sendKeys(Keys.UP);
	}

	public   void moveToTab(WebElement element) {
	element.sendKeys(Keys.chord(Keys.ALT, Keys.TAB));
	}

	public   void keyboardEvents(WebElement webelement, Keys key,String alphabet) {
		
	webelement.sendKeys(Keys.chord(key, alphabet));

	}

	public   void navigate_forward() {
	driver.navigate().forward();
	}

	public   void navigate_back() {
	driver.navigate().back();
	}

	public   void refresh() {
	driver.navigate().refresh();
	}
	
	public   boolean checkAlert_Accept() {
	try {
	Alert a = driver.switchTo().alert();
	String str = a.getText();
	System.out.println(str);

	a.accept();
	return true;

	} catch (Exception e) {

	System.out.println("no alert");
	return false;

	}
	}

	public   boolean checkAlert_Dismiss() {
	try {
	Alert a = driver.switchTo().alert();
	String str = a.getText();
	System.out.println(str);

	a.dismiss();
	return true;

	} catch (Exception e) {

	System.out.println("no alert ");
	return false;

	}
	}

	public   void scrolltoElement(WebElement ScrolltoThisElement) {
	Coordinates coordinate = ((Locatable) ScrolltoThisElement)
	.getCoordinates();
	coordinate.onPage();
	coordinate.inViewPort();
	}

	public   void checkbox_Checking(WebElement checkbox) {
	boolean checkstatus;
	checkstatus = checkbox.isSelected();
	if (checkstatus == true) {
	System.out.println("Checkbox is already checked");
	} else {
	checkbox.click();
	System.out.println("Checked the checkbox");
	}
	}

	public   void radiobutton_Select(WebElement Radio) {
	boolean checkstatus;
	checkstatus = Radio.isSelected();
	if (checkstatus == true) {
	System.out.println("RadioButton is already checked");
	} else {
	Radio.click();
	System.out.println("Selected the Radiobutton");
	}
	}

	// Unchecking
	public   void checkbox_Unchecking(WebElement checkbox) {
	boolean checkstatus;
	checkstatus = checkbox.isSelected();
	if (checkstatus == true) {
	checkbox.click();
	System.out.println("Checkbox is unchecked");
	} else {
	System.out.println("Checkbox is already unchecked");
	}
	}

	public   void radioButton_Deselect(WebElement Radio) {
	boolean checkstatus;
	checkstatus = Radio.isSelected();
	if (checkstatus == true) {
	Radio.click();
	System.out.println("Radio Button is deselected");
	} else {
	System.out.println("Radio Button was already Deselected");
	}
	}

	public   void dragAndDrop(WebElement fromWebElement,
	WebElement toWebElement)throws InterruptedException {
	Actions builder = new Actions(driver);
	builder.dragAndDrop(fromWebElement, toWebElement).build().perform();;
	}
	
	
	public   void hoverWebelement(WebElement HovertoWebElement)
	throws InterruptedException {
	Actions builder = new Actions(driver);
	builder.moveToElement(HovertoWebElement).build().perform();
	Thread.sleep(2000);
	}

	public   void doubleClickWebelement(WebElement doubleclickonWebElement)
	throws InterruptedException {
	Actions builder = new Actions(driver);
	builder.doubleClick(doubleclickonWebElement).build().perform();
	Thread.sleep(2000);
	}

	public void clickCheckboxFromList(String xpathOfElement, String valueToSelect) {

	List<WebElement> lst = driver.findElements(By.xpath(xpathOfElement));
	for (int i = 0; i < lst.size(); i++) {
	List<WebElement> dr = lst.get(i).findElements(By.tagName("label"));
	for (WebElement f : dr) {
	System.out.println("value in the list : " + f.getText());
	if (valueToSelect.equals(f.getText())) {
	f.click();
	break;
											}
						}
				}
		}
	
	public void selectDropDownByValue(String xpath, String value ){
		  Select selectByValue = new Select(driver.findElement(By.xpath(xpath)));
          selectByValue.selectByValue(value);
		}

	public void selectDropDownByVisibleText(String xpath, String visibleText ){

          Select selectByVisibleText = new Select (driver.findElement(By.xpath(xpath)));
          selectByVisibleText.selectByVisibleText(visibleText);
         
	}
	
      public void selectDropDownByIndex(String xpath, int index ){

          Select selectByIndex = new Select(driver.findElement(By.xpath(xpath)));
          selectByIndex.selectByIndex(index);         
   }
      
      public String getTitle()
      {
    	return  driver.getTitle();
      }
      
      public String getValue(WebElement element)
      {
    	  WebElement webElement = this.waitForElementClickable(element);
          webElement = this.waitForElementClickable(element);
          return webElement.getAttribute("value");
      }
      
      public String getText(WebElement element)
      {
    	  WebElement webElement = this.waitForElementClickable(element);
          webElement = this.waitForElementClickable(element);
          return webElement.getText();
      }
      
      public void enterText(WebElement element, String text) {
    	  
          WebElement webElement = this.waitTillElementVisiblity(element);
          webElement.clear();
          webElement.sendKeys(text);
      }
      
      public void clickOnElement(WebElement element) {
	
	 	  WebElement webElement = this.waitForElementClickable(element);
          webElement = this.waitForElementClickable(element);
          webElement.click(); 
      }
      
      public boolean checkWebelementEnabled(WebElement element) {
    	  
    	  boolean booleanElement;
    	  try {
    		 booleanElement = element.isEnabled();

    	  } catch (Exception e) {
    	  System.out.println("Element is not enabled");
    	  booleanElement=false;
    	  }
		return booleanElement;
    	  }
      
      public boolean checkWebelementDisplayed(WebElement element) {
    	  
    	  boolean booleanElement;
    	  try {
    		  booleanElement = element.isDisplayed();

    	  } catch (Exception e) {
    	  System.out.println("Element is not enabled");
    	  booleanElement=false;
    	  }
		return booleanElement;
    	  }
      
      
}



