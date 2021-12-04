import config.FootballApiConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJsonTests extends FootballApiConfig {

    @Test
    public void extractMapOfElementsWithFind(){

        Response response = get("http://api.football-data.org/v2/competitions/2021/teams");

        Map<String, ?> allTeamDataForSingleTeam = response.path("teams.find{ it.name == 'Manchester United FC'}");

        System.out.println("Map of Team Data = " + allTeamDataForSingleTeam);
    }

    @Test
    public void extractSingleValueWithFind(){
        Response response = get("teams/57");
        String certainPlayer = response.path("squad.find {it.nationality == 'Gabon'}.name");
        System.out.println("Name of player = " + certainPlayer);
    }

    @Test
    public void extractListOfValuesWithFindAll(){
        Response response = get("teams/58");
        List<String> playerNames = response.path("squad.findAll {it.nationality == 'England'}.name");
        System.out.println("List of players" + playerNames);
    }
}
