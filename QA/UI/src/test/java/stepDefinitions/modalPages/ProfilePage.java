package stepDefinitions.modalPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    public static WebDriver driver;
    private static final By USER_NAME = By.xpath("//i[contains(@class, 'fa-user')]/../span");
    private static final By LOGOUT_BTN = By.xpath("//i[contains(@class, 'fa-sign-out')]");
    private static final String ADMIN_PANEL = "//div[contains(@class, 'panel')]//p[contains(text(), '%s')]";

    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getUserName() {
        return driver.findElement(USER_NAME).getText();
    }

    public String getAdminPanelElement(String sectionName) {
        return driver.findElement(By.xpath(String.format(ADMIN_PANEL, sectionName))).getText();
    }

    public void clickLogoutBtn() {
        driver.findElement(USER_NAME).click();
        driver.findElement(LOGOUT_BTN).click();
    }

    public void clickToPanelElement(String sectionName) {
        driver.findElement(By.xpath(String.format(ADMIN_PANEL, sectionName))).click();
    }
}
