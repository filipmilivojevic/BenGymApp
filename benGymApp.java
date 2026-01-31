import java.util.Scanner; //for reading keyboard input and Files
import java.io.File;
import java.io.PrintWriter; //for printing to a file
import java.io.IOException; //Exception that can be thrown by File and Scanner
import java.util.ArrayList;

public class benGymApp{

public static ArrayList<String[]> readDataFromFile(String filename){
    ArrayList<String[]> benExercises = new ArrayList<>();
   try{
     File file = new File(filename);
    Scanner scan = new Scanner(file);

    while (scan.hasNextLine()){
        String line = scan.nextLine();
        String[] row = line.split(",");
        benExercises.add(row);
    }
    scan.close();
    }
    catch (Exception e){
        System.out.println("Cannot Read file");
    }
    
    return benExercises;
}






// Main Method

public static void main(String[] args){
    Scanner keyboard = new Scanner(System.in);
    String inputfile = keyboard.nextLine();
    ArrayList<String[]> exerciseData  = readDataFromFile(inputfile);
    System.out.println(exerciseData.size());
}







}