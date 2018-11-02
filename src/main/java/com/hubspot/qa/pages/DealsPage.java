package com.hubspot.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DealsPage extends BasePage {

	@FindBy(xpath = "//span[text()='Create deal']")
	WebElement createDeal;

	@FindBy(xpath = "//input[@id='uid-ctrl-1']")
	WebElement dealname;

	@FindBy(xpath = "(//span[@class='private-dropdown__button-label uiDropdown__buttonLabel'])[3]")
	WebElement pipeline;

	@FindBy(xpath = "(//span[@class='private-dropdown__button-label uiDropdown__buttonLabel'])[4]")
	WebElement dealstage;

	@FindBy(id = "uid-ctrl-4")
	WebElement amount;

	@FindBy(xpath = "//div//span[text()='Create']")
	WebElement createDealSecondBtn;

	public DealsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void createNewDeal(String dealName, String pipeLine, String dealStage, String totalAmount) {
		//Select select = null;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(createDeal));
		createDeal.click();

		wait.until(ExpectedConditions.elementToBeClickable(dealname));
		dealname.sendKeys(dealName);

		wait.until(ExpectedConditions.elementToBeClickable(pipeline));
		
		dealstage.click();
		List<WebElement> pipeList = driver.findElements(By.xpath("//*[@id='dropdown-203']/div/ul/li[2]/button/span/span/span"));
		System.out.println(pipeList);
		for (int i = 0; i < pipeList.size(); i++) {
			String pipeLink = pipeList.get(i).getText();
			System.out.println(pipeLink);

			if (pipeLink.equals(pipeLine)) {
				pipeList.get(i).click();
				break;
			}
		}
		
		//select = new Select(pipeline);
		//select.selectByVisibleText(pipeLine);

		wait.until(ExpectedConditions.elementToBeClickable(dealstage));
		// dealstage.sendKeys(dealStage);//*[@id="dropdown-198"]/div/ul/li/button/span/span/span
		// *[@id="dropdown-198"]/div/ul/li[4]/button/span/span/span

		// *[@id="dropdown-203"]/div/ul/li[2]/button/span/span/span
		dealstage.click();
		List<WebElement> stageList = driver.findElements(By.xpath("//*[@id='dropdown-198']/div/ul/li/button/span/span/span"));
		System.out.println(stageList);
		for (int i = 0; i < stageList.size(); i++) {
			String dealLink = stageList.get(i).getText();
			System.out.println(dealLink);

			if (dealLink.equals(dealStage)) {
				stageList.get(i).click();
				break;
			}
		}
		//select = new Select(dealstage);
		//select.selectByVisibleText(dealStage);

		wait.until(ExpectedConditions.elementToBeClickable(amount));

		amount.sendKeys(totalAmount);

		wait.until(ExpectedConditions.elementToBeClickable(createDealSecondBtn));
		createDealSecondBtn.click();
		
		driver.navigate().refresh();
	}

}
