package launchpadBC.testcases.EndUser;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_AuditReview;
import launchpadBC.objects.BC_MainPage;
import utils.ObjectHelper;

public class VSTS_11423_EUser extends BaseClass_EUser {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void validateAuditTrail(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11423");
			BaseClass_EUser.bh.getAllSteps(11423);
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.auditreviewlink));
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AuditReview.otherEntity));
			BC_AuditReview.selectentitytype("Laboratory Test - Regular");
			ObjectHelper.driver.findElement(BC_AuditReview.entityTypeID)
					.sendKeys(BaseClass_EUser.xlhandler.getDatabyCell(1, 6, 1));
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AuditReview.reportBtn));
			Thread.sleep(8000);
			int aTrailCount = BC_AuditReview
					.auditTrailCount(ObjectHelper.driver.findElement(BC_AuditReview.trailCount).getText());
			if (!(aTrailCount > 0)) {
				BaseClass_EUser.bh.addexpected(1);
			}
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_EUser.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11423",
				BaseClass_EUser.AssignedTo, BaseClass_EUser.Iterationpath, BaseClass_EUser.CreatedBy,
				BaseClass_EUser.Areapath, 1);
	}
}
