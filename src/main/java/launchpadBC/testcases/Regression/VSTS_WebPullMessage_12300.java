package launchpadBC.testcases.Regression;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_Inbox;
import launchpadBC.objects.BC_LoginPage;
import launchpadBC.objects.BC_MainPage;
import utils.CommonFunctions;
import utils.ObjectHelper;
import utils.Screenshots;

public class VSTS_WebPullMessage_12300 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void filterByTest(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_12300");
			// BaseClass.bh.getAllSteps(11255);
			System.out.println("VSTS_12300_PullMessage started");
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().defaultContent();
			CommonFunctions.waitForVisiblity(ObjectHelper.driver.findElement(By.id("ifInbox")), 20);
			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(2000);

			Actions builder = new Actions(ObjectHelper.driver);
			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.status)).perform();
			Thread.sleep(2000);
			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.alltonew)).click().perform();

			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().defaultContent();
			BC_MainPage.logout();

			Thread.sleep(2000);
			ObjectHelper.driver.navigate().to(BaseClass_Regression.xlhandler.getDatabyCell(2, 8, 1));
			if (!ObjectHelper.driver.getPageSource().contains("AccessGranted")) {
				BaseClass_Regression.bh.addexpected(4);
			}

			Thread.sleep(2000);
			ObjectHelper.driver.navigate().to(BaseClass_Regression.xlhandler.getDatabyCell(2, 9, 1));
			if (!ObjectHelper.driver.getPageSource().contains("Message MsgID")) {
				BaseClass_Regression.bh.addexpected(5);
			}

			Thread.sleep(2000);
			ObjectHelper.driver.navigate().to(BaseClass_Regression.xlhandler.getDatabyCell(2, 10, 1));
			if (!ObjectHelper.driver.getPageSource().contains("HL7Messages ReturnCode")) {
				BaseClass_Regression.bh.addexpected(7);
			}

			Thread.sleep(2000);
			ObjectHelper.driver.navigate().to(BaseClass_Regression.xlhandler.getDatabyCell(2, 11, 1));
			if (!ObjectHelper.driver.getPageSource().contains("The XML page cannot be displayed ")) {
				BaseClass_Regression.bh.addexpected(8);
			}

			Thread.sleep(2000);
			ObjectHelper.driver.navigate().to(ObjectHelper.enviURL);
			Thread.sleep(5000);
			BC_LoginPage.loginpage(BaseClass_Regression.xlhandler.getDatabyCell(0, 2, 1),
					BaseClass_Regression.xlhandler.getDatabyCell(0, 2, 2));

			Thread.sleep(10000);

			ObjectHelper.driver.switchTo().defaultContent();
			CommonFunctions.waitForVisiblity(ObjectHelper.driver.findElement(By.id("ifInbox")), 20);
			ObjectHelper.driver.switchTo().frame("ifInbox");
			Thread.sleep(2000);

			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.status)).perform();
			Thread.sleep(2000);
			builder.moveToElement(ObjectHelper.driver.findElement(BC_Inbox.alltonew)).click().perform();
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().defaultContent();

		} catch (

		Exception e) {
			exceptionError = true;
			e.getStackTrace();
			ObjectHelper.test.fail(e.getMessage());
			Screenshots.takeScreenshot("ID_12300_WebPullMessage");

		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_12300",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}

}
