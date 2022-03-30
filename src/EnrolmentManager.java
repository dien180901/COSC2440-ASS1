import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EnrolmentManager {
//    private boolean Enrol(){
//
//    }

    private StudentEnrolmentList studentEnrolmentList;

    public static < E > void addToList(ArrayList<E> addedList,E data){
        if (!addedList.contains(data)){
            addedList.add(data);
        }
    }
    public static void dataProcessing(){
        try{
            String path="src/data/default.csv";
            BufferedReader bufferedReader =  new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] arrOfStr = line.split(",");
                System.out.println(Arrays.toString(arrOfStr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        dataProcessing();
    }
}
