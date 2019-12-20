package launchpadBC.testcases.Regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_AuditReview;
import launchpadBC.objects.BC_MainPage;
import utils.ObjectHelper;

public class VSTS_Admin_AuditReview_11423 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void validateAuditTrail(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11423");
			BaseClass_Regression.bh.getAllSteps(11423);
			System.out.println("VSTS_11423_Audit Review started");
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.auditreviewlink));
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AuditReview.otherEntity));
			BC_AuditReview.selectentitytype("Laboratory Test - Regular");
			ObjectHelper.driver.findElement(BC_AuditReview.entityTypeID)
					.sendKeys(BaseClass_Regression.xlhandler.getDatabyCell(2, 23, 1));
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AuditReview.reportBtn));
			Thread.sleep(8000);
			int aTrailCount = BC_AuditReview
					.auditTrailCount(ObjectHelper.driver.findElement(BC_AuditReview.trailCount).getText());
			if (!(aTrailCount > 0)) {
				BaseClass_Regression.bh.addexpected(1);
			}
			Thread.sleep(12000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11423",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
