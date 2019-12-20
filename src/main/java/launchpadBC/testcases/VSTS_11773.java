package launchpadBC.testcases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_AccountSettings;
import utils.ObjectHelper;

public class VSTS_11773 extends BaseClass {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11773");
			BaseClass.bh.getAllSteps(11773);
			System.out.println("VSTS_11773_Update print preference started");
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.optionsBtn));
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_AccountSettings.accountsettings));
			Thread.sleep(5000);
			ObjectHelper.driver.switchTo().frame("ifUserPreferencePage");
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_AccountSettings.reportPrintPreferences));
			Thread.sleep(2000);
			if (ObjectHelper.driver.findElement(BC_AccountSettings.pdfPrinting).isEnabled() == false) {
				BaseClass.bh.addexpected(1);
			}
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.savebtn));
			Thread.sleep(5000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.backtomain));
			Thread.sleep(4000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11773", BaseClass.AssignedTo,
				BaseClass.Iterationpath, BaseClass.CreatedBy, BaseClass.Areapath, 1);
	}
}