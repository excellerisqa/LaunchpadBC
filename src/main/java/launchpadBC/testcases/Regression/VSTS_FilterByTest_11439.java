package launchpadBC.testcases.Regression;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;

public class VSTS_FilterByTest_11439 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void filterByTest(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11439");
			// BaseClass_EUser.bh.getAllSteps(11439);
			System.out.println("VSTS_FilterByTest_11439 started");
			Thread.sleep(8000);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			ObjectHelper.driver.switchTo().window(parentwindow);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifInbox");
			int sizeBefore = ObjectHelper.driver.findElements(BC_Inbox.resultsBAFilter).size();
			Thread.sleep(2000);
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
					"div text:" + ObjectHelper.driver.findElement(By.xpath("//div[@id='selTest_item_2']")).getText());
			Thread.sleep(2000);
			ObjectHelper.driver.findElement(By.xpath("//div[@id='selTest_item_2']")).click();

			Thread.sleep(2000);

			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.applyBtn));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.resetFilter));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.closeFilter));

			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifInbox");
			int sizeAfter = ObjectHelper.driver.findElements(BC_Inbox.resultsBAFilter).size();
			Thread.sleep(3000);
			if (sizeAfter >= sizeBefore) {
				BaseClass_Regression.bh.addexpected(1);
			}
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass_EUser.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11439",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}

}
