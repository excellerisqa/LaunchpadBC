package launchpadBC.testcases.Regression;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;
import utils.Screenshots;

public class VSTS_UserResetToInbox_10339 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_10339");
			BaseClass_Regression.bh.getAllSteps(10339);
			System.out.println("VSTS_UserResetToInbox_10339 started");
			Thread.sleep(1000);
			BC_Inbox.searchpatient(BaseClass_Regression.xlhandler.getDatabyCell(2, 16, 1),
					BaseClass_Regression.xlhandler.getDatabyCell(2, 16, 2));
			ObjectHelper.driver.switchTo().frame("ifSearchResults");
			Thread.sleep(3000);
			JavascriptExecutor executor = (JavascriptExecutor) ObjectHelper.driver;
			executor.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.patientFoundName));
			Thread.sleep(3000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifRecords");
			ObjectHelper.driver.switchTo().frame("frame0");
			Thread.sleep(3000);
			executor.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("selbox")));
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifRecords");
			Thread.sleep(3000);
			executor.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(By.xpath("//table[@id='buttons0']/tbody/tr/td[3]/input")));
			Thread.sleep(3000);
			ObjectHelper.driver.switchTo().alert().accept();
			Thread.sleep(3000);
			ObjectHelper.driver.switchTo().alert().accept();
			Thread.sleep(3000);
			ObjectHelper.driver.switchTo().defaultContent();
			executor.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("InboxButton")));
			Thread.sleep(6000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifInbox");
			String patientList = ObjectHelper.driver.findElement(By.id("InfoMessage")).getText();
			String patientList1 = StringUtils.substringAfter(patientList, ": ");
			int size = Integer.parseInt(patientList1);
			System.out.println("size" + size);
			for (int i = 1; i <= size; i++) {
				WebElement patient = ObjectHelper.driver
						.findElement(By.xpath("//table[@id='ResultTable']/tbody/tr[" + i + "]/td[3]"));
				System.out.println("i" + patient.getText());
				if (patient.getText().contains(BaseClass_Regression.xlhandler.getDatabyCell(2, 15, 1))) {
					break;
				}
				if (i == size && !patient.getText().contains(BaseClass_Regression.xlhandler.getDatabyCell(2, 15, 1))) {
					BaseClass_Regression.bh.addexpected(5);
				}
			}
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			e.getStackTrace();
			ObjectHelper.test.fail(e.getMessage());
			Screenshots.takeScreenshot("VSTS_UserResetToInbox_10339");
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_10339",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
