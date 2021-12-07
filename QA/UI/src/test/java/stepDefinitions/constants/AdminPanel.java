package stepDefinitions.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum AdminPanel {
    TICKETS("Tickets"),
    PLAYERS("Players online / total"),
    WITHDRAWAL("Withdrawal requests"),
    ACTIVE_GAME("Active game sessions");

    private String title;

    public static List<String> getTitles() {
        List<String> titles = new ArrayList<>();
        Arrays.asList(values()).stream().forEach(s -> titles.add(s.getTitle()));
        return titles;
    }
}
