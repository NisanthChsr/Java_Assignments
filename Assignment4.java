import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.Calendar;

public class Assignment4 {
    public static void main(String[] args) throws Exception{
        Scanner scan=new Scanner(System.in);
        int inputCount=scan.nextInt();
        while(inputCount-->0)
        {
            String date1=scan.next();
            String date2=scan.next();
            Date signupDate=new SimpleDateFormat("dd-MM-yyyy").parse(date1);
            Date currDate=new SimpleDateFormat("dd-MM-yyyy").parse(date2);
            if(currDate.compareTo(signupDate)>0)
            {
                Date start,end,cal;
                cal=signupDate;
                cal.setYear(currDate.getYear());
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(cal);
                calendar.add(Calendar.DATE, 30);
                end=calendar.getTime();
                calendar.add(Calendar.DATE,-60);
                start=calendar.getTime();
                if(end.compareTo(currDate)>0)
                    end=currDate;
                SimpleDateFormat print=new SimpleDateFormat("dd-MM-yyyy");

                System.out.println(print.format(start)+"  "+print.format(end));

            }
            else
            {
                System.out.println("No range");
            }
        }
    }
}
