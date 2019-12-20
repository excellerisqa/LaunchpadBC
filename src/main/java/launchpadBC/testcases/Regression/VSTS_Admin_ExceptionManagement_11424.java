package launchpadBC.testcases.Regression;

import java.time.LocalDate;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_ExceptionManagement;
import launchpadBC.objects.BC_MainPage;
import launchpadON.objects.ON_ExceptionManagement;
import utils.ObjectHelper;

public class VSTS_Admin_ExceptionManagement_11424 extends BaseClass_Regression {

	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void exceptionResultsFound(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11424");
			BaseClass_Regression.bh.getAllSteps(11424);
			System.out.println("VSTS_11424_Exception Management started");
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.exceptionMangLink));
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			ObjectHelper.driver.findElement(ON_ExceptionManagement.startDate)
					.sendKeys(LocalDate.now().minusMonths(2).toString());
			Thread.sleep(2000);
			ObjectHelper.driver.findElement(ON_ExceptionManagement.endDate).sendKeys(LocalDate.now().toString());
			Thread.sleep(2000);
			ObjectHelper.driver.findElement(BC_ExceptionManagement.freeText).sendKeys("");
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_ExceptionManagement.applyBtn));
			Thread.sleep(5000);
			int numberOfExceptionResults = (ObjectHelper.driver.findElements(BC_ExceptionManagement.numberOfResults)
					.size() - 1);
			if (!(numberOfExceptionResults > 0)) {
				BaseClass_Regression.bh.addexpected(1);
			}
			Thread.sleep(10000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11424",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
