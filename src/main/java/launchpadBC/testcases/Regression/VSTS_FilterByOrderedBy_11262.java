package launchpadBC.testcases.Regression;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;

public class VSTS_FilterByOrderedBy_11262 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void filterByTest(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11262");
			// BaseClass.bh.getAllSteps(11255);
			System.out.println("VSTS_FilterByOrderedBy_11262 started");
			Thread.sleep(3000);
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
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.orderbycheckBox));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver
					.findElement(By.xpath("//input[@id='chkOrderedBy']//parent::td//img[@id='selOrderedBy_drop']")));
			Thread.sleep(3000);
			System.out.println("div text:"
					+ ObjectHelper.driver.findElement(By.xpath("//div[@id='selOrderedBy_item_1']")).getText());
			ObjectHelper.driver.findElement(By.xpath("//div[@id='selOrderedBy_item_1']")).click();
			Thread.sleep(3000);
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
			Thread.sleep(9000);

			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			e.getStackTrace();
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11262",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}

}
