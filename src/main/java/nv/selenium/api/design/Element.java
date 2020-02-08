package nv.selenium.api.design;

import org.openqa.selenium.WebElement;

public interface Element {
	public void click(WebElement ele);
	public void clearAndType(WebElement ele,String data);
	public boolean verifyPartialText(WebElement ele, String expectedText);
}
