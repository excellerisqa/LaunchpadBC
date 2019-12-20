package launchpadBC.testcases.Regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_AccountSettings;
import utils.ObjectHelper;
import utils.XLHandler;

public class VSTS_Admin_ModifyPIN_19325 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void hl7Upload(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_19325");
			BaseClass_Regression.bh.getAllSteps(19325);
			System.out.println("VSTS_Admin_ModifyPIN_19325 started");
			// Click option button
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.optionsBtn));
			Thread.sleep(1000);

			// Click Account setting
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_AccountSettings.accountsettings));
			Thread.sleep(2000);

			// Click user profile & email account link
			ObjectHelper.driver.switchTo().frame("ifUserPreferencePage");
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.userProfile));
			Thread.sleep(2000);

			// change pin
			if (ObjectHelper.driver.findElement(BC_AccountSettings.resetPin).isDisplayed()) {
				js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.resetPin));
			}

			String[] newPin = XLHandler.readexcel(2, 27, testDataFileName); // get test data from sheet
			BC_AccountSettings.changePin(newPin);

			// click save button
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_AccountSettings.userPrefSavebtn));

			// click user profile & email account link
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.userProfile));

			if (!(ObjectHelper.driver.findElement(BC_AccountSettings.pinLable).getText()
					.contains("You have already stored your Account PIN"))) {
				BaseClass_Regression.bh.addexpected(4);
			}

			Thread.sleep(10000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_19325",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
