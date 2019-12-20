package launchpadBC.testcases.QA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import launchpadBC.testcases.EndUser.BaseClass_EUser;
import utils.ObjectHelper;

public class VSTS_10324_QA extends BaseClass_EUser {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testCummilativeReport(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_10324");
			BaseClass_EUser.bh.getAllSteps(10324);
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(1000);
			// modified from here for Cumulative report patient search

			ObjectHelper.driver.switchTo().frame("ifPatientSearch");
			BC_Inbox.enterText(BaseClass_EUser.xlhandler.getDatabyCell(1, 12, 1),
					BaseClass_EUser.xlhandler.getDatabyCell(1, 12, 2));
			Thread.sleep(2000);
			BC_Inbox.enterText(BaseClass_EUser.xlhandler.getDatabyCell(1, 12, 3),
					BaseClass_EUser.xlhandler.getDatabyCell(1, 12, 4));
			Thread.sleep(4000);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.searchbtn));
			ObjectHelper.driver.switchTo().defaultContent();

			// modified till here for Cumulative report patient search

			// BC_Inbox.searchpatient(BaseClass_EUser.xlhandler.getDatabyCell(1, 12,
			// 1),BaseClass_EUser.xlhandler.getDatabyCell(1, 12, 2));

			ObjectHelper.driver.switchTo().frame("ifSearchResults");
			Thread.sleep(3000);
			// JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.patientFoundName));
			Thread.sleep(3000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifRecords");
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("RecordTabs5")));
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().frame("frame5");
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("CreateReportButton")));
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(5000);
			System.out.println("zzzzzzzzz" + ObjectHelper.driver
					.findElement(By.xpath("//div[@id=\"divWS\"]/table/tbody/tr[3]/td/table/tbody/tr/td[2]")).getText());
			System.out.println("zzzzzzzzz" + BaseClass_EUser.xlhandler.getDatabyCell(1, 18, 1));
			if (!ObjectHelper.driver
					.findElement(By.xpath("//div[@id=\"divWS\"]/table/tbody/tr[3]/td/table/tbody/tr/td[2]")).getText()
					.contains(BaseClass_EUser.xlhandler.getDatabyCell(1, 18, 1))) {
				System.out.println("Failed");
				BaseClass_EUser.bh.addexpected(10);
			}
			ObjectHelper.driver.switchTo().defaultContent();
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.closeReport));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("InboxButton")));
			Thread.sleep(1000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass_EUser.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_EUser.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_10324",
				BaseClass_EUser.AssignedTo, BaseClass_EUser.Iterationpath, BaseClass_EUser.CreatedBy,
				BaseClass_EUser.Areapath, 7878);
	}

}