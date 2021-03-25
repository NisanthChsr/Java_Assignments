import java.io.*;
import java.util.*;

public class Assignment3 {
    public static void runSystemCommand(String command) {
        try {
            List<Float> time=new ArrayList<>();
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String s = "";
            while ((s = inputStream.readLine()) != null) {
                System.out.println(s);
                int index=s.indexOf("time=");
                if(index!=-1)
                    time.add(Float.parseFloat(s.substring(index+5,s.indexOf("ms"))));
            }
            Collections.sort(time);
            System.out.println(time);
            if(time.size()%2==0)
                System.out.println("The median is "+(time.get(time.size()/2)+time.get((time.size()/2)-1))/2);
            else
                System.out.println("The median is "+time.get(time.size()/2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the ip address");
        String ip =scanner.next();
        System.out.println("Enter the number of times to ping");
        int c=scanner.nextInt();
        runSystemCommand("ping -c "+c+" "+ ip);
    }
}
