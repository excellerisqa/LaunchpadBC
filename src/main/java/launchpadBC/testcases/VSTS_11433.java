package launchpadBC.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;

public class VSTS_11433 extends BaseClass {

	boolean step1 = true, exceptionError = false;
	String printtype = "Selection";

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11433");
			BaseClass.bh.getAllSteps(11433);
			System.out.println("VSTS_11433_User can view the report from history started");
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(3000);
			BC_Inbox.searchpatient(BaseClass.xlhandler.getDatabyCell(1, 12, 1),
					BaseClass.xlhandler.getDatabyCell(1, 12, 2));
			JavascriptExecutor executor = (JavascriptExecutor) ObjectHelper.driver;
			ObjectHelper.driver.switchTo().frame("ifSearchResults");
			Thread.sleep(6000);
			executor.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.patientFoundName));
			Thread.sleep(3000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifRecords");
			ObjectHelper.driver.switchTo().frame("frame0");
			Thread.sleep(3000);
			executor.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("selbox")));
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(1000);
			ObjectHelper.driver.switchTo().frame("ifRecords");
			Thread.sleep(3000);
			executor.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(By.xpath("//table[@id='buttons0']/tbody/tr/td[5]/input")));
			Thread.sleep(6000);
			ObjectHelper.driver.switchTo().defaultContent();
			if (!ObjectHelper.driver.findElement(By.id("tdWSTitle")).isDisplayed()) {
				System.out.println("Failed");
				BaseClass.bh.addexpected(5);
			}
			ObjectHelper.driver.switchTo().defaultContent();
			executor.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.closeReport));
			Thread.sleep(3000);
			executor.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("InboxButton")));
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11433", BaseClass.AssignedTo,
				BaseClass.Iterationpath, BaseClass.CreatedBy, BaseClass.Areapath, 1);
	}

}
