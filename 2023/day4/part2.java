import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main{

    public static List<String> CleanseArray (List<String> arrayInput){

        List<String> fixedList = new ArrayList<String>() {};

        for (String item : arrayInput) {

        if(!item.isEmpty()) {

            if(item.charAt(0)==' ') {
                item = item.substring(1);

            }

            fixedList.add(item);
            
        }      
        }

        return(fixedList);

    }


    public static void main (String[] args){

        HashMap<Integer,Integer> lineTracker = new HashMap<>();
        lineTracker.put(1,1);


        try{   

            //total points counter
            int totalpoints = 0;

            //filereader stuff
            FileReader reader = new FileReader("inputtext.txt");

            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null){
                
                String[] lineColonSplit = line.split(":");
                line = lineColonSplit[1].strip();

                //regex
                Pattern pattern = Pattern.compile("Card\\s+(\\d+)");
                Matcher matcher = pattern.matcher(lineColonSplit[0]);
                
                int card_num = 0;
                if (matcher.find()) {
                    card_num = Integer.parseInt(matcher.group(1));
                }

                int reps = 0;
                if (lineTracker.get(card_num) == null){
                    reps = 1;
                }
                else {
                    reps = lineTracker.get(card_num);
                }
    

                for (int i=0; i<reps; i++) {

                    String[] line_pipe_split = line.split("\\|");
                    String WinningNumbers = line_pipe_split[0].strip();
                    String MyNumbers = line_pipe_split[1].strip();

                    //make winning numbers to array
                    String[] WinningList = WinningNumbers.split(" ");
                    List<String> winningList = Arrays.asList(WinningList);

                    //make what i scratched to array
                    String[] MyList = MyNumbers.split(" ");
                    List<String> myList = Arrays.asList(MyList);

                    //fix parsing with whitespaces and empty chars
                    myList = CleanseArray(myList);
                    winningList = CleanseArray(winningList);            

                    int wins = 0;

                    for (String winningnum : winningList) {
                        if (myList.contains(winningnum)) {
                            wins++;    
                        }
                    }

                    
                    //find
                    totalpoints+=wins;

                    int existing_val = 0;
                    for (int winamount = 1; winamount<wins+1; winamount++){

                        int next_index = card_num+winamount;

                        if (lineTracker.get(next_index) == null){
                            lineTracker.put(next_index,1);
                        }
                        else {
                            existing_val = lineTracker.get(card_num);
                            lineTracker.put(next_index,existing_val+1);
                        }

                    }

                    }

                };

            System.out.println(totalpoints);

            bufferedReader.close();


        }
        catch (IOException e){
            System.out.println(e);
        }



}
}

// 27454 correct