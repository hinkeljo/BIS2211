package teamB.BIS2211.TankApp.WebController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import teamB.BIS2211.TankApp.Model.LeaderboardData;
import teamB.BIS2211.TankApp.Model.LeaderboardDataService;

@Controller
public class UserDataController 
{

    @Autowired
    LeaderboardDataService leaderboardDataService;

    @GetMapping("/userData")
    public String leaderboard(@CookieValue(value = "username", required=false, defaultValue = "") String username, final Model model)
    {
        model.addAttribute("username", username);
        ArrayList<LeaderboardData> lbData = leaderboardDataService.getDataByUser(username); 
        model.addAttribute("lbData", lbData);
        return "userData";
    }
}