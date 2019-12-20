package launchpadBC.testcases.Regression;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.CommonFunctions;
import utils.ObjectHelper;
import utils.Screenshots;

public class VSTS_UpdateStatusViewedToClearOverNight_11429 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void validateAuditTrail(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11429");
			BaseClass_Regression.bh.getAllSteps(11429);
			System.out.println("VSTS_UpdateStatusViewedToClearOverNight_11429 started");
			Thread.sleep(5000);
			ObjectHelper.driver.switchTo().defaultContent();
			CommonFunctions.waitForVisiblity(ObjectHelper.driver.findElement(By.id("ifInbox")), 20);
			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(3000);

			Actions builder = new Actions(ObjectHelper.driver);
			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.status)).perform();
			Thread.sleep(2000);
			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.viewedtoclear)).click().perform();
			Thread.sleep(3000);

			if (!ObjectHelper.driver.findElement(By.xpath("//table[@id='ResultTable']/tbody/tr[1]/td[7]"))
					.getAttribute("outerHTML").contains("img")) {
				BaseClass_Regression.bh.addexpected(3);
			}

			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			e.getStackTrace();
			ObjectHelper.test.fail(e.getMessage());
			Screenshots.takeScreenshot("VSTS_UpdateStatusViewedToClearOverNight_11429");

		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11429",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
