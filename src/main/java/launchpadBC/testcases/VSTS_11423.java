package launchpadBC.testcases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_AuditReview;
import launchpadBC.objects.BC_MainPage;
import utils.ObjectHelper;

public class VSTS_11423 extends BaseClass {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void validateAuditTrail(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11423");
			BaseClass.bh.getAllSteps(11423);
			System.out.println("VSTS_11423_Audit Review started");
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.auditreviewlink));
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AuditReview.otherEntity));
			BC_AuditReview.selectentitytype("Laboratory Test - Regular");
			ObjectHelper.driver.findElement(BC_AuditReview.entityTypeID)
					.sendKeys(BaseClass.xlhandler.getDatabyCell(1, 6, 1));
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_AuditReview.reportBtn));
			Thread.sleep(8000);
			int aTrailCount = BC_AuditReview
					.auditTrailCount(ObjectHelper.driver.findElement(BC_AuditReview.trailCount).getText());
			if (!(aTrailCount > 0)) {
				BaseClass.bh.addexpected(1);
			}
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11423", BaseClass.AssignedTo,
				BaseClass.Iterationpath, BaseClass.CreatedBy, BaseClass.Areapath, 1);
	}
}
