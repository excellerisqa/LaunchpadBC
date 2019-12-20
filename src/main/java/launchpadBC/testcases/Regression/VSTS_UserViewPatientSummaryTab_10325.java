package launchpadBC.testcases.Regression;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;
import utils.Screenshots;

public class VSTS_UserViewPatientSummaryTab_10325 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testCummilativeReport(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_10325");
			BaseClass_Regression.bh.getAllSteps(10324);
			System.out.println("VSTS_UserViewPatientSummaryTab_10325 started");
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(1000);
			// modified from here for Cumulative report patient search

			// ObjectHelper.driver.switchTo().frame("ifPatientSearch");
			// BC_Inbox.enterText(BaseClass_Regression.xlhandler.getDatabyCell(2, 16, 1),
			// BaseClass_Regression.xlhandler.getDatabyCell(2, 16, 2));

			BC_Inbox.searchpatientbynamesex(BaseClass_Regression.xlhandler.getDatabyCell(2, 15, 1),
					BaseClass_Regression.xlhandler.getDatabyCell(2, 15, 2));

			Thread.sleep(2000);

			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			// js.executeScript("arguments[0].click();",
			// ObjectHelper.driver.findElement(BC_Inbox.searchbtn));
			ObjectHelper.driver.switchTo().defaultContent();

			ObjectHelper.driver.switchTo().frame("ifSearchResults");
			Thread.sleep(3000);

			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.patientFoundName));
			Thread.sleep(3000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifRecords");
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("RecordTabs0")));
			System.out.println(ObjectHelper.driver.findElement(By.id("RecordTabs0")).getText());
			if (!ObjectHelper.driver.findElement(By.id("RecordTabs0")).getText().contains("Laboratory")) {
				System.out.println("Failed");
				BaseClass_Regression.bh.addexpected(3);
			}

			Thread.sleep(3000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("RecordTabs1")));
			System.out.println(ObjectHelper.driver.findElement(By.id("RecordTabs1")).getText());
			if (!ObjectHelper.driver.findElement(By.id("RecordTabs1")).getText().contains("Pathology")) {
				System.out.println("Failed");
				BaseClass_Regression.bh.addexpected(3);
			}

			Thread.sleep(3000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("RecordTabs2")));
			System.out.println(ObjectHelper.driver.findElement(By.id("RecordTabs2")).getText());
			if (!ObjectHelper.driver.findElement(By.id("RecordTabs2")).getText().contains("Imaging")) {
				System.out.println("Failed");
				BaseClass_Regression.bh.addexpected(3);
			}

			Thread.sleep(3000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("RecordTabs3")));
			System.out.println(ObjectHelper.driver.findElement(By.id("RecordTabs3")).getText());
			if (!ObjectHelper.driver.findElement(By.id("RecordTabs3")).getText().contains("Clinical Documents")) {
				System.out.println("Failed");
				BaseClass_Regression.bh.addexpected(3);
			}

			Thread.sleep(3000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("RecordTabs4")));
			System.out.println(ObjectHelper.driver.findElement(By.id("RecordTabs4")).getText());
			if (!ObjectHelper.driver.findElement(By.id("RecordTabs4")).getText().contains("Referral / Consult")) {
				System.out.println("Failed");
				BaseClass_Regression.bh.addexpected(3);
			}

			ObjectHelper.driver.switchTo().defaultContent();

			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("InboxButton")));
			Thread.sleep(1000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			e.getStackTrace();
			ObjectHelper.test.fail(e.getMessage());
			Screenshots.takeScreenshot("VSTS_UserViewPatientSummaryTab_10325");
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_10325",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 7878);
	}

}