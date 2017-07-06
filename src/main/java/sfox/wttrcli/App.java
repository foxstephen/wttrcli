package sfox.wttrcli;

import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class App {
    private static String USAGE_MESSAGE = "--Usage--\n" +
            "\t[l, location]\t: The location to fetch the weather report.\n" +
            "\t[o, option]\t: An option for parsing the report, can be any of the following:\n\t\t\t\t" +
            "all, tomorrow, live, aftertomorrow";

    public static void main( String[] args ) throws ParseException {
        final Options options = new Options();
        options.addOption("l", "location", true, "The location to fetch the weather report.");
        options.addOption("o", "option", true, "An option for parsing the report.");
        options.addOption("h", "help", false, "Lists options.");

        CommandLine commandLine = new DefaultParser().parse(options, args);
        if (commandLine.getOptions().length == 0) {
            return;
        }
        else if (commandLine.hasOption("h")) {
            System.out.println(USAGE_MESSAGE);
            return;
        }
        String location = commandLine.getOptionValue("l") != null ? commandLine.getOptionValue("l") : "Dublin";
        String[] oOptions =
                commandLine.getOptionValues("o").length > 0 ? commandLine.getOptionValues("o") : new String[]{ "all" };
        List<String> oOptionsList = Arrays.asList(oOptions);
        // Reverse order, ensures printed as queried
        Collections.reverse(oOptionsList);
        HashSet<String> parserOptions = new HashSet<String>(oOptionsList);

        for (String report: new Wttr().getWeatherReport(location, parserOptions)) {
            System.out.println(report);
        }
    }
}
