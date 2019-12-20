package launchpadBC.testcases.Regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_AccountSettings;
import utils.ObjectHelper;
import utils.XLHandler;

public class VSTS_Admin_ModifyProfile_19324 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void hl7Upload(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_19324");
			BaseClass_Regression.bh.getAllSteps(19324);
			System.out.println("VSTS_Admin_ModifyProfile_19324 started");
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

			// Modify profile
			String[] userProfile = XLHandler.readexcel(2, 26, testDataFileName); // get testdata from sheet
			BC_AccountSettings.modifyProfile(userProfile);

			// click save button
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_AccountSettings.userPrefSavebtn));

			// click user profile & email account link
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.userProfile));

			System.out.println(ObjectHelper.driver.findElement(BC_AccountSettings.firstName).getAttribute("value"));
			System.out.println(BaseClass_Regression.xlhandler.getDatabyCell(2, 26, 1));

			// check if first name updated
			String updatedValue = ObjectHelper.driver.findElement(BC_AccountSettings.firstName).getAttribute("value")
					.replaceAll(" ", "");
			String orignalValue = BaseClass_Regression.xlhandler.getDatabyCell(2, 26, 1).replaceAll(" ", "");

			if (!(updatedValue.equalsIgnoreCase(orignalValue))) {
				BaseClass_Regression.bh.addexpected(4);
			}

			// check if last name updated
			if (!(ObjectHelper.driver.findElement(BC_AccountSettings.lastName).getAttribute("value").replaceAll(" ", "")
					.equalsIgnoreCase(BaseClass_Regression.xlhandler.getDatabyCell(2, 26, 2).replaceAll(" ", "")))) {
				BaseClass_Regression.bh.addexpected(4);
			}

			if (!(ObjectHelper.driver.findElement(BC_AccountSettings.street1).getAttribute("value").replaceAll(" ", "")
					.equalsIgnoreCase(BaseClass_Regression.xlhandler.getDatabyCell(2, 26, 3).replaceAll(" ", "")))) {
				BaseClass_Regression.bh.addexpected(4);
			}

			if (!(ObjectHelper.driver.findElement(BC_AccountSettings.street2).getAttribute("value").replaceAll(" ", "")
					.equalsIgnoreCase(BaseClass_Regression.xlhandler.getDatabyCell(2, 26, 4).replaceAll(" ", "")))) {
				BaseClass_Regression.bh.addexpected(4);
			}

			if (!(ObjectHelper.driver.findElement(BC_AccountSettings.city).getAttribute("value").replaceAll(" ", "")
					.equalsIgnoreCase(BaseClass_Regression.xlhandler.getDatabyCell(2, 26, 5).replaceAll(" ", "")))) {
				BaseClass_Regression.bh.addexpected(4);
			}

			if (!(ObjectHelper.driver.findElement(BC_AccountSettings.province).getAttribute("value").replaceAll(" ", "")
					.equalsIgnoreCase(BaseClass_Regression.xlhandler.getDatabyCell(2, 26, 6).replaceAll(" ", "")))) {
				BaseClass_Regression.bh.addexpected(4);
			}

			if (!(ObjectHelper.driver.findElement(BC_AccountSettings.postalCode).getAttribute("value")
					.replaceAll(" ", "")
					.equalsIgnoreCase(BaseClass_Regression.xlhandler.getDatabyCell(2, 26, 7).replaceAll(" ", "")))) {
				BaseClass_Regression.bh.addexpected(4);
			}

			if (!(ObjectHelper.driver.findElement(BC_AccountSettings.userPhone).getAttribute("value")
					.replaceAll(" ", "")
					.equalsIgnoreCase(BaseClass_Regression.xlhandler.getDatabyCell(2, 26, 8).replaceAll(" ", "")))) {
				BaseClass_Regression.bh.addexpected(4);
			}

			Thread.sleep(10000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_19324",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
