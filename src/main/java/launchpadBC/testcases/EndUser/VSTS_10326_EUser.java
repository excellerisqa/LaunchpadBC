package launchpadBC.testcases.EndUser;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;

public class VSTS_10326_EUser extends BaseClass_EUser {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_10326");
			BaseClass_EUser.bh.getAllSteps(10326);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(2000);
			js.executeScript("arguments[0].scrollIntoView();", ObjectHelper.driver.findElement(BC_Inbox.view));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.multipleReportsView));
			Thread.sleep(5000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifWS");
			Thread.sleep(3000);
			if (ObjectHelper.driver.findElement(BC_Inbox.multiResultsVerify).isEnabled() == false) {
				BaseClass_EUser.bh.addexpected(1);
			}
			ObjectHelper.driver.switchTo().defaultContent();
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.closeReport));
			ObjectHelper.driver.switchTo().frame("ifInboxFilter");
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.closeFilter));
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_EUser.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_10326", BaseClass_EUser.AssignedTo,
				BaseClass_EUser.Iterationpath, BaseClass_EUser.CreatedBy, BaseClass_EUser.Areapath, 1);
	}

}
