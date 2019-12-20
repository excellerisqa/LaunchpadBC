package launchpadBC.testcases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_MainPage;
import launchpadBC.objects.BC_PhysicianEntry;
import utils.ObjectHelper;

public class VSTS_11419 extends BaseClass {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void verifyLinksPhysicianPage(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11419");
			BaseClass.bh.getAllSteps(11419);
			System.out.println("VSTS_11419_Physician Entry started");
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.physicianEntryLink));
			Thread.sleep(4000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			ObjectHelper.driver.findElement(BC_PhysicianEntry.providerid)
					.sendKeys(BaseClass.xlhandler.getDatabyCell(1, 2, 1));
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_PhysicianEntry.searchbtn));
			Thread.sleep(2000);
			BC_PhysicianEntry.selectSearchResult();
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(5000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			if (!BC_PhysicianEntry.findLinks() == true) {
				BaseClass.bh.addexpected(2);
			}
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_PhysicianEntry.saveButton));
			Thread.sleep(2000);
			BC_MainPage.acceptalert();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11419", BaseClass.AssignedTo,
				BaseClass.Iterationpath, BaseClass.CreatedBy, BaseClass.Areapath, 1);
	}
}
