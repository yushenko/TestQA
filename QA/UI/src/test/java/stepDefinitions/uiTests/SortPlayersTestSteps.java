package stepDefinitions.uiTests;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import stepDefinitions.ContextSteps;
import stepDefinitions.modalPages.PlayersListPage;
import stepDefinitions.modalPages.ProfilePage;

import java.util.Collections;

import static org.junit.Assert.assertTrue;

public class SortPlayersTestSteps {
    public static PlayersListPage playersListPage;
    public static ProfilePage profilePage;
    public static WebDriver driver;
    private final ContextSteps contextSteps;

    // PicoContainer injects class ContextSteps
    public SortPlayersTestSteps(ContextSteps contextSteps) {
        this.contextSteps = contextSteps;
        driver = contextSteps.getDriver();
        profilePage = new ProfilePage(driver);
        playersListPage = new PlayersListPage(driver);
    }

    @And("^user clicks to sort players by \"([^\"]*)\"$")
    public void sortPlayers(String username) {
        playersListPage.clickToSortPlayers(username);
    }

    @Then("^players are sorted correctly")
    public void IsPlayersSortCorrect() {
        playersListPage.collectAllPlayers().forEach(player ->
                assertTrue("Players are not sorted correctly",
                        playersListPage.verifySortList(Collections.singletonList(player))));
    }
}
