package launchpadBC.testcases.EndUser;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_MainPage;
import launchpadBC.objects.BC_TestCatalogueEntry;
import utils.ObjectHelper;

public class VSTS_11421_EUser extends BaseClass_EUser {

	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void verifyTestCatalougeSuccessMsg(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11421");
			BaseClass_EUser.bh.getAllSteps(11421);
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_MainPage.testcatalougeentrylink));
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_TestCatalogueEntry.testSecListGeneral));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_TestCatalogueEntry.testGroupBtn));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_TestCatalogueEntry.testGrpListGeneral));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_TestCatalogueEntry.testCodesBtn));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_TestCatalogueEntry.testCodesListGCM));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_TestCatalogueEntry.resultCodesBtn));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_TestCatalogueEntry.resultCodesList));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_TestCatalogueEntry.editBtn));
			Thread.sleep(8000);
			ObjectHelper.driver.switchTo().defaultContent();
			ObjectHelper.driver.switchTo().frame("ifWS");
			Thread.sleep(4000);
			String loincID = ObjectHelper.driver.findElement(BC_TestCatalogueEntry.loincID).getAttribute("value");
			if (!(loincID.replaceAll(" ", "")
					.equalsIgnoreCase(BaseClass_EUser.xlhandler.getDatabyCell(1, 5, 1).replaceAll(" ", "")))) {
				BaseClass_EUser.bh.addexpected(4);
			}
			Thread.sleep(3000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_TestCatalogueEntry.saveBtn));
			Thread.sleep(8000);
			ObjectHelper.driver.switchTo().alert().accept();
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}

		BaseClass_EUser.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11421",
				BaseClass_EUser.AssignedTo, BaseClass_EUser.Iterationpath, BaseClass_EUser.CreatedBy,
				BaseClass_EUser.Areapath, 1);

	}
}
