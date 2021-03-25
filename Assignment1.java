import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Assignment1 {
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        File fr=new File("/home/nisac/");
        String files[]=fr.list();
        while(scan.next().charAt(0)=='y')
        {
            String pattern=scan.next();
            Pattern pat= Pattern.compile(pattern);
            for(int i=0;i< files.length;i++)
            {
                Matcher matcher = pat.matcher(files[i]);
                boolean matchFound = matcher.find();
                if(matchFound)
                {
                    System.out.println("/home/nisac/"+files[i]);
                }
            }
        }
    }
}