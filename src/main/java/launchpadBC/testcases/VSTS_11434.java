package launchpadBC.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;

public class VSTS_11434 extends BaseClass {
	boolean step1 = true, exceptionError = false;

	// Please make sure Print prefrences -Pdf Printing check box is checked (For
	// running in production)

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11434");
			BaseClass.bh.getAllSteps(11434);
			System.out.println("VSTS_11434_Print All started");
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(4000);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].scrollIntoView();", ObjectHelper.driver.findElement(BC_Inbox.print));
			Thread.sleep(2000);
			js.executeScript("arguments[0].scrollIntoView();", ObjectHelper.driver.findElement(BC_Inbox.printAll));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.printAll));
			Thread.sleep(10000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(2000);
			System.out.println("Img status is " + ObjectHelper.driver
					.findElement(By.xpath("//table[@id='ResultTable']/tbody/tr[1]/td[7]/img")).isDisplayed());
			if (ObjectHelper.driver.findElement(By.xpath("//table[@id='ResultTable']/tbody/tr[1]/td[7]/img"))
					.isDisplayed() == false) {
				BaseClass.bh.addexpected(3);
			}
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11434", BaseClass.AssignedTo,
				BaseClass.Iterationpath, BaseClass.CreatedBy, BaseClass.Areapath, 1);
	}
}
