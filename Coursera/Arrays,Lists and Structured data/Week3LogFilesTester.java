package Assignments2;

import java.util.*;

public class Week3LogFilesTester
{
    public void testLogEntry() {
        Week3LogFilesLogEntry le = new Week3LogFilesLogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        Week3LogFilesLogEntry le2 = new Week3LogFilesLogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        Week3LogFilesLogAnalyzer la = new Week3LogFilesLogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
}