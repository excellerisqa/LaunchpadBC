package launchpadBC.testcases.EndUser;

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
import utils.BugsHandler;
import utils.CreateBugTFS;
import utils.ObjectHelper;
import utils.Screenshots;
import utils.WebHandler;
import utils.XLHandler;
import vsts.connect.TFSAccess;

public class BaseClass_EUser {
	public static TFSAccess tfa;
	public static CreateBugTFS cbtfs;
	public static BugsHandler bh;
	public static XLHandler xlhandler;
	public static Screenshots screenshots;
	public static String testDataFileName;
	public static String Areapath = "Enterprise\\QA_Regression_ON";
	public static String Iterationpath = "Enterprise\\QA_Regression_ON";
	public static String AssignedTo = "";
	public static String CreatedBy = "Anubhav/Shravan/Sidra";
	public static String parentwindow = null;

	@BeforeSuite
	public void openbrowser(ITestContext con) throws Exception {
		testDataFileName = con.getCurrentXmlTest().getParameter("testDataFile");
		ObjectHelper.enviURL = con.getCurrentXmlTest().getParameter("environmentURL");
		ObjectHelper.browsertype = con.getCurrentXmlTest().getParameter("browser");
		ObjectHelper.testtitle = "Launchpad_BC Sanity Test Report";
		String dirName = con.getName() + LocalDateTime.now().toString().replace(":", ".");
		WebHandler.setupOutputFolder(dirName);
		WebHandler.openBrowser();
		WebHandler.createextentreport("Launchpad_BC", "Launchpad BC Smoke EndUser Report", ObjectHelper.enviURL);
		tfa = new TFSAccess("Enterprise");
		cbtfs = new CreateBugTFS();
		bh = new BugsHandler();
		xlhandler = new XLHandler("\\TestData\\" + testDataFileName);
		ObjectHelper.driver.navigate().to(ObjectHelper.enviURL);
		parentwindow = ObjectHelper.driver.getWindowHandle();

	}

	@BeforeTest
	public static void beforeTest(ITestContext con) throws Exception {
		Thread.sleep(5000);
		if (con.getCurrentXmlTest().getParameter("userType").equals("admin")) {
			BC_LoginPage.loginpage(BaseClass_EUser.xlhandler.getDatabyCell(0, 1, 1),
					BaseClass_EUser.xlhandler.getDatabyCell(0, 1, 2));
			System.out.println("Logged in as Global Admin");
		}
		if (con.getCurrentXmlTest().getParameter("userType").equals("MOA")) {
			BC_LoginPage.loginpage(BaseClass_EUser.xlhandler.getDatabyCell(0, 2, 1),
					BaseClass_EUser.xlhandler.getDatabyCell(0, 2, 2));
			System.out.println("Logged in as MOA");
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		xlhandler = new XLHandler("\\TestData\\" + testDataFileName);
	}

	@AfterMethod
	public static void setTestResult(ITestResult result) throws InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			ObjectHelper.test.fail(result.getTestName());
			ObjectHelper.test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			ObjectHelper.test.pass(result.getTestName());
		} else if (result.getStatus() == ITestResult.SKIP) {
			ObjectHelper.test.skip("Test Case :" + result.getTestName() + "has been skipped");
		}
		Thread.sleep(1000);
	}

	@AfterTest
	public static void afterTest() {
		BC_MainPage.logout();
		System.out.println("Logged out successfully");
	}

	@AfterSuite
	public static void afterSuite() {
		WebHandler.closebrowser();
	}

}
