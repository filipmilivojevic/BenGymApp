import java.io.FileWriter;
import java.util.Scanner; //for reading keyboard input and Files
import java.io.File;
import java.io.PrintWriter; //for printing to a file
import java.io.IOException; //Exception that can be thrown by File and Scanner
import java.util.*;

public class benGymApp{

    public int category;
    public int calorie;
    public int stressLevel;
    public String exerciseName;
    public String inputfile;


    private ArrayList<String[]> exerciseData = new ArrayList<>();
    private ArrayList<String[]> pushExercises   = new ArrayList<>();
    private ArrayList<String[]> pullExercises   = new ArrayList<>();
    private ArrayList<String[]> legsExercises   = new ArrayList<>();
    private ArrayList<String[]> otherExercises  = new ArrayList<>();
// my basic constructor.
public benGymApp(String inputfile, int category, int calorie, int stressLevel){
    this.category = category;
    this.calorie = calorie;
    this.inputfile = inputfile;
    this.stressLevel = stressLevel;


}
// Used this to help me with file because i didnt find it in any of our projects https://www.w3schools.com/java/java_files_write.asp
public void writeToFile(String content){
    try{
        FileWriter writer = new FileWriter("dailyExercises.txt");
        writer.write(content);
        writer.close();
    } catch(IOException e){
        System.out.println("Ben looks like theres an error saving to a file");
    }
}




public void readDataFromFile(){
    ArrayList<String[]> benExercises = new ArrayList<>();
   try{
     File file = new File(this.inputfile);
    Scanner scan = new Scanner(this.inputfile);
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
    
    addCategories();
}

public void addCategories(){
    for (String[] row : exerciseData){
        String cat = row[2];
        if (cat.equals("chest") || cat.equals("triceps") || cat.equals("shoulders")){
            pushExercises.add(row);
        }
        else if (cat.equals("back") || cat.equals("biceps") || cat.equals("arms"))
            pullExercises.add(row);
        else if (cat.equals("legs"))
            legsExercises.add(row);
        else if (cat.equals("abs"))
            otherExercises.add(row);

    }}

    public static ArrayList<String[]> searchInputs(ArrayList<String[]> gen, String userCat, int userCal, int userStress) {
        ArrayList<String[]> generals = new ArrayList<>();

        for (String[] row : gen) {
            if (userStress >= Integer.parseInt(row[0]) && userStress <= Integer.parseInt(row[1])) {
                if (userCal >= Integer.parseInt(row[3]) && userCal <= Integer.parseInt(row[4])) {
                    generals.add(row);
                }
            }
        }

        return generals;
}

    


















// Main Method



 public static void main(String[] args) {
    System.out.println("gui Run");

 }}


   
    







