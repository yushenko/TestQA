package stepDefinitions.uiTests;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import stepDefinitions.ContextSteps;
import stepDefinitions.constants.AdminPanel;
import stepDefinitions.modalPages.PlayersListPage;
import stepDefinitions.modalPages.ProfilePage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stepDefinitions.uiTests.LoginTestSteps.profilePage;

public class PlayersListTestSteps {
    public static PlayersListPage playersListPage;
    public static WebDriver driver;
    private final ContextSteps contextSteps;

    // PicoContainer injects class ContextSteps
    public PlayersListTestSteps(ContextSteps contextSteps) {
        this.contextSteps = contextSteps;
        driver = contextSteps.getDriver();
    }

    @Then("^user clicks to 'Players' icon")
    public void openPlayersList() {
        profilePage = new ProfilePage(driver);
        profilePage.clickToPanelElement(String.valueOf(AdminPanel.PLAYERS.getTitle()));
    }

    @Then("^the tab name is \"([^\"]*)\"$")
    public void verifyPageName(String name) {
        playersListPage = new PlayersListPage(driver);
        assertEquals("The tab title is not equals ", name, playersListPage.getPlayersListTabTitle());
    }

    @And("^'create players' button is present")
    public void isCreatePlayersBtnPresent() {
        assertTrue("'Create Players' btn is present", playersListPage.isCreatePlayerBtnDisplayed());
    }

    @And("^players list is present")
    public void isPlayersListPresent() {
        assertTrue("Players list is present", playersListPage.isPlayerListDisplayed());
    }
}
