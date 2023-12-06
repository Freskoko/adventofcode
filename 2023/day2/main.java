import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap; 

class Main {

    public static void main(String[] args) {

        ArrayList<String> colours = new ArrayList<>(Arrays.asList("red","blue","green"));
    
        HashMap<String, Integer> HashmapMax = new HashMap<String,Integer>() {{
            put("red",12);
            put("green",13);
            put("blue",14);
            }};

        int possible_id_sum = 0;

        try {
            FileReader reader = new FileReader("inputtext.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            
            //read file line for line

            while ((line = bufferedReader.readLine())!= null){

                boolean LinePassed = true;

                String[] arr_semicol = line.split(":");
                String parse_line = arr_semicol[1];

                int gameId = Integer.parseInt(arr_semicol[0].replaceAll("[\\D]", ""));
                
                // System.out.println(gameId);

                String[] strSplit = parse_line.split(";");

                ArrayList<String> StringLineList = new ArrayList<String>(
                    Arrays.asList(strSplit));

            
                //read line after ";" split

                for (int i = 0; i < StringLineList.size(); i++) {
        
                    String line_semicol = StringLineList.get(i);

                    // split on comma again. build a hashmap

                    String[] comma_split = line_semicol.split(",");

                    HashMap<String, Integer> Hashmap = new HashMap<String,Integer>();

                    for (int j = 0; j < comma_split.length; j++) {

                        String line_splitted = comma_split[j].strip();

                        // System.out.println(line_splitted);

                        String[] space_split = line_splitted.split(" ");

                        Integer amount = Integer.valueOf(space_split[0]);
                        String colour = space_split[1];
                        
                        Hashmap.put(colour,amount);

                    }

                    for (String colour : colours) {

                        try {

                            if (Hashmap.get(colour) > HashmapMax.get(colour)) {
                                LinePassed = false;
                                System.out.println(Hashmap.get(colour));
                                System.out.println(HashmapMax.get(colour));
                            }

                        } 
                        catch (NullPointerException e){
                            ;
                        }
                        
                    }
                }

                if (LinePassed){
                    possible_id_sum += gameId;
                }

            }



            bufferedReader.close();
        }

        catch (IOException e){
            System.err.println(e);
        }
        System.out.println(possible_id_sum);
    }
}

//14105 too high
//13881 too high
//12664 wrong