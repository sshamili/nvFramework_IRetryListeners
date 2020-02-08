package nv.selenium.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import nv.selenium.api.base.SeleniumBase;

public class CommonLocators extends SeleniumBase{

	@FindBy(xpath="//tr/td/span") WebElement eletable;
}
