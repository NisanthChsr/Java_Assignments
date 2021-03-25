import java.util.*;
import java.io.*;

public class Assignment11 {
    public static void main(String[] args) throws IOException{

        HashMap<Character,Integer> characterCount=new HashMap<>();
        File file = new File(args[0]);

            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            //  String currentLine = reader.readLine();
            while (reader.readLine() !=  null) {
                String data = reader.readLine();
                for (int i = 0; i < data.length(); i++) {
                    if (characterCount.containsKey(data.charAt(i))) {
                        characterCount.put(data.charAt(i), characterCount.get(data.charAt(i)) + 1);
                    } else {
                        characterCount.put(data.charAt(i), 1);
                    }
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(args[0], true));
            for (Map.Entry<Character, Integer> entry : characterCount.entrySet()) {
                writer.append(entry.getKey() + "encountered" + entry.getValue());
            }

    }
}

