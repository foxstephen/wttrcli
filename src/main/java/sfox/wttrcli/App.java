package sfox.wttrcli;

import org.apache.commons.cli.*;

import java.util.*;

public class App {
    public static void main( String[] args ) throws ParseException {
        final Options options = new Options();
        options.addOption("l", "location", true, "The location to fetch the weather report.");
        options.addOption("o", "options", true, "Comma separated options for the weather report.");

        CommandLine commandLine = new DefaultParser().parse(options, args);
        String location = commandLine.getOptionValue("l");
        List<String> cliParserOptions = Arrays.asList(commandLine.getOptionValue("o").split(","));
        Set<String> parserOptions = new HashSet<String>();
        for (String parseOption: cliParserOptions) {
            parserOptions.add(parseOption);
        }

        for (String report: new Wttr().getWeatherReport(location, parserOptions)) {
            System.out.println(report);
        }
    }
}
