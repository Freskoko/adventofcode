import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

                        if(wins == 0){
                            wins = 1;
                        }
                        else{
                            wins = wins*2;
                        }
                        
                        
                    }
                }
                totalpoints+=wins;
            }

            System.out.println(totalpoints);

            bufferedReader.close();


        }
        catch (IOException e){
            System.out.println(e);
        }



}
}

// 27454 correct