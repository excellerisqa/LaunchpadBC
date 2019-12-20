package launchpadBC.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;

public class VSTS_11427 extends BaseClass {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			System.out.println("VSTS_11427_Search patient by PHN started");
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11427");
			BaseClass.bh.getAllSteps(11427);
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(10000);
			BC_Inbox.searchpatient(BaseClass.xlhandler.getDatabyCell(1, 12, 1),
					BaseClass.xlhandler.getDatabyCell(1, 12, 2));
			ObjectHelper.driver.switchTo().frame("ifSearchResults");
			Thread.sleep(4000);
			if (!ObjectHelper.driver.findElement(BC_Inbox.patientFound).getText()
					.contains(BaseClass.xlhandler.getDatabyCell(1, 12, 2))) {
				BaseClass.bh.addexpected(3);
			}
			System.out.println("Patient found");
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("InboxButton")));
			System.out.println("VSTS 11427 completed");
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11427", BaseClass.AssignedTo,
				BaseClass.Iterationpath, BaseClass.CreatedBy, BaseClass.Areapath, 1);
	}
}
