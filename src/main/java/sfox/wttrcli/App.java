package sfox.wttrcli;

import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.HashSet;

public class App {
    public static void main( String[] args ) throws ParseException {
        final Options options = new Options();
        options.addOption("l", "location", true, "The location to fetch the weather report.");
        options.addOption("o", "options", true, "Options for the weather report.");

        CommandLine commandLine = new DefaultParser().parse(options, args);
        String location = commandLine.getOptionValue("l");
        HashSet<String> parserOptions = new HashSet<String>(Arrays.asList(commandLine.getOptionValues("o")));

        for (String report: new Wttr().getWeatherReport(location, parserOptions)) {
            System.out.println(report);
        }
    }
}
