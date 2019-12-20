package launchpadBC.testcases.Regression;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;
import utils.Screenshots;

public class VSTS_SearchPatientByNameSex_16698 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			System.out.println("VSTS_SearchPatientByNameSex_16698 started");
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_16698");
			BaseClass_Regression.bh.getAllSteps(16697);
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(5000);
			BC_Inbox.searchpatientbynamesex(BaseClass_Regression.xlhandler.getDatabyCell(2, 15, 1),
					BaseClass_Regression.xlhandler.getDatabyCell(2, 15, 2));
			ObjectHelper.driver.switchTo().frame("ifSearchResults");
			Thread.sleep(2000);
			System.out.println(ObjectHelper.driver.findElement(BC_Inbox.patientFoundName).getText());
			if (!ObjectHelper.driver.findElement(BC_Inbox.patientFoundName).getText()
					.contains(BaseClass_Regression.xlhandler.getDatabyCell(2, 15, 1))) {
				BaseClass_Regression.bh.addexpected(3);
			}

			Thread.sleep(3000);

			if (!ObjectHelper.driver.findElement(BC_Inbox.patientFoundName).getText()
					.contains(BaseClass_Regression.xlhandler.getDatabyCell(2, 15, 2))) {
				BaseClass_Regression.bh.addexpected(3);
			}
			Thread.sleep(3000);

			if (!ObjectHelper.driver.findElement(BC_Inbox.patientFoundSex).getText().contains("M")) {
				BaseClass_Regression.bh.addexpected(3);
			}

			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(3000);

			System.out.println("VSTS_SearchPatientByNameSex_16698 completed");
		} catch (Exception e) {
			exceptionError = true;
			e.getStackTrace();
			ObjectHelper.test.fail(e.getMessage());
			Screenshots.takeScreenshot("VSTS_SearchPatientByNameSex_16698");
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_16698",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
