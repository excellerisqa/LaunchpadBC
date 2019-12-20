package launchpadBC.testcases.Regression;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;
import utils.Screenshots;

public class VSTS_PrintFromReportView_10327 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_10318");
			BaseClass_Regression.bh.getAllSteps(10318);
			System.out.println("VSTS_PrintFromReportView_10327 started");
			Thread.sleep(3000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(3000);
			System.out.println(ObjectHelper.driver.findElements(BC_Inbox.resultsBAFilter).size());
			// int size = ObjectHelper.driver.findElements(BC_Inbox.resultsBAFilter).size()
			// / 2;

			Actions builder = new Actions(ObjectHelper.driver);
			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.view)).perform();
			Thread.sleep(2000);
			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.individualReportsView)).click().perform();

			Thread.sleep(3000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifWS");

			Thread.sleep(5000);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;

			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.print));

			Thread.sleep(2000);

			ObjectHelper.driver.switchTo().defaultContent();

			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.closeReport));

			// if
			// (ObjectHelper.driver.findElement(By.xpath("//table[@id='ResultTable']/tbody/tr[1]/td[7]/img"))
			// .isDisplayed() == false) {
			// BaseClass_Regression.bh.addexpected(3);
			// }

		} catch (Exception e) {
			exceptionError = true;
			e.getStackTrace();
			ObjectHelper.test.fail(e.getMessage());
			Screenshots.takeScreenshot("VSTS_PrintFromReportView_10327");
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_10327",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
