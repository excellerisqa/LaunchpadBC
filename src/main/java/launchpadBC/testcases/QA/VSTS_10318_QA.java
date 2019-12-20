package launchpadBC.testcases.QA;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import launchpadBC.testcases.EndUser.BaseClass_EUser;
import utils.ObjectHelper;

public class VSTS_10318_QA extends BaseClass_EUser {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_10318");
			BaseClass_EUser.bh.getAllSteps(10318);
			Thread.sleep(3000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			WebElement element = ObjectHelper.driver.findElement(BC_Inbox.view);
			js.executeScript("arguments[0].scrollIntoView();", element);
			WebElement element2 = ObjectHelper.driver.findElement(BC_Inbox.individualReportsView);
			js.executeScript("arguments[0].click();", element2);
			Thread.sleep(3000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifWS");
			Thread.sleep(2000);
			if (ObjectHelper.driver.findElement(BC_Inbox.reportVerify).getText().contains("Patient:") == false) {
				BaseClass_EUser.bh.addexpected(1);
			}
			ObjectHelper.driver.switchTo().defaultContent();
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.closeReport));
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass_EUser.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_EUser.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_10318",
				BaseClass_EUser.AssignedTo, BaseClass_EUser.Iterationpath, BaseClass_EUser.CreatedBy,
				BaseClass_EUser.Areapath, 1);
	}
}
