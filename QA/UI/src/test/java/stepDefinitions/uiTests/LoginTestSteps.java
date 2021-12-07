package stepDefinitions.uiTests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import stepDefinitions.ConfProperties;
import stepDefinitions.ContextSteps;
import stepDefinitions.constants.AdminPanel;
import stepDefinitions.modalPages.LoginPage;
import stepDefinitions.modalPages.ProfilePage;

import static org.junit.Assert.assertEquals;

public class LoginTestSteps {
    public static LoginPage loginPage;
    public static WebDriver driver;
    public static ProfilePage profilePage;
    private ContextSteps contextSteps;

    // PicoContainer injects class ContextSteps
    public LoginTestSteps(ContextSteps contextSteps) {
        this.contextSteps = contextSteps;
    }

    @Given("^login page is open")
    public void fooStep() throws Throwable {
        // Access WebDriver instance
        driver = contextSteps.getDriver();
    }

    @When("^user loged in")
    public void loginTest() {
        loginPage = new LoginPage(driver);
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        loginPage.clickLoginBtn();
    }

    @Then("^user name is expected")
    public void userNameVerification() {
        profilePage = new ProfilePage(driver);
        assertEquals("User is not expected", ConfProperties.getProperty("login"), profilePage.getUserName());
    }

    @Then("^admin panel is present")
    public void adminPanelVerification() {
        AdminPanel.getTitles().forEach(title ->
                Assert.assertEquals("In the admin panel " + title + " is not present", profilePage.getAdminPanelElement(title),
                        title));
    }

    @Then("^user loges out")
    public void clickLogoutBtn() {
        profilePage.clickLogoutBtn();
    }
}