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

    //helper function to remove ' ' from array and remove leading whitespace from each num
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

            //total reps counter
            int total_reps = 0;

            //filereader stuff
            FileReader reader = new FileReader("inputtext.txt");

            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null){
                
                String[] lineColonSplit = line.split(":");
                line = lineColonSplit[1].strip();

                //regex to get card num
                Pattern pattern = Pattern.compile("Card\\s+(\\d+)");
                Matcher matcher = pattern.matcher(lineColonSplit[0]);
                
                int card_num = 0;
                if (matcher.find()) {
                    card_num = Integer.parseInt(matcher.group(1));
                }

                //grab amounts we need to loop for each scratch card
                int reps = 0;
                if (!lineTracker.containsKey(card_num)){
                    lineTracker.put(card_num,1);
                }
                reps = lineTracker.get(card_num);
                
                //keep track of how many reps we have done = how many scratch cards
                total_reps+=reps;

                //loop amount we got from dict
                for (int i=0; i<reps; i++) { // changed from reps+1, was too many loops

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

                    //how many matches for each scratch card?
                    int matches = 0;
                    for (String winningnum : winningList) {
                        if (myList.contains(winningnum)) {
                            matches++;    
                        }
                    }
                    
                    //add amount of loops required for next cards to dict
                    int existing_val = 0;
                    for (int winamount = 1; winamount<matches+1; winamount++){

                        int next_index = card_num+winamount;
                
                        if (!lineTracker.containsKey(next_index)){
                            lineTracker.put(next_index,1);
                        }
                        else {
                            existing_val = lineTracker.get(next_index);
                            lineTracker.put(next_index,existing_val+1);
                            
                        }

                    }

                    }

                };

           
            System.out.println(lineTracker);
            System.out.println(total_reps);

            bufferedReader.close();


        }
        catch (IOException e){
            System.out.println(e);
        }
}
}

// 2093 too low

// maybe 3418379