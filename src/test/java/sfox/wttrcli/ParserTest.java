package sfox.wttrcli;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class ParserTest extends TestCase {
    public ParserTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( ParserTest.class );
    }

    public void testInvalidOptions() {
        HashSet<String> parserOptions = new HashSet<String>() {
            {
                add("unknown_option");
            }
        };
        List<String> parsedReports =
                new Parser()
                        .report(MockReport.REPORT)
                        .options(parserOptions)
                        .parse();
        assertEquals(parsedReports.size(), 0); // Indicates no valid report was parsed.
    }

    public void testOptionLive() {
        HashSet<String> parserOptions = new HashSet<String>() {
            {
                add("live");
            }
        };
        List<String> parsedReports =
                new Parser()
                        .report(MockReport.REPORT)
                        .options(parserOptions)
                        .parse();
        assertEquals(parsedReports.get(0), getReportRange(MockReport.REPORT, new Parser.Range(0, 6)));
    }

    public void testOptionAll() {
        HashSet<String> parserOptions = new HashSet<String>() {
            {
                add("all");
            }
        };
        List<String> parsedReports =
                new Parser()
                        .report(MockReport.REPORT)
                        .options(parserOptions)
                        .parse();
        assertEquals(parsedReports.get(0), getReportRange(MockReport.REPORT, new Parser.Range(0, 37)));
    }

    public void testOptionToday() {
        HashSet<String> parserOptions = new HashSet<String>() {
            {
                add("today");
            }
        };
        List<String> parsedReports =
                new Parser()
                        .report(MockReport.REPORT)
                        .options(parserOptions)
                        .parse();
        assertEquals(parsedReports.get(0), getReportRange(MockReport.REPORT, new Parser.Range(7, 17)));
    }

    public void testOptionTomorrow() {
        HashSet<String> parserOptions = new HashSet<String>() {
            {
                add("tomorrow");
            }
        };
        List<String> parsedReports =
                new Parser()
                        .report(MockReport.REPORT)
                        .options(parserOptions)
                        .parse();
        assertEquals(parsedReports.get(0), getReportRange(MockReport.REPORT, new Parser.Range(17, 27)));
    }

    public void testOptionAfterTomorrow() {
        HashSet<String> parserOptions = new HashSet<String>() {
            {
                add("aftertomorrow");
            }
        };
        List<String> parsedReports =
                new Parser()
                        .report(MockReport.REPORT)
                        .options(parserOptions)
                        .parse();
        assertEquals(parsedReports.get(0), getReportRange(MockReport.REPORT, new Parser.Range(27, 37)));
    }

    private String getReportRange(String report, Parser.Range range) {
        String reportFromRange = "";
        List<String> reportLines = Arrays.asList(report.split("\n"));
        for (String line: reportLines.subList(range.getStart(), range.getEnd())) {
            reportFromRange = reportFromRange + line + "\n";
        }
        return reportFromRange;
    }
}
