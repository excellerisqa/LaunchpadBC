package launchpadBC.testcases.EndUser;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_HL7Upload;
import launchpadBC.objects.BC_MainPage;
import utils.ObjectHelper;

public class VSTS_11426_EUser extends BaseClass_EUser {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void hl7Upload(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11426");
			BaseClass_EUser.bh.getAllSteps(11426);
			// click hl7upload link
			ObjectHelper.driver.switchTo().defaultContent();
			JavascriptExecutor js = (JavascriptExecutor) ObjectHelper.driver;
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_MainPage.hl7uploadlink));
			Thread.sleep(2000);

			// Switch frame
			ObjectHelper.driver.switchTo().frame("ifSPAdmin");
			Thread.sleep(2000);

			// pass path of file to upload
			ObjectHelper.driver.findElement(BC_HL7Upload.chooseFile).clear();
			String filename = new File(".", "\\TestData\\16-EXC999167017_bc enduser test msg.txt").getAbsolutePath();
			System.out.println("File Path : " + filename);
			ObjectHelper.driver.findElement(BC_HL7Upload.chooseFile).sendKeys(filename);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_HL7Upload.uploadFileBtn));
			Thread.sleep(15000);
			if (!(ObjectHelper.driver.findElement(BC_HL7Upload.uploadResults).getText()
					.contains("Successfully stored"))) {
				BaseClass_EUser.bh.addexpected(1);
			}
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass_EUser.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11426",
				BaseClass_EUser.AssignedTo, BaseClass_EUser.Iterationpath, BaseClass_EUser.CreatedBy,
				BaseClass_EUser.Areapath, 1);
	}
}
