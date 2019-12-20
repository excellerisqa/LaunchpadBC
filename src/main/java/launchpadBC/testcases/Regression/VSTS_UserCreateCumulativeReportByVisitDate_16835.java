package launchpadBC.testcases.Regression;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;
import utils.Screenshots;

public class VSTS_UserCreateCumulativeReportByVisitDate_16835 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testCummilativeReport(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_16835");
			BaseClass_Regression.bh.getAllSteps(16835);
			System.out.println("VSTS_UserCreateCumulativeReportByVisitDate_16835 started");
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(1000);
			// modified from here for Cumulative report patient search
			BC_Inbox.searchpatientbynamesex(BaseClass_Regression.xlhandler.getDatabyCell(2, 15, 1),
					BaseClass_Regression.xlhandler.getDatabyCell(2, 15, 2));

			Thread.sleep(2000);

			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			ObjectHelper.driver.switchTo().defaultContent();

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

			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.dateVisitRadio));
			Thread.sleep(2000);

			ObjectHelper.driver.findElement(BC_Inbox.sdateVisitText).clear();
			ObjectHelper.driver.findElement(BC_Inbox.sdateVisitText)
					.sendKeys(BaseClass_Regression.xlhandler.getDatabyCell(2, 17, 1));
			Thread.sleep(2000);

			ObjectHelper.driver.findElement(BC_Inbox.edateVisitText).click();
			ObjectHelper.driver.findElement(BC_Inbox.edateVisitText).clear();
			ObjectHelper.driver.findElement(BC_Inbox.edateVisitText)
					.sendKeys(BaseClass_Regression.xlhandler.getDatabyCell(2, 17, 2));

			Thread.sleep(2000);

			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.createReportBtn));
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(5000);

			System.out.println(ObjectHelper.driver.findElement(By.id("tdWSTitle")).getText());

			if (!ObjectHelper.driver.findElement(By.id("tdWSTitle")).getText()
					.contains(BaseClass_Regression.xlhandler.getDatabyCell(2, 17, 1))) {
				System.out.println("Failed");
				BaseClass_Regression.bh.addexpected(3);
			}

			if (!ObjectHelper.driver.findElement(By.id("tdWSTitle")).getText()
					.contains(BaseClass_Regression.xlhandler.getDatabyCell(2, 17, 2))) {
				System.out.println("Failed");
				BaseClass_Regression.bh.addexpected(3);
			}

			ObjectHelper.driver.switchTo().frame("ifWS");

			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.sortOrderbtn));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.swapAxisbtn));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.printCumbtn));
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().defaultContent();

			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.closeReport));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("InboxButton")));
			Thread.sleep(1000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			e.getStackTrace();
			ObjectHelper.test.fail(e.getMessage());
			Screenshots.takeScreenshot("VSTS_UserCreateCumulativeReportByVisitDate_16835");
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_16835",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 7878);
	}

}