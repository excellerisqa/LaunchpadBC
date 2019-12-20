package launchpadBC.testcases.Regression;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_MainPage;
import launchpadBC.objects.BC_MyehealthMapping;
import utils.ObjectHelper;

public class VSTS_Admin_MyEHealthPatientMapping_11425 extends BaseClass_Regression {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void testmodals(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11425");
			BaseClass_Regression.bh.getAllSteps(11425);
			System.out.println("VSTS_11425_MyEHealth Patient Mapping started");
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.myeHealthPatMapLink));
			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			ObjectHelper.driver.findElement(BC_MyehealthMapping.loginID)
					.sendKeys(BaseClass_Regression.xlhandler.getDatabyCell(2, 24, 1));
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_MyehealthMapping.clientListBtn));
			Thread.sleep(4000);
			BC_MyehealthMapping.selectclient(BaseClass_Regression.xlhandler.getDatabyCell(2, 24, 2));
			Thread.sleep(5000);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MyehealthMapping.searchBtn));
			Thread.sleep(8000);
			String fullName = ObjectHelper.driver.findElement(BC_MyehealthMapping.lastName).getText() + ", "
					+ ObjectHelper.driver.findElement(BC_MyehealthMapping.firstName).getText();

			if (!(StringUtils.substringBefore(BaseClass_Regression.xlhandler.getDatabyCell(2, 24, 2), " (")
					.replaceAll(" ", "")).equalsIgnoreCase(fullName.replaceAll(" ", ""))) {
				BaseClass_Regression.bh.addexpected(2);
			}
			Thread.sleep(9000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11425",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}
}
