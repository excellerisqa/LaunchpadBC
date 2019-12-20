package launchpadBC.testcases.EndUser;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_MainPage;
import launchpadBC.objects.BC_StandardizedTestCatalogue;
import utils.ObjectHelper;

public class VSTS_11422_EUser extends BaseClass_EUser {

	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void validateLOINCvalue(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11422");
			BaseClass_EUser.bh.getAllSteps(11422);
			ObjectHelper.driver.switchTo().defaultContent();
			Thread.sleep(1000);
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.standTestCatlink));
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			Thread.sleep(1000);
			ObjectHelper.driver.findElement(BC_StandardizedTestCatalogue.testCodeTextBox)
					.sendKeys(BaseClass_EUser.xlhandler.getDatabyCell(1, 4, 1));
			ObjectHelper.driver.findElement(BC_StandardizedTestCatalogue.testNameTextBox)
					.sendKeys(BaseClass_EUser.xlhandler.getDatabyCell(1, 4, 2));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_StandardizedTestCatalogue.searchbtn));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_StandardizedTestCatalogue.bcotcRadioBtn));
			js.executeScript("arguments[0].click();",
					ObjectHelper.driver.findElement(BC_StandardizedTestCatalogue.viewlOINC));
			Thread.sleep(2000);
			String loinc = ObjectHelper.driver.findElement(BC_StandardizedTestCatalogue.loincValue)
					.getAttribute("value");
			if (!(loinc.replaceAll(" ", "")
					.equalsIgnoreCase(BaseClass_EUser.xlhandler.getDatabyCell(1, 4, 3).replaceAll(" ", "")))) {
				BaseClass_EUser.bh.addexpected(2);
			}
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_EUser.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11422",
				BaseClass_EUser.AssignedTo, BaseClass_EUser.Iterationpath, BaseClass_EUser.CreatedBy,
				BaseClass_EUser.Areapath, 1);
	}
}
