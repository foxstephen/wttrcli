package sfox.wttrcli;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;
import java.util.Set;

public class Wttr {

    private final String WTTR_URL = "http://wttr.in";

    /**
     * Retrieves a weather from from http://wttr.in
     * for a given location.
     * @param location The location for the weather report.
     * @return List of Strings containg the parsed report
     *  according to the options given.
     */
    public List<String> getWeatherReport(String location, Set<String> options) {
        String url = this.WTTR_URL + "/" + location;
        return new Parser()
                .report(httpRequest(url))
                .options(options)
                .parse();
    }

    private String httpRequest(String url) {
        try {
            HttpResponse<String> response =
                    Unirest.get(url)
                            .header("User-Agent", "curl/7.51.0")
                            .asString();
            return response.getBody();
        } catch (UnirestException e) {
            System.out.print(e.getMessage());
            return "";
        }
    }

}
