package sfox.wttrcli;

import java.util.*;

public class Parser {
    private String report;
    private Set<String> options;
    private final String OPTION_LIVE = "live";
    private final String OPTION_ALL = "all";
    private final String OPTION_TODAY = "today";
    private final String OPTION_TOMORROW = "tomorrow";
    private final String OPTION_AFTER_TOMORROW = "aftertomorrow";

    private final Set<String> parserOptions = new HashSet<String>() {
        {
            add(OPTION_LIVE);
            add(OPTION_ALL);
            add(OPTION_TODAY);
            add(OPTION_TOMORROW);
            add(OPTION_AFTER_TOMORROW);
        }
    };
    private final Map<String, Range> optionsRanges = new HashMap<String, Range>() {
        {
            put(OPTION_LIVE, new Range(0, 6));
            put(OPTION_ALL, new Range(0, 37));
            put(OPTION_TODAY, new Range(7, 17));
            put(OPTION_TOMORROW, new Range(17, 27));
            put(OPTION_AFTER_TOMORROW, new Range(27, 37));
        }
    };

    /**
     * Sets the report for this instance.
     * @param report The weather report.
     * @return Instance of this class for method chaining.
     */
    public Parser report(String report) {
        this.report = report;
        return this;
    }

    /**
     * Sets the options for parsing.
     * If the options are not valid, they will be ignored.
     * @param options Options for parsing.
     * @return Instance of this class for method chaining.
     */
    public Parser options(Set<String> options) {
        options.retainAll(parserOptions);
        this.options = options;
        return this;
    }

    /**
     * Parses the report(s) for the options given.
     * @return All the parsed reports.
     */
    public List<String> parse() {
        List<String> parsedReports = new ArrayList<String>();
        for (String optionKey: options) {
            String parsedReport = "";
            int startRange = optionsRanges.get(optionKey).getStart();
            int endRange = optionsRanges.get(optionKey).getEnd();
            List<String> reportLines = Arrays.asList(report.split("\n"));
            for (String line: reportLines.subList(startRange, endRange)) {
                parsedReport = parsedReport + line + "\n";
            }
            parsedReports.add(parsedReport);
        }
        return parsedReports;
    }

    public static class Range {
        private int start;
        private int end;
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        int getStart() {
            return start;
        }

        int getEnd() {
            return end;
        }
    }

}
