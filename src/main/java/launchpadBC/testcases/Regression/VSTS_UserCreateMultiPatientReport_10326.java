package launchpadBC.testcases.Regression;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;

public class VSTS_UserCreateMultiPatientReport_10326 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_10326");
			BaseClass_Regression.bh.getAllSteps(10326);
			System.out.println("VSTS_10326_Create Multipatient Report started");
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifInbox");

			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.filterbtn));
			Thread.sleep(4000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifInboxFilter");
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.testcheckBox));
			Thread.sleep(3000);

			js.executeScript("arguments[0].click();", ObjectHelper.driver
					.findElement(By.xpath("//input[@id='chkTest']//parent::td//img[@id='selTest_drop']")));
			Thread.sleep(2000);
			System.out.println(
					"div text:" + ObjectHelper.driver.findElement(By.xpath("//div[@id='selTest_item_4']")).getText());
			Thread.sleep(2000);
			ObjectHelper.driver.findElement(By.xpath("//div[@id='selTest_item_4']")).click();
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.applyBtn));
			Thread.sleep(3000);

			ObjectHelper.driver.switchTo().defaultContent();

			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(2000);
			js.executeScript("arguments[0].scrollIntoView();", ObjectHelper.driver.findElement(BC_Inbox.view));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.multipleReportsView));
			Thread.sleep(5000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifWS");
			Thread.sleep(3000);
			if (ObjectHelper.driver.findElement(BC_Inbox.multiResultsVerify).isEnabled() == false) {
				BaseClass_Regression.bh.addexpected(1);
			}
			ObjectHelper.driver.switchTo().defaultContent();

		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_10326",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}

}
