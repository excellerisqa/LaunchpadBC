package launchpadBC.testcases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_ExceptionManagement;
import launchpadBC.objects.BC_MainPage;
import utils.ObjectHelper;

public class VSTS_11424 extends BaseClass {

	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void exceptionResultsFound(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11424");
			BaseClass.bh.getAllSteps(11424);
			System.out.println("VSTS_11424_Exception Management started");
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.exceptionMangLink));
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			ObjectHelper.driver.findElement(BC_ExceptionManagement.startDate).sendKeys("2018-06-01");
			Thread.sleep(2000);
			ObjectHelper.driver.findElement(BC_ExceptionManagement.endDate).sendKeys("2018-06-01");
			Thread.sleep(2000);
			ObjectHelper.driver.findElement(BC_ExceptionManagement.freeText).sendKeys("");
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_ExceptionManagement.applyBtn));

			Thread.sleep(12000);
			int numberOfExceptionResults = (ObjectHelper.driver.findElements(BC_ExceptionManagement.numberOfResults)
					.size() - 1);
			if (!(numberOfExceptionResults > 0)) {
				BaseClass.bh.addexpected(1);
			}
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11424", BaseClass.AssignedTo,
				BaseClass.Iterationpath, BaseClass.CreatedBy, BaseClass.Areapath, 1);
	}
}
