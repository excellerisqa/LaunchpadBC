package launchpadBC.testcases.Regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_MainPage;
import launchpadBC.objects.BC_PhysicianEntry;
import utils.ObjectHelper;

public class VSTS_Admin_PhysicianEntrySearchByID_11419 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void verifyLinksPhysicianPage(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11419");
			BaseClass_Regression.bh.getAllSteps(11419);
			System.out.println("VSTS_Admin_PhysicianEntrySearchByID_11419 started");
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.physicianEntryLink));
			Thread.sleep(4000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			ObjectHelper.driver.findElement(BC_PhysicianEntry.providerid)
					.sendKeys(BaseClass_Regression.xlhandler.getDatabyCell(2, 19, 1));
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_PhysicianEntry.searchbtn));
			Thread.sleep(2000);
			BC_PhysicianEntry.selectSearchResult();
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(5000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			if (!BC_PhysicianEntry.findLinks() == true) {
				BaseClass_Regression.bh.addexpected(2);
			}
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_PhysicianEntry.saveButton));
			Thread.sleep(5000);
			BC_MainPage.acceptalert();
			Thread.sleep(10000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11419",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
