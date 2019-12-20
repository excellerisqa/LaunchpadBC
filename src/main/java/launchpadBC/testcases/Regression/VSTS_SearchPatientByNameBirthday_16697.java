package launchpadBC.testcases.Regression;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;
import utils.Screenshots;

public class VSTS_SearchPatientByNameBirthday_16697 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			System.out.println("VSTS_SearchPatientByNameBirthday_16697 started");
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_16697");
			BaseClass_Regression.bh.getAllSteps(16697);
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(5000);
			BC_Inbox.searchpatientbynamebirthday(BaseClass_Regression.xlhandler.getDatabyCell(2, 14, 1),
					BaseClass_Regression.xlhandler.getDatabyCell(2, 14, 2),
					BaseClass_Regression.xlhandler.getDatabyCell(2, 14, 3));
			ObjectHelper.driver.switchTo().frame("ifSearchResults");
			Thread.sleep(2000);
			System.out.println(ObjectHelper.driver.findElement(BC_Inbox.patientFoundName).getText());
			if (!ObjectHelper.driver.findElement(BC_Inbox.patientFoundName).getText()
					.contains(BaseClass_Regression.xlhandler.getDatabyCell(2, 14, 1))) {
				BaseClass_Regression.bh.addexpected(3);
			}

			if (!ObjectHelper.driver.findElement(BC_Inbox.patientFoundName).getText()
					.contains(BaseClass_Regression.xlhandler.getDatabyCell(2, 14, 2))) {
				BaseClass_Regression.bh.addexpected(3);
			}

			if (!ObjectHelper.driver.findElement(BC_Inbox.patientFoundBirthday).getText()
					.contains(BaseClass_Regression.xlhandler.getDatabyCell(2, 14, 3))) {
				BaseClass_Regression.bh.addexpected(3);
			}

			System.out.println("Patient found");
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(3000);

			System.out.println("VSTS_SearchPatientByNameBirthday_16697 completed");
		} catch (Exception e) {
			exceptionError = true;
			e.getStackTrace();
			ObjectHelper.test.fail(e.getMessage());
			Screenshots.takeScreenshot("VSTS_SearchPatientByNameBirthday_16697");
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_16697",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
