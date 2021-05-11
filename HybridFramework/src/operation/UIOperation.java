package operation;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.*;

public class UIOperation {

	WebDriver driver;
	public UIOperation(WebDriver driver){
		this.driver = driver;
	}
	public void perform(Properties p,String operation,String objectName,String objectType,String value) throws Exception{
		System.out.println("");
		switch (operation.toUpperCase()) {
		case "CLICK":
			//Perform click
			Thread.sleep(3000);
			driver.findElement(this.getObject(p,objectName,objectType)).click();
			break;
		case "SETTEXT":
			//Set text on control
			driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(value);
			break;
		case "SETTEXTWAIT":
				//Set text on control
				Thread.sleep(10000);
				driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(value);
				break;
		case "GOTOURL":
			//Get url of application
			driver.get(p.getProperty(value));
			break;
		case "GETTEXT":
			//Get text of an element
			driver.findElement(this.getObject(p,objectName,objectType)).getText();
			break;
		case "SWITCHFOCUS":
				//Get text of an element
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			break;
			case "CLEAR":
				//Get text of an element

				driver.findElement(this.getObject(p,objectName,objectType)).clear();
				Thread.sleep(3000);
				driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(Keys.TAB);

				break;
			case "TABE":
				//Get text of an element

				driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(Keys.TAB);

				break;
		case "CLICKSOMEWAIT":
				//Perform click
			Thread.sleep(10000);
			driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(value);
			break;

		case "GETURL":
				//Get text of an element
			System.out.println(driver.getCurrentUrl());
			break;
			case "SCROLL":
				//Get text of an element
				Thread.sleep(3000);
				List<WebElement> autoSuggestions= driver.findElements(this.getObject(p,objectName,objectType));
				System.out.println(autoSuggestions);
				for (WebElement suggestions : autoSuggestions) {
					 {
						System.out.println(suggestions.getText());
						if(suggestions.getText().contains("Ooty") ||suggestions.getText().contains("Hotel") ){
							suggestions.click();
							Thread.sleep(3000);
							break;
						}


					}
				}
				break;
			default:
			break;
		}
	}
	
	/**
	 * Find element BY using object type and value
	 * @param p
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getObject(Properties p,String objectName,String objectType) throws Exception{
		//Find by xpath
		if(objectType.equalsIgnoreCase("XPATH")){
			
			return By.xpath(p.getProperty(objectName));
		}
		//find by class
		else if(objectType.equalsIgnoreCase("CLASSNAME")){
			
			return By.className(p.getProperty(objectName));
			
		}
		//find by name
		else if(objectType.equalsIgnoreCase("NAME")){
			
			return By.name(p.getProperty(objectName));
			
		}

		else if(objectType.equalsIgnoreCase("ID")){

			return By.id(p.getProperty(objectName));

		}
		//Find by css
		else if(objectType.equalsIgnoreCase("CSS")){
			
			return By.cssSelector(p.getProperty(objectName));
			
		}
		//find by link
		else if(objectType.equalsIgnoreCase("LINK")){
			
			return By.linkText(p.getProperty(objectName));
			
		}
		//find by partial link
		else if(objectType.equalsIgnoreCase("PARTIALLINK")){
			
			return By.partialLinkText(p.getProperty(objectName));
			
		}else
		{
			throw new Exception("Wrong object type");
		}
	}
}
