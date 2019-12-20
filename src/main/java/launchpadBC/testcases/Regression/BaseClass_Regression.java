package launchpadBC.testcases.Regression;

import java.time.LocalDateTime;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import launchpadBC.objects.BC_LoginPage;
import launchpadBC.objects.BC_MainPage;
import launchpadBC.objects.BC_PhysicianMaintenance;
import launchpadBC.objects.BC_UserEntryCreation;
import utils.BugsHandler;
import utils.CreateBugTFS;
import utils.ObjectHelper;
import utils.Screenshots;
import utils.WebHandler;
import utils.XLHandler;
import vsts.connect.TFSAccess;

public class BaseClass_Regression {
	public static TFSAccess tfa;
	public static CreateBugTFS cbtfs;
	public static BugsHandler bh;
	// public static XLHandler xlhandlerData;

	public static XLHandler xlhandler;

	public static Screenshots screenshots;
	public static String testDataFileName;
	public static String Areapath = "Enterprise\\QA_Regression_ON";
	public static String Iterationpath = "Enterprise\\QA_Regression_ON";
	public static String AssignedTo = "";
	public static String CreatedBy = "Anubhav/Shravan/Sidra/Sarah";
	public static String parentwindow = null;
	// add by sarah
	public static BC_UserEntryCreation userEntryCreation;
	public static BC_PhysicianMaintenance phyEntryCreation;

	// end

	@BeforeSuite
	public void openbrowser(ITestContext con) throws Exception {
		testDataFileName = con.getCurrentXmlTest().getParameter("testDataFile");
		ObjectHelper.enviURL = con.getCurrentXmlTest().getParameter("environmentURL");
		ObjectHelper.browsertype = con.getCurrentXmlTest().getParameter("browser");
		// ObjectHelper.testtitle = "Launchpad_BC Sanity Test Report";
		ObjectHelper.testtitle = "Launchpad_BC Regression Test Report on " + ObjectHelper.enviURL;
		String dirName = con.getName() + LocalDateTime.now().toString().replace(":", ".");
		WebHandler.setupOutputFolder(dirName);
		WebHandler.openBrowser();
		WebHandler.createextentreport("Launchpad_BC", "Launchpad BC Sanity Prod Report", ObjectHelper.enviURL);
		tfa = new TFSAccess("Enterprise");
		cbtfs = new CreateBugTFS();
		bh = new BugsHandler();
		////////////// data file change
		// xlhandlerData = new XLHandler("\\TestData\\" + testDataFileName);
		xlhandler = new XLHandler("\\TestData\\" + testDataFileName);
		//////////////////
		ObjectHelper.driver.navigate().to(ObjectHelper.enviURL);
		parentwindow = ObjectHelper.driver.getWindowHandle();

		userEntryCreation = new BC_UserEntryCreation(ObjectHelper.driver);
		phyEntryCreation = new BC_PhysicianMaintenance(ObjectHelper.driver);
	}

	@BeforeTest
	public static void beforeTest(ITestContext con) throws Exception {
		Thread.sleep(2000);
		if (con.getCurrentXmlTest().getParameter("userType").equals("admin")) {
			BC_LoginPage.loginpage(BaseClass_Regression.xlhandler.getDatabyCell(0, 1, 1),
					BaseClass_Regression.xlhandler.getDatabyCell(0, 1, 2));
			System.out.println("Logged in as Global Admin");
		}
		if (con.getCurrentXmlTest().getParameter("userType").equals("MOA")) {
			BC_LoginPage.loginpage(BaseClass_Regression.xlhandler.getDatabyCell(0, 2, 1),
					BaseClass_Regression.xlhandler.getDatabyCell(0, 2, 2));
			System.out.println("Logged in as MOA");
		}
	}

	@BeforeMethod
	/*
	 * public void beforeMethod(ITestContext con) throws Exception {
	 * Thread.sleep(1000); if
	 * (con.getCurrentXmlTest().getParameter("userType").equals("admin")) {
	 * BC_LoginPage.loginpage(BaseClass_Regression.xlhandler.getDatabyCell(0, 1, 1),
	 * BaseClass_Regression.xlhandler.getDatabyCell(0, 1, 2));
	 * System.out.println("Logged in as Global Admin"); } if
	 * (con.getCurrentXmlTest().getParameter("userType").equals("MOA")) {
	 * BC_LoginPage.loginpage(BaseClass_Regression.xlhandler.getDatabyCell(0, 2, 1),
	 * BaseClass_Regression.xlhandler.getDatabyCell(0, 2, 2));
	 * System.out.println("Logged in as MOA"); }
	 * 
	 * // xlhandler = new XLHandler("\\TestData\\" + testDataFileName); }
	 */

	@AfterMethod
	public static void setTestResult(ITestResult result) throws InterruptedException {
		Thread.sleep(1000);
		if (result.getStatus() == ITestResult.FAILURE) {
			ObjectHelper.test.fail(result.getTestName());
			ObjectHelper.test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			ObjectHelper.test.pass(result.getTestName());
		} else if (result.getStatus() == ITestResult.SKIP) {
			ObjectHelper.test.skip("Test Case :" + result.getTestName() + "has been skipped");
		}
		Thread.sleep(1000);
		// login and logout each test
		// BC_MainPage.logout();
		// System.out.println("Logged out successfully");
		// WebHandler.closebrowser();
	}

	@AfterTest
	public static void afterTest() {
		// login and logout once
		BC_MainPage.logout();
		System.out.println("Logged out successfully");
		WebHandler.closebrowser();
	}

	@AfterSuite
	public static void afterSuite() {
		WebHandler.closebrowser();
	}

}
