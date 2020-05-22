package API;

import model.GasStation;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class ApiData {
	public static ArrayList<GasStation> getJSON(float lat, float lon, float rad) {
		URL url;
		HttpURLConnection request;
		try {
			url = new URL(buildRequestString(lat, lon, rad));
			request = (HttpURLConnection) url.openConnection();
			request.setDoOutput(true);
			request.setRequestMethod("GET");
			request.connect();

			JsonElement element = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));
			JsonObject obj = element.getAsJsonObject();
			// comment trial
			System.out.println("Checking JSON status");
			if (obj.get("status").getAsString().equals("ok")) {
				System.out.println("Status: OK");
				String jsonString = obj.toString().substring(obj.toString().indexOf("["),
						(obj.toString().indexOf("]") + 1));

				System.out.println("JSON file: " + jsonString);
				System.out.println("Trying to parse the JSON file...");

				Type listType = new TypeToken<ArrayList<GasStation>>() {
				}.getType();
				ArrayList<GasStation> gsList = new Gson().fromJson(jsonString, listType);

				return gsList;
			}
			System.out.println("Status: NOT OK");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("There was an error parsing the JSON file.");
		}
		return new ArrayList<GasStation>();
	}

	public static ArrayList<GasStation> getJSON(String[] favList) {
		ArrayList<GasStation> gsList = new ArrayList<GasStation>();
		for (String s : favList) {
			URL url;
			HttpURLConnection request;
			try {
				url = new URL(buildRequestString(s));
				request = (HttpURLConnection) url.openConnection();
				request.setDoOutput(true);
				request.setRequestMethod("GET");
				request.connect();

				JsonElement element = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));
				JsonObject obj = element.getAsJsonObject();
				// comment trial
				System.out.println("Checking JSON status");
				if (obj.get("status").getAsString().equals("ok")) {
					System.out.println("Status: OK");
					int startindex = obj.toString().indexOf("station")+9;
					String jsonstring = obj.toString().substring(startindex, obj.toString().length()-1);
					GasStation gs = new Gson().fromJson(jsonstring, GasStation.class);
					gsList.add(gs);	
				}
				System.out.println("Status: NOT OK");

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("There was an error parsing the JSON file.");
			}

		}
        return gsList;
	}

	// returns a https request string with the specified latitude, longitude and
	// radius
	public static String buildRequestString(float lat, float lon, float radius) {
		return "https://creativecommons.tankerkoenig.de/json/list.php?lat=" + lat + "&lng=" + lon + "&rad=" + radius
				+ "&sort=dist&type=all&apikey=" + new Api_Key().getApiKey();
	}

	public static String buildRequestString(String id) {
		return "https://creativecommons.tankerkoenig.de/json/detail.php?id=" + id + "&apikey="
				+ new Api_Key().getApiKey();
	}
}
