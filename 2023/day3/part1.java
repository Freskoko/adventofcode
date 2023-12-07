import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
class Main {

    public static void main(String[] args) {

        int part_sum = 0;

        try {

        FileReader reader = new FileReader("inputtext.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;

        ArrayList<String[]> allLines = new ArrayList<String[]>();

        while ((line = bufferedReader.readLine())!= null){

            String[] line_as_str = line.split("");

            allLines.add(line_as_str);

        }

        bufferedReader.close();

        // File lines parsed, now find symbols

        String current_num = "";

        boolean isSchematic = false;

        for (int current_row = 0; current_row < allLines.size(); current_row++) {

            String[] row = allLines.get(current_row);

            if (isSchematic){

                System.out.println(current_num);
                try{
                    part_sum+=Integer.valueOf(current_num);
                }
                catch (NumberFormatException e){
                    part_sum+=0;
                }
            }

            current_num = "";

            for (int current_col = 0; current_col < row.length; current_col++) {

                String indv_char = row[current_col];

                if ("0123456789".contains(indv_char)) {
                    current_num = current_num + indv_char;

                    //sort of like list of tuples with coords in python how do i do this in java
                    Integer[][] coords = {
                        {-1,0},
                        {+1,0},
                        {0,+1},
                        {0,-1},
                        {-1,-1},
                        {-1,+1},
                        {+1,+1},
                        {+1,-1},
                    };

                    for (Integer[] coord : coords) {
                        int x_coord = coord[0];
                        int y_coord = coord[1];

                        try{
                            if ("0123456789.".contains(
                                    allLines.get(current_row+x_coord)[current_col+y_coord])== false) {
                                isSchematic = true;
                            }
                        }
                        catch (IndexOutOfBoundsException e){
                            ;
                        }
                    }
                }

                else {
                
                    if (isSchematic){

                        System.out.println(current_num);
                        try{
                            part_sum+=Integer.parseInt(current_num);
                        }
                        catch (NumberFormatException e){
                            part_sum+=0;
                        }
                    }

                    current_num = "";
                    isSchematic = false;

                }
            }
        }

        }
        catch (IOException e){
            System.out.println(e);
        }

        System.out.println(part_sum);
    }
}

//465839 too low

//519015 too low again

//526729 maybe this?---

//524603 wrong

//528049 wrong

//712543 too high