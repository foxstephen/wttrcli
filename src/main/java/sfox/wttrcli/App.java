package sfox.wttrcli;

import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main( String[] args ) {
        String location = "Dublin";
        Set<String> options = new HashSet<String>() {
            {
                add("live");
                add("aftertomorrow");
            }
        };

        for (String report: new Wttr().getWeatherReport(location, options)) {
            System.out.println(report);
        }
    }
}
