package Assignments2;

import java.util.*;
        import edu.duke.*;

public class Week3LogFilesLogAnalyzer
{
    private ArrayList<Week3LogFilesLogEntry> records;

    public Week3LogFilesLogAnalyzer() {
        records = new ArrayList<Week3LogFilesLogEntry>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);

        for (String line : fr.lines()) {
            Week3LogFilesWebLogParser parser = new Week3LogFilesWebLogParser();
            Week3LogFilesLogEntry newEntry = parser.parseEntry(line);
            records.add(newEntry);
        }
    }

    public void printAll() {
        for (Week3LogFilesLogEntry le : records) {
            System.out.println(le);
        }
    }


}
