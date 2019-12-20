package launchpadBC.testcases.EndUser;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;

public class VSTS_10339_EUser extends BaseClass_EUser {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_10339");
			BaseClass_EUser.bh.getAllSteps(10339);
			Thread.sleep(1000);
			BC_Inbox.searchpatient(BaseClass_EUser.xlhandler.getDatabyCell(1, 12, 1),
					BaseClass_EUser.xlhandler.getDatabyCell(1, 12, 2));
			ObjectHelper.driver.switchTo().frame("ifSearchResults");
			Thread.sleep(3000);
			JavascriptExecutor executor = (JavascriptExecutor) ObjectHelper.driver;
			executor.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_Inbox.patientFoundName));
			Thread.sleep(3000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifRecords");
			ObjectHelper.driver.switchTo().frame("frame3");
			Thread.sleep(3000);
			executor.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(By.id("selbox")));
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifRecords");
			Thread.sleep(3000);
			executor.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(By.xpath("//table[@id='buttons3']/tbody/tr/td[3]/input")));
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
				if (patient.getText().contains(BaseClass_EUser.xlhandler.getDatabyCell(1, 17, 1))) {
					break;
				}
				if (i == size && !patient.getText().contains(BaseClass_EUser.xlhandler.getDatabyCell(1, 17, 1))) {
					BaseClass_EUser.bh.addexpected(5);
				}
			}
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_EUser.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_10339",
				BaseClass_EUser.AssignedTo, BaseClass_EUser.Iterationpath, BaseClass_EUser.CreatedBy,
				BaseClass_EUser.Areapath, 1);
	}
}
