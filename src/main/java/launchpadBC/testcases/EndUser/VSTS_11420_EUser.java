package launchpadBC.testcases.EndUser;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_LocationEntry;
import launchpadBC.objects.BC_MainPage;
import utils.ObjectHelper;

public class VSTS_11420_EUser extends BaseClass_EUser {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void verifyLinksLocationEntryPage(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11420");
			BaseClass_EUser.bh.getAllSteps(11420);
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.locationEntryLink));
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			ObjectHelper.driver.findElement(BC_LocationEntry.organizationid)
					.sendKeys(BaseClass_EUser.xlhandler.getDatabyCell(1, 3, 1));
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_LocationEntry.searchbtn));
			Thread.sleep(5000);
			BC_LocationEntry.selectSearchResult();
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			if (!BC_LocationEntry.findLinks() == true) {
				BaseClass_EUser.bh.addexpected(2);
			}
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_LocationEntry.saveButton));
			Thread.sleep(2000);
			BC_MainPage.acceptalert();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_EUser.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11420",
				BaseClass_EUser.AssignedTo, BaseClass_EUser.Iterationpath, BaseClass_EUser.CreatedBy,
				BaseClass_EUser.Areapath, 1);
	}
}
