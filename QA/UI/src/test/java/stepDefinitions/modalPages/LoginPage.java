package stepDefinitions.modalPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private static final By LOGIN_FIELD = By.name("UserLogin[username]");
    private static final By PASSWORD_FIELD = By.name("UserLogin[password]");
    private static final By SIGN_IN_BTN = By.name("yt0");

    protected final WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void inputLogin(String login) {
        driver.findElement(LOGIN_FIELD).sendKeys(login);
    }

    public void inputPasswd(String passwd) {
        driver.findElement(PASSWORD_FIELD).sendKeys(passwd);
    }

    public void clickLoginBtn() {
        driver.findElement(SIGN_IN_BTN).click();
    }
}
