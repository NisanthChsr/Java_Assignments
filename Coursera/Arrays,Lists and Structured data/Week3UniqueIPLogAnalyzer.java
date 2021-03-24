package Assignments2;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import edu.duke.*;

public class Week3UniqueIPLogAnalyzer
{
    private ArrayList<Week3UniqueIPLogEntry> records;

    public Week3UniqueIPLogAnalyzer() {
        records = new ArrayList<Week3UniqueIPLogEntry>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);

        for (String line : fr.lines()) {
            Week3UniqueIPWebLogParser parser = new Week3UniqueIPWebLogParser();
            Week3UniqueIPLogEntry newEntry = parser.parseEntry(line);
            records.add(newEntry);
        }
    }

    public void printAll() {
        for (Week3UniqueIPLogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIps = new ArrayList<String>();

        for (Week3UniqueIPLogEntry le : records) {
            if (!uniqueIps.contains(le.getIpAddress())) {
                uniqueIps.add(le.getIpAddress());
            }
        }

        return uniqueIps.size();
    }

    public void printAllHigherThanNum(int num) {
        for (Week3UniqueIPLogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIps = new ArrayList<String>();

        for (Week3UniqueIPLogEntry le : records) {
            if (le.getAccessTime().toString().contains(someday)) {
                if (!uniqueIps.contains(le.getIpAddress())) {
                    uniqueIps.add(le.getIpAddress());
                }
            }
        }

        return uniqueIps;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIps = new ArrayList<String>();

        for (Week3UniqueIPLogEntry le : records) {
            if (le.getStatusCode() >= low && le.getStatusCode() <= high) {
                if (!uniqueIps.contains(le.getIpAddress())) {
                    uniqueIps.add(le.getIpAddress());
                }
            }
        }

        return uniqueIps.size();
    }
}

public class Week3UniqueIPLogEntry
{
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;

    public Week3UniqueIPLogEntry(String ip, Date time, String req, int status, int bytes) {
        ipAddress = ip;
        accessTime = time;
        request = req;
        statusCode = status;
        bytesReturned = bytes;

    }

    public String getIpAddress() {
        return ipAddress;
    }
    public Date getAccessTime() {
        return accessTime;
    }
    public String getRequest() {
        return request;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public int getBytesReturned() {
        return bytesReturned;
    }

    public String toString() {
        return ipAddress + " " + accessTime + " " + request
                + " " + statusCode + " " + bytesReturned;
    }
}

public class Week3UniqueIPTester
{
    public void testLogEntry() {
        Week3UniqueIPLogEntry le = new Week3UniqueIPLogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        Week3UniqueIPLogEntry le2 = new Week3UniqueIPLogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        Week3UniqueIPLogAnalyzer la = new Week3UniqueIPLogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }

    public void testUniqueIP() {
        Week3UniqueIPLogAnalyzer la = new Week3UniqueIPLogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("Total unique IPs = " + la.countUniqueIPs());
    }

    public void testPrintAllHigherThanNum() {
        Week3UniqueIPLogAnalyzer la = new Week3UniqueIPLogAnalyzer();
        la.readFile("short-test_log");
        la.printAllHigherThanNum(200);
    }

    public void testUniqueIPVisitsOnDay() {
        Week3UniqueIPLogAnalyzer la = new Week3UniqueIPLogAnalyzer();
        la.readFile("weblog-short_log");

        ArrayList<String> record = la.uniqueIPVisitsOnDay("Sep 14");
        System.out.println("Sep 14 = " + record);

        record = la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Sep 30 = " + record);
    }

    public void testCountUniqueIPsInRange() {
        Week3UniqueIPLogAnalyzer la = new Week3UniqueIPLogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("countUniqueIPsInRange 200 - 299 = " + la.countUniqueIPsInRange(200, 299));
        System.out.println("countUniqueIPsInRange 300 - 399 = " + la.countUniqueIPsInRange(300, 399));
    }
}

public class Week3UniqueIPWebLogParser {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy:kk:mm:ss Z", Locale.US);
    private static String munchTo(StringBuilder sb, String delim) {
        int x = sb.indexOf(delim);
        if (x == -1) {
            x = sb.length();
        }
        String ans = sb.substring(0,x);
        sb.delete(0, x + delim.length());
        return ans;
    }
    public static Week3LogFilesLogEntry parseEntry(String line) {
        //Assumes line is vald and in this format:
        //110.76.104.12 - - [30/Sep/2015:07:47:11 -0400] "GET //favicon.ico HTTP/1.1" 200 3426
        StringBuilder sb = new StringBuilder(line);
        String ip = munchTo(sb, " ");
        munchTo(sb, " "); //ignore -
        munchTo(sb, " ["); //ignore -, and eat the leading [
        String dateStr = munchTo(sb, "] \""); //]-space is intentional: eat both
        Date date = parseDate(dateStr);
        String request = munchTo(sb, "\" "); // quote-space is intentional: eat both
        String statusStr = munchTo(sb, " ");
        int status = Integer.parseInt(statusStr);
        String byteStr = munchTo(sb, " ");
        int bytes = Integer.parseInt(byteStr);
        return new Week3LogFilesLogEntry(ip, date, request, status, bytes);
    }
    public static Date parseDate(String dateStr) {
        ParsePosition pp = new ParsePosition(0);
        return  dateFormat.parse(dateStr, pp);
    }

}