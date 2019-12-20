package launchpadBC.testcases.Regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_AccountSettings;
import utils.CheckandFetchEmail;
import utils.CommonFunctions;
import utils.ObjectHelper;

public class VSTS_Admin_ModifyEmail_19326 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void hl7Upload(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_19326");
			BaseClass_Regression.bh.getAllSteps(19326);
			System.out.println("VSTS_Admin_ModifyEmail_19326 started");
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

			// click discard button to reset back to original
			// if (CommonFunctions.waitForVisiblity(BC_AccountSettings.discardEmailbtn, 5))
			// {
			// js.executeScript("arguments[0].click();",
			// ObjectHelper.driver.findElement(BC_AccountSettings.discardEmailbtn));
			// }

			Thread.sleep(2000);

			ObjectHelper.driver.findElement(BC_AccountSettings.emailtxt).clear();
			ObjectHelper.driver.findElement(BC_AccountSettings.emailtxt)
					.sendKeys(BaseClass_Regression.xlhandler.getDatabyCell(2, 28, 1));

			// click Email Me Now button
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_AccountSettings.emailMeNowbtn));

			Thread.sleep(20000);
			// enter validation code
			System.out.println(CheckandFetchEmail.check("outlook.office365.com", "587", "pop3", "szhang@excelleris.com",
					"4uSuccess11", "noreply-support@excelleris.com", "Validation code: <b>", "</b>"));
			ObjectHelper.driver.findElement(BC_AccountSettings.validationCodetxt).clear();
			ObjectHelper.driver.findElement(BC_AccountSettings.validationCodetxt)
					.sendKeys(CheckandFetchEmail.check("outlook.office365.com", "587", "pop3", "szhang@excelleris.com",
							"4uSuccess11", "noreply-support@excelleris.com", "Validation code: <b>", "</b>"));

			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.validatedbtn));

			Thread.sleep(1000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_AccountSettings.userPrefSavebtn));

			// click user profile & email account link
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AccountSettings.userProfile));

			if (!(ObjectHelper.driver.findElement(BC_AccountSettings.emailConfirmInfo).getText().contains(
					"Change the email address will affect some features of Launchpad application such as Alert"))) {
				BaseClass_Regression.bh.addexpected(4);
			} else {
				CommonFunctions
						.clickUsingJavaExecutor(ObjectHelper.driver.findElement(BC_AccountSettings.deleteEmaillink));
				Thread.sleep(1000);
				ObjectHelper.driver.switchTo().alert().accept();
				js.executeScript("arguments[0].click();",
						ObjectHelper.driver.findElement(BC_AccountSettings.userPrefSavebtn));
			}

			Thread.sleep(10000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
			e.printStackTrace();
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_19326",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
