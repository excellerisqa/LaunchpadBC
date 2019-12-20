package launchpadBC.testcases.Regression;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_MainPage;
import launchpadBC.objects.BC_PhysicianEntry;
import launchpadBC.objects.BC_PhysicianMaintenance;
import utils.CommonFunctions;
import utils.ObjectHelper;
import utils.XLHandler;

@Test
public class VSTS_CreatePhysician_12296 extends BaseClass_Regression {

	private static final Boolean False = null;

	@Parameters({ "bugflag", "exceptionflag" })
	public void CreatePhysician(boolean bugflag, boolean exceptionflag) throws Exception {
		boolean exceptionError = false;

		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_12296");
			BaseClass_Regression.bh.getAllSteps(12296);
			System.out.println("VSTS_CreatePhysician_12296 started");
			CommonFunctions.waitForVisiblity(BC_MainPage.physicianEntryLink, 20);

			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.physicianEntryLink));

			Thread.sleep(2000);
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");

			Thread.sleep(2000);

			CommonFunctions.clickUsingJavaExecutor(ObjectHelper.driver.findElement(BC_PhysicianEntry.newbtn));
			CommonFunctions.waitForVisiblity(BC_PhysicianMaintenance.phyAttributeLink, 20);

			String[] rowProviderData = XLHandler.readexcel(2, 5, testDataFileName); // get testdata from sheet
			BC_PhysicianMaintenance.FillProviderInformation(rowProviderData);

			CommonFunctions.clickUsingJavaExecutor(BC_PhysicianMaintenance.phyIdentifierLink);
			Thread.sleep(2000);
			BC_PhysicianMaintenance.FillProviderIdentifiers();

			// BC_PhysicianMaintenance.FillAllProviderIdentifiers();

			// Click Add button to add profile
			// CommonFunctions.clickUsingJavaExecutor(BC_PhysicianMaintenance.phyAttributeAddbtn);
			// Thread.sleep(2000);

			// Click Save button to save user
			CommonFunctions.clickUsingJavaExecutor(BC_PhysicianMaintenance.phySaveButton);

			String saveConfirmedinfo = ObjectHelper.driver.switchTo().alert().getText();
			Boolean isFound = False;
			isFound = saveConfirmedinfo.contains("has been saved successfully"); // true

			if (isFound) {
				ObjectHelper.driver.switchTo().alert().accept();

			} else {
				BaseClass_Regression.bh.addexpected(4);
			}
			Thread.sleep(5000);
			ObjectHelper.driver.switchTo().defaultContent();
		} catch (Exception e) {
			ObjectHelper.test.fail(e);
		}
		BaseClass_Regression.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_12296",
				BaseClass_Regression.AssignedTo, BaseClass_Regression.Iterationpath, BaseClass_Regression.CreatedBy,
				BaseClass_Regression.Areapath, 1);
	}

}