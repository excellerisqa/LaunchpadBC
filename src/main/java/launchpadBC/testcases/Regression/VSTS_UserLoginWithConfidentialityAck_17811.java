package launchpadBC.testcases.Regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Acknowledgement;
import launchpadBC.objects.BC_MainPage;
import utils.CommonFunctions;
import utils.ObjectHelper;

public class VSTS_UserLoginWithConfidentialityAck_17811 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void validateAuditTrail(boolean bugflag, boolean exceptionflag) throws Exception {
		try {

			// need new user
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_17811");
			BaseClass_Regression.bh.getAllSteps(11423);
			System.out.println("VSTS_UserLoginWithConfidentialityAck_17811 started");
			Thread.sleep(4000);

			// *[@id="ctl00_agreementContent_AgreeButton"]

			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Acknowledgement.agreebtn));
			Thread.sleep(2000);

			ObjectHelper.driver.switchTo().defaultContent();

			CommonFunctions.waitForVisiblity(BC_MainPage.logoutlink, 20);

			Thread.sleep(2000);

		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_17811",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
