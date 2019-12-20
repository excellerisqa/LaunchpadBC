package launchpadBC.testcases.EndUser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.CommonFunctions;
import utils.ObjectHelper;

public class VSTS_11432_EUser extends BaseClass_EUser {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11432");
			BaseClass_EUser.bh.getAllSteps(11432);
			Thread.sleep(5000);
			ObjectHelper.driver.switchTo().defaultContent();
			CommonFunctions.waitForVisiblity(ObjectHelper.driver.findElement(By.id("ifInbox")), 20);
			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			WebElement element = ObjectHelper.driver.findElement(BC_Inbox.status);
			js.executeScript("arguments[0].scrollIntoView();", element);
			WebElement element2 = ObjectHelper.driver.findElement(BC_Inbox.alltonew);
			js.executeScript("arguments[0].click();", element2);
			Thread.sleep(5000);
			for (int i = 1; i <= 6; i++) {
				if (ObjectHelper.driver.findElement(By.xpath("//table[@id='ResultTable']/tbody/tr[" + i + "]/td[7]"))
						.getAttribute("outerHTML").contains("img")) {
					BaseClass_EUser.bh.addexpected(1);
				}
			}
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_EUser.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11432", BaseClass_EUser.AssignedTo,
				BaseClass_EUser.Iterationpath, BaseClass_EUser.CreatedBy, BaseClass_EUser.Areapath, 1);
	}

}
