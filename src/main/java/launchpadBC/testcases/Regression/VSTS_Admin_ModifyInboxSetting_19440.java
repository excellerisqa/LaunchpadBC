package launchpadBC.testcases.Regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_AccountSettings;
import utils.ObjectHelper;

public class VSTS_Admin_ModifyInboxSetting_19440 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void hl7Upload(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_19440");
			BaseClass_Regression.bh.getAllSteps(19440);
			System.out.println("VSTS_Admin_ModifyInboxSetting_19440 started");
			// Click option button
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.optionsBtn));
			Thread.sleep(1000);

			// Click Account setting
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_AccountSettings.accountsettings));
			Thread.sleep(2000);

			// Click inbox setting link
			ObjectHelper.driver.switchTo().frame("ifUserPreferencePage");
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.inboxSetting));
			Thread.sleep(2000);

			// Modify inbox setting
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_AccountSettings.inboxPrintOrder2));

			// click save button
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.inboxSavebtn));

			// click inbox setting link
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.inboxSetting));

			// check if saved
			if (!ObjectHelper.driver.findElement(BC_AccountSettings.inboxPrintOrder2).isSelected()) {
				BaseClass_Regression.bh.addexpected(4);
			}

			Thread.sleep(10000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_19440",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
