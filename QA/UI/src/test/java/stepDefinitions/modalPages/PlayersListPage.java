package stepDefinitions.modalPages;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class PlayersListPage {

    public static WebDriver driver;
    private static final By PLAYER_MANAGEMENT_TITLE = By.xpath("//i[contains(@class, 'fa-th')]//..");
    private static final By CREATE_PLAYER_BTN = By.xpath("//i[contains(@class, 'fa-plus')]//..");
    private static final By PLAYERS_LIST = By.xpath("//div[@id='payment-system-transaction-grid']//table");
    private static final String SORT_ELEMENTS = "//a[contains(@class, 'sort-link')][contains(text(), '%s')]";
    private static final String ELEMENTS_LIST = "//tr%s//a[contains(@href, '/user/player/details')]";

    public PlayersListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getPlayersListTabTitle() {
        return driver.findElement(PLAYER_MANAGEMENT_TITLE).getText();
    }

    public boolean isCreatePlayerBtnDisplayed() {
        return driver.findElement(CREATE_PLAYER_BTN).isDisplayed();
    }

    public boolean isPlayerListDisplayed() {
        return driver.findElement(PLAYERS_LIST).isDisplayed();
    }

    public void clickToSortPlayers(String columnName) {
        driver.findElement(By.xpath(String.format(SORT_ELEMENTS, columnName))).click();
    }

    public List<String> collectAllPlayers() {
        List<String> values = new ArrayList<>();
        int countOfRows = getCountOfPlayersOnSearchPage() / 2;

        for (int x = 1; x <= countOfRows; x++) {
            By row = By.xpath(String.format(ELEMENTS_LIST, "[" + x + "]"));
            String cellValue = driver.findElement(row).getText();
            values.add(cellValue);
        }
        return values;
    }

    public int getCountOfPlayersOnSearchPage() {
        return driver.findElements(By.xpath(String.format(ELEMENTS_LIST, ""))).size();
    }

    public boolean verifySortList(List<String> values) {
        return Ordering.natural().isOrdered(values);
    }
}
