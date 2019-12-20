package launchpadBC.testcases.Regression;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import utils.ObjectHelper;
import utils.Screenshots;

public class VSTS_PrintAll_11434 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	// Please make sure Print prefrences -Pdf Printing check box is checked (For
	// running in production)

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11434");
			BaseClass_Regression.bh.getAllSteps(11434);
			System.out.println("VSTS_PrintAll_11434 started");
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(3000);

			Actions builder = new Actions(ObjectHelper.driver);

			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.status)).perform();
			Thread.sleep(1000);
			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.alltonew)).click().perform();
			Thread.sleep(2000);

			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.print)).perform();
			Thread.sleep(1000);
			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.printAll)).click().perform();
			Thread.sleep(3000);

			// CommonFunctions.waitForVisiblity(ObjectHelper.driver.findElement(By.id("ifInbox")),
			// 20);
			// ObjectHelper.driver.switchTo().frame("ifInbox");

			System.out.println(ObjectHelper.driver.findElements(BC_Inbox.resultsBAFilter).size());

			int size = ObjectHelper.driver.findElements(BC_Inbox.resultsBAFilter).size() / 2;
			for (int i = 1; i <= size; i++) {
				if (ObjectHelper.driver
						.findElement(By.xpath("//table[@id='ResultTable']/tbody/tr[" + i + "]/td[7]/img"))
						.isDisplayed() == false) {
					BaseClass_Regression.bh.addexpected(3);
				}
			}
			Thread.sleep(5000);
			ObjectHelper.driver.switchTo().defaultContent();

		} catch (Exception e) {
			exceptionError = true;
			e.getStackTrace();
			ObjectHelper.test.fail(e.getMessage());
			Screenshots.takeScreenshot("ID_11434_PrintAllViaPDF");
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11434",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
