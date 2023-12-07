import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
class Main {

    public static void main(String[] args) {

        int power_sum = 0;

        try {
            FileReader reader = new FileReader("inputtext.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            
            //read file line for line

            while ((line = bufferedReader.readLine())!= null){


                String[] arr_semicol = line.split(":");
                String parse_line = arr_semicol[1];

                // System.out.println(gameId);

                String[] strSplit = parse_line.split(";");

                ArrayList<String> StringLineList = new ArrayList<String>(
                    Arrays.asList(strSplit));

            
                //read line after ";" split'

                int max_blue_seen = 0;
                int max_green_seen = 0;
                int max_red_seen = 0;

                for (int i = 0; i < StringLineList.size(); i++) {
        
                    String line_semicol = StringLineList.get(i);

                    // split on comma again. build a hashmap

                    String[] comma_split = line_semicol.split(",");

                    for (int j = 0; j < comma_split.length; j++) {

                        String line_splitted = comma_split[j].strip();

                        // System.out.println(line_splitted);

                        String[] space_split = line_splitted.split(" ");

                        Integer amount = Integer.valueOf(space_split[0]);
                        String colour = space_split[1];
                        
                        if (colour.equals("red")) {
                            if (amount > max_red_seen) {
                                max_red_seen = amount;
                            } 
                        }
                        if (colour.equals("blue")) {
                            if (amount > max_blue_seen) {
                                max_blue_seen = amount;
                            } 
                        }
                        if (colour.equals("green")) {
                            if (amount > max_green_seen) {
                                max_green_seen = amount;
                            } 
                        }
                    }
                }
                
                float powers = max_blue_seen*max_green_seen*max_red_seen;
                System.out.println(powers);
                power_sum += powers;

            }

            bufferedReader.close();
        }

        catch (IOException e){
            System.err.println(e);
        }
        System.out.println(power_sum);
    }
}
