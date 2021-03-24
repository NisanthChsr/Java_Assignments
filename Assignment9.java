import java.util.regex.*;
import java.util.*;
public class Assignment9 {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        String line=scan.nextLine();
        String pattern="[A-Z].*[.,]";
        Pattern pat= Pattern.compile(pattern);
        Matcher match=pat.matcher(line);
        if(match.find())
        {
            System.out.println("This line satisfies the condition");
        }
        else
        {
            System.out.println("This line doesn't satisfies the condition");
        }
    }
}
