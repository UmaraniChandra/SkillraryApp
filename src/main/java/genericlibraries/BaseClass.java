package genericlibraries;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pompages.AddNewCategoryPage;
import pompages.AddNewCoursePage;
import pompages.AddNewUserPage;
import pompages.AdminHomePage;
import pompages.CategoryPage;
import pompages.CourseListPage;
import pompages.LoginPage;
import pompages.UsersPage;
import pompages.WelcomePage;

public class BaseClass {
	//@BeforeSuite
	//BeforeTest
	protected PropertiesUtility property;
	protected ExcelUtility excel;
	protected JavaUtility jutil;
	protected WebDriverUtility webUtil;
	protected WebDriver driver;
	
	protected WelcomePage welcome;
	protected LoginPage login;
	protected AdminHomePage home;
	protected UsersPage users;
	protected CourseListPage course;
	protected CategoryPage category;
	protected AddNewUserPage addUser;
	protected AddNewCoursePage addcourse;
	protected AddNewCategoryPage addCategory;

	
	public static WebDriver sdriver;
	public static JavaUtility sjutil;
	
	@BeforeClass
	public void classConfig() {
		property=new PropertiesUtility();
		excel=new ExcelUtility();
		jutil=new JavaUtility();
		webUtil=new WebDriverUtility();
		
		property.PropertiesInitialization(IconstantPath.PROPERTIES_PATH);
		driver=webUtil.launchBrowser(property.readFromProperties("browser"));
		
		sdriver=driver;
		sjutil=jutil;
		
	}
	@BeforeMethod
	public void methodConfig() {
		excel.excelInitialization(IconstantPath.EXCEL_PATH);
		
		welcome=new WelcomePage(driver);
		login= new LoginPage(driver);
		home=new AdminHomePage(driver);
		users=new UsersPage(driver);
		course=new CourseListPage(driver);
		category=new CategoryPage(driver);
		addUser=new AddNewUserPage(driver);
		addcourse=new AddNewCoursePage(driver);
		addCategory=new AddNewCategoryPage(driver);
		
		webUtil.navigateToApp(property.readFromProperties("url"));
		
		long time=Long.parseLong(property.readFromProperties("timeouts"));
		webUtil.waitTillElementFound(time);
		
		welcome.clickLoginButton();
		login.setEmail(property.readFromProperties("usernmae"));
		login.setPassword(property.readFromProperties("password"));
		login.clickLogin();
		
	}
	@AfterMethod
	public void methodTeardown() {
		excel.closeExcel();
		home.signOutofApp();
	}
	@AfterClass
	public void classTeardown() {
		webUtil.closeAllWindows();
	}
	//@AfterTest
	//@AfterSuite
	
	
	
	

}
