package Assignments2;

import java.util.*;
        import edu.duke.*;
import java.text.*;

public class Week3WebsiteLogAnalyzer
{
    private ArrayList<Week3WebsiteLogEntry> records;

    public Week3WebsiteLogAnalyzer() {
        records = new ArrayList<Week3WebsiteLogEntry>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);

        for (String line : fr.lines()) {
            Week3WebsiteWebLogParser parser = new Week3WebsiteWebLogParser();
            Week3WebsiteLogEntry newEntry = parser.parseEntry(line);
            records.add(newEntry);
        }
    }

    public void printAll() {
        for (Week3WebsiteLogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIps = new ArrayList<String>();

        for (Week3WebsiteLogEntry le : records) {
            if (!uniqueIps.contains(le.getIpAddress())) {
                uniqueIps.add(le.getIpAddress());
            }
        }

        return uniqueIps.size();
    }

    public void printAllHigherThanNum(int num) {
        for (Week3WebsiteLogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIps = new ArrayList<String>();

        for (Week3WebsiteLogEntry le : records) {
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

        for (Week3WebsiteLogEntry le : records) {
            if (le.getStatusCode() >= low && le.getStatusCode() <= high) {
                if (!uniqueIps.contains(le.getIpAddress())) {
                    uniqueIps.add(le.getIpAddress());
                }
            }
        }

        return uniqueIps.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();

        for (Week3WebsiteLogEntry le : records) {
            String ip = le.getIpAddress();

            if (!ipCounts.containsKey(ip)) {
                ipCounts.put(ip, 1);
            } else {
                ipCounts.put(ip, ipCounts.get(ip) + 1);
            }
        }

        return ipCounts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> ipCounts) {
        int maxVisit = Integer.MIN_VALUE;

        for (String key : ipCounts.keySet()) {
            int currentCount = ipCounts.get(key);

            if (currentCount > maxVisit) {
                maxVisit = currentCount;
            }
        }

        return maxVisit;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipCounts) {
        ArrayList<String> ipList = new ArrayList<String>();
        int maxVisit = mostNumberVisitsByIP(ipCounts);

        for (String key : ipCounts.keySet()) {
            int currentCount = ipCounts.get(key);

            if (currentCount == maxVisit) {
                ipList.add(key);
            }
        }

        return ipList;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipsPerDay = new HashMap<String, ArrayList<String>>();

        for (Week3WebsiteLogEntry le : records) {
            String accessTime = le.getAccessTime().toString();
            String day = accessTime.substring(4, 10);
            String currIp = le.getIpAddress();
            ArrayList<String> ipList;

            if (!ipsPerDay.containsKey(day)) {
                ipList = new ArrayList<String>();
            } else {
                ipList = ipsPerDay.get(day);
            }

            ipList.add(currIp);
            ipsPerDay.put(day, ipList);
        }

        return ipsPerDay;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsPerDay) {
        String day = "";
        int maxVisit = Integer.MIN_VALUE;

        for (String key : ipsPerDay.keySet()) {
            int currentVisit = ipsPerDay.get(key).size();

            if (currentVisit > maxVisit) {
                maxVisit = currentVisit;
                day = key;
            }
        }

        return day;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsPerDay, String day) {
        ArrayList<String> ipList = ipsPerDay.get(day);
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();

        for (String ip : ipList) {
            if (!ipCounts.containsKey(ip)) {
                ipCounts.put(ip, 1);
            } else {
                ipCounts.put(ip, ipCounts.get(ip) + 1);
            }
        }

        return iPsMostVisits(ipCounts);
    }
}

class Week3WebsiteLogEntry
{
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;

    public Week3WebsiteLogEntry(String ip, Date time, String req, int status, int bytes) {
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

class Week3WebsiteTester
{
    public void testLogEntry() {
        Week3WebsiteLogEntry le = new Week3WebsiteLogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        Week3WebsiteLogEntry le2 = new Week3WebsiteLogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        Week3WebsiteLogAnalyzer la = new Week3WebsiteLogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }

    public void testUniqueIP() {
        Week3WebsiteLogAnalyzer la = new Week3WebsiteLogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("Total unique IPs = " + la.countUniqueIPs());
    }

    public void testPrintAllHigherThanNum() {
        Week3WebsiteLogAnalyzer la = new Week3WebsiteLogAnalyzer();
        la.readFile("short-test_log");
        la.printAllHigherThanNum(200);
    }

    public void testUniqueIPVisitsOnDay() {
        Week3WebsiteLogAnalyzer la = new Week3WebsiteLogAnalyzer();
        la.readFile("weblog-short_log");

        ArrayList<String> record = la.uniqueIPVisitsOnDay("Sep 14");
        System.out.println("Sep 14 = " + record);

        record = la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Sep 30 = " + record);
    }

    public void testCountUniqueIPsInRange() {
        Week3WebsiteLogAnalyzer la = new Week3WebsiteLogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("countUniqueIPsInRange 200 - 299 = " + la.countUniqueIPsInRange(200, 299));
        System.out.println("countUniqueIPsInRange 300 - 399 = " + la.countUniqueIPsInRange(300, 399));
    }

    public void testCountVisitsPerIP() {
        Week3WebsiteLogAnalyzer la = new Week3WebsiteLogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> ipCounts = la.countVisitsPerIP();
        System.out.println(ipCounts);
    }

    public void testMostNumberVisitsByIP() {
        Week3WebsiteLogAnalyzer la = new Week3WebsiteLogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, Integer> ipCounts = la.countVisitsPerIP();
        System.out.println("Most number of visits = " + la.mostNumberVisitsByIP(ipCounts));
    }

    public void testIPsMostVisits() {
        Week3WebsiteLogAnalyzer la = new Week3WebsiteLogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, Integer> ipCounts = la.countVisitsPerIP();
        ArrayList<String> ipList = la.iPsMostVisits(ipCounts);
        System.out.println("Ips with most visits = " + ipList);
    }

    public void testIPsForDays() {
        Week3WebsiteLogAnalyzer la = new Week3WebsiteLogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ipsPerDay = la.iPsForDays();
        System.out.println("Ips per day = " + ipsPerDay);

    }

    public void testDayWithMostIPVisits() {
        Week3WebsiteLogAnalyzer la = new Week3WebsiteLogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ipsPerDay = la.iPsForDays();
        String day = la.dayWithMostIPVisits(ipsPerDay);
        System.out.println("Day with most ip visit = " + day);
    }

    public void testIPsWithMostVisitsOnDay() {
        Week3WebsiteLogAnalyzer la = new Week3WebsiteLogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ipsPerDay = la.iPsForDays();
        ArrayList<String> ipList = la.iPsWithMostVisitsOnDay(ipsPerDay, "Sep 30");
        System.out.println("IP with most visits on Sep 30 = " + ipList);
    }
}

class Week3WebsiteWebLogParser {
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
    public static Week3WebsiteLogEntry parseEntry(String line) {
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
        return new Week3WebsiteLogEntry(ip, date, request, status, bytes);
    }
    public static Date parseDate(String dateStr) {
        ParsePosition pp = new ParsePosition(0);
        return  dateFormat.parse(dateStr, pp);
    }

}