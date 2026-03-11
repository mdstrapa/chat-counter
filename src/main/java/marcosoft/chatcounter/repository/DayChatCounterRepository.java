package marcosoft.chatcounter.repository;

import marcosoft.chatcounter.SystemStrings;
import marcosoft.chatcounter.model.DayChatCounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DayChatCounterRepository {

    SystemStrings ss = new SystemStrings();

    public List<DayChatCounter> getDayChatCounterList(){
        List<DayChatCounter> dayChatCounterList = new ArrayList<>();
        Path filePath = Path.of(ss.DATABASE_TEXT_FILE);
        try{
            List<String> lines = Files.readAllLines(filePath);

            for(String line : lines){
                String[] lineContent = line.split(";");

                // before adding the new day to the day list
                // we have to check if it already exists
                // if so, we first have to remove it
                // and them adding the new version

                if(!dayChatCounterList.isEmpty() && dayChatCounterList.get(dayChatCounterList.size() - 1).getDay().equals(LocalDate.parse(lineContent[0]))){
                    dayChatCounterList.remove(dayChatCounterList.size() - 1);
                }

                dayChatCounterList.add(new DayChatCounter(LocalDate.parse(lineContent[0]),
                        Integer.parseInt(lineContent[1]),
                        Integer.parseInt(lineContent[2]),
                        Integer.parseInt(lineContent[3]),
                        Integer.parseInt(lineContent[4])));
            }

            return dayChatCounterList;

        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
