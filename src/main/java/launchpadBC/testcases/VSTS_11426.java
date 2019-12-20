package launchpadBC.testcases;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import launchpadBC.objects.BC_HL7Upload;
import launchpadBC.objects.BC_MainPage;
import utils.ObjectHelper;

public class VSTS_11426 extends BaseClass {
	boolean step1 = true, exceptionError = false;

	@Test
	@Parameters({ "bugflag", "exceptionflag" })
	public void hl7Upload(boolean bugflag, boolean exceptionflag) throws Exception {
		try {
			ObjectHelper.test = ObjectHelper.reports.createTest("VSTS_11426");
			BaseClass.bh.getAllSteps(11426);
			System.out.println("VSTS_11426_Upload HL7 started");
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
			String filename = new File(".", "\\TestData\\16-EX999167017_bc prod test msg.txt").getAbsolutePath();
			System.out.println("File Path : " + filename);
			ObjectHelper.driver.findElement(BC_HL7Upload.chooseFile).sendKeys(filename);
			js.executeScript("arguments[0].click();", ObjectHelper.driver.findElement(BC_HL7Upload.uploadFileBtn));
			Thread.sleep(15000);
			if (!(ObjectHelper.driver.findElement(BC_HL7Upload.uploadResults).getText()
					.contains("Successfully stored"))) {
				BaseClass.bh.addexpected(1);
			}
		} catch (Exception e) {
			exceptionError = true;
			// BaseClass.bh.addexpected("Error Message " + e.getMessage());
			ObjectHelper.test.fail(e);
		}
		BaseClass.bh.createbug(exceptionError, bugflag, exceptionflag, "VSTS_ID_11426", BaseClass.AssignedTo,
				BaseClass.Iterationpath, BaseClass.CreatedBy, BaseClass.Areapath, 1);
	}
}
