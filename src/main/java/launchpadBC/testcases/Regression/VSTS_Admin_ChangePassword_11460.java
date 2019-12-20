package launchpadBC.testcases.Regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_AccountSettings;
import utils.ObjectHelper;

public class VSTS_Admin_ChangePassword_11460 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void hl7Upload(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11460");
			BaseClass_Regression.bh.getAllSteps(11460);
			System.out.println("VSTS_Admin_ChangePassword_11460 started");
			// Click option button
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.optionsBtn));
			Thread.sleep(1000);

			// Click Account setting
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_AccountSettings.accountsettings));
			Thread.sleep(2000);

			// Click Change password link
			ObjectHelper.driver.switchTo().frame("ifUserPreferencePage");
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_AccountSettings.changePassword));
			Thread.sleep(2000);

			// Enter old & new password
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifWS");
			Thread.sleep(1000);

			BaseClass_Regression.xlhandler.setDatabyCell("login", 1, 2,
					BC_AccountSettings.enterPassword(BaseClass_Regression.xlhandler.getDatabyCell(0, 1, 2)));

			Thread.sleep(2000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_AccountSettings.savebtnPassword));

			Thread.sleep(2000);
			if (!(ObjectHelper.driver.findElement(BC_AccountSettings.successfulInfo).getText()
					.contains("Password Changed Successfully."))) {
				BaseClass_Regression.bh.addexpected(3);
			}
			ObjectHelper.driver.switchTo().defaultContent();
			// ObjectHelper.driver.switchTo().frame("ifForSaving");
			Thread.sleep(4000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_AccountSettings.closebtnPassword));

			Thread.sleep(10000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11460",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
