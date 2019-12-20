package launchpadBC.testcases.EndUser;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_MainPage;
import launchpadBC.objects.BC_StatusUpdate;
import utils.ObjectHelper;

public class VSTS_11663_EUser extends BaseClass_EUser {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void statusUpdateAccession(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11663");
			BaseClass_EUser.bh.getAllSteps(11663);
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.statuspdate));
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			ObjectHelper.driver.findElement(BC_StatusUpdate.accessionListBox)
					.sendKeys(BaseClass_EUser.xlhandler.getDatabyCell(1, 7, 1));
			Thread.sleep(2000);
			BC_StatusUpdate.selectupdatetype("ListedDoctors");
			Thread.sleep(2000);
			ObjectHelper.driver.findElement(BC_StatusUpdate.updateDocListBox)
					.sendKeys(BaseClass_EUser.xlhandler.getDatabyCell(1, 7, 2));
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_StatusUpdate.updateBtn));
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().alert().accept();
			Thread.sleep(2000);
			int result = Integer.parseInt(StringUtils.substringBetween(
					ObjectHelper.driver.findElement(BC_StatusUpdate.updateresults).getText(), ": ", " accessions"));
			if (!(result > 0)) {
				BaseClass_EUser.bh.addexpected(5);
			}
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().defaultContent();
			System.out.println("VSTS 11663 completed-last test case in Test Suite 1");
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_EUser.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11663",
				BaseClass_EUser.AssignedTo, BaseClass_EUser.Iterationpath, BaseClass_EUser.CreatedBy,
				BaseClass_EUser.Areapath, 1);
	}
}
