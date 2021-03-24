import java.util.*;

public class Assignment2 {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int characters[]=new int[26];
        String line=scan.nextLine();
        for(int i=0;i<line.length();i++)
        {
            if(Character.isAlphabetic(line.charAt(i)))
            {
                characters[Character.toLowerCase(line.charAt(i))-97]++;
            }
        }
        int flag=0;
        for(int i=0;i<26;i++)
        {
            if(characters[i]==0)
            {
                flag=1;
                break;
            }
        }
        if(flag==0)
        {
            System.out.println("Contains all the alphabets");
        }
        else
        {
            System.out.println("Doesn't contain all the alphabets");
        }
    }

}