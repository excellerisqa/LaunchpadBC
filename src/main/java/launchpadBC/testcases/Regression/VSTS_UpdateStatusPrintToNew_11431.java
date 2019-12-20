package launchpadBC.testcases.Regression;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.CommonFunctions;
import utils.ObjectHelper;
import utils.Screenshots;

public class VSTS_UpdateStatusPrintToNew_11431 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	/**
	 * @param bugflag
	 * @param exceptionflag
	 * @throws Exception
	 */
	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void validateAuditTrail(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11431");
			BaseClass_Regression.bh.getAllSteps(11431);
			System.out.println("VSTS_UpdateStatusPrintToNew_11431 started");
			Thread.sleep(3000);
			ObjectHelper.driver.switchTo().defaultContent();
			CommonFunctions.waitForVisiblity(ObjectHelper.driver.findElement(By.id("ifInbox")), 20);
			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(3000);

			Actions builder = new Actions(ObjectHelper.driver);

			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.status)).perform();
			Thread.sleep(3000);
			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.alltonew)).click().perform();
			Thread.sleep(5000);

			ObjectHelper.driver.switchTo().defaultContent();
			CommonFunctions.waitForVisiblity(ObjectHelper.driver.findElement(By.id("ifInbox")), 20);
			ObjectHelper.driver.switchTo().frame("ifInbox");

			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.print)).perform();
			Thread.sleep(3000);
			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.printAll)).click().perform();
			Thread.sleep(5000);

			ObjectHelper.driver.switchTo().defaultContent();
			CommonFunctions.waitForVisiblity(ObjectHelper.driver.findElement(By.id("ifInbox")), 20);
			ObjectHelper.driver.switchTo().frame("ifInbox");

			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.status)).perform();
			Thread.sleep(3000);
			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.printedtonew)).click().perform();
			Thread.sleep(5000);

			System.out.println(ObjectHelper.driver.findElements(BC_Inbox.resultsBAFilter).size());

			int size = ObjectHelper.driver.findElements(BC_Inbox.resultsBAFilter).size() / 2;

			for (int i = 1; i <= size; i++) {
				if (ObjectHelper.driver.findElement(By.xpath("//table[@id='ResultTable']/tbody/tr[" + i + "]/td[7]"))
						.getAttribute("outerHTML").contains("img")) {
					BaseClass_Regression.bh.addexpected(3);
				}
			}
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(5000);
		} catch (Exception e) {
			exceptionError = true;
			e.getStackTrace();
			ObjectHelper.test.fail(e.getMessage());
			Screenshots.takeScreenshot("ID_11431_StatusPrintToNew");

		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11431",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
