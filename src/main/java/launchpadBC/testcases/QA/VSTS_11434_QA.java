package launchpadBC.testcases.QA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import launchpadBC.testcases.EndUser.BaseClass_EUser;
import utils.ObjectHelper;

public class VSTS_11434_QA extends BaseClass_EUser {
	boolean step1 = true, exceptionError = false;

	// Please make sure Print prefrences -Pdf Printing check box is checked (For
	// running in production)

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11434");
			BaseClass_EUser.bh.getAllSteps(11434);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].scrollIntoView();", ObjectHelper.driver.findElement(BC_Inbox.print));
			Thread.sleep(2000);
			js.executeScript("arguments[0].scrollIntoView();", ObjectHelper.driver.findElement(BC_Inbox.printAll));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.printAll));
			Thread.sleep(10000);
			if (BC_Inbox.isAlertPresent() == true) {
				ObjectHelper.driver.switchTo().alert().accept();
				System.out.println("Failure to load print server");
				BaseClass_EUser.bh.addexpected(3);
			}
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(2000);
			System.out.println("Img status is " + ObjectHelper.driver
					.findElement(By.xpath("//table[@id='ResultTable']/tbody/tr[1]/td[7]/img")).isDisplayed());
			if (ObjectHelper.driver.findElement(By.xpath("//table[@id='ResultTable']/tbody/tr[1]/td[7]/img"))
					.isDisplayed() == false) {
				BaseClass_EUser.bh.addexpected(3);
			}
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass_EUser.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_EUser.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11434",
				BaseClass_EUser.AssignedTo, BaseClass_EUser.Iterationpath, BaseClass_EUser.CreatedBy,
				BaseClass_EUser.Areapath, 1);
	}
}
