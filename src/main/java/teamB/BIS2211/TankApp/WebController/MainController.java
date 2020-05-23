package teamB.BIS2211.TankApp.WebController;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import teamB.BIS2211.TankApp.ApiData.ApiData;
import teamB.BIS2211.TankApp.Model.GasStation;

@Controller
public class MainController 
{
  @GetMapping("/main")
  public String main(
    @RequestParam(name="name", required=false, defaultValue="lizer") final String name, 
    @RequestParam(name="lat", required=false, defaultValue="") final String lat,
    @RequestParam(name="lon", required=false, defaultValue="") final String lon,
    @RequestParam(name="rad", required=false, defaultValue="") final String rad,
    @CookieValue(value = "cfavourites", defaultValue = "") String favs,
    final Model model) 
  {
    model.addAttribute("name", name);
    final ArrayList<GasStation> gsList = ApiData.getJSON(convertReqestparameter(lat), convertReqestparameter(lon), convertReqestparameter(rad));
    model.addAttribute("gsList", gsList);
    final String[] favourites = splitFavString(favs); 
    final ArrayList<GasStation> favList = ApiData.getJSON(favourites);
    model.addAttribute("favList", favList);
    model.addAttribute("favString", favs);
		return "main";
  }

  private static float convertReqestparameter(final String s)
  {
    if(s == null) return 0;
		try
		{
			return Float.parseFloat(s);
		}
		catch(final NumberFormatException ex)
		{
			return 0;
		}
  }

  private static String[] splitFavString(final String s)
  {
    return s.split("/"); 
  }
}