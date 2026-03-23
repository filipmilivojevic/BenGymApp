import java.io.FileWriter;
import java.lang.reflect.Array;
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
public benGymApp(String inputfile, int calorie, int stressLevel){
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



// GPA Statistics asignment helped me on this method
public void readDataFromFile(){
   try{
     File file = new File(this.inputfile);
    Scanner scan = new Scanner(file);
    while (scan.hasNextLine()){
        String line = scan.nextLine();
        String[] row = line.split(",");
        this.exerciseData.add(row);
    }
    scan.close();
    }
    catch (Exception e){
        System.out.println("Cannot Read file");
    }
    
    addCategories();
}
// very straighforward method
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

    public ArrayList<String[]> searchInputs(ArrayList<String[]> gen) {
        ArrayList<String[]> generals = new ArrayList<>();

        for (String[] row : gen) {
            if (this.stressLevel >= Integer.parseInt(row[0]) && this.stressLevel <= Integer.parseInt(row[1])) {
                if (this.calorie >= Integer.parseInt(row[3]) && this.calorie <= Integer.parseInt(row[4])) {
                    generals.add(row);
                }
            }
        }

        return generals;
}


// Now after a few meetings with my client he requested 5 random exercises every time so i decided to make it 6 im using math.random for the algorithm
    public ArrayList<String[]> pickRandom (ArrayList<String[]> list, int count) {
        ArrayList<String[]> picked = new ArrayList<>();
        for (int i = 0; i < count && i < list.size(); i++) {
            int index = (int) (Math.random() * list.size());
            picked.add(list.get(index));
        }
        return picked;
    }


    
// Now it is time for my getter metods so i can use these later in my GUI code to make it much easier.
    public ArrayList<String[]> getPushExercises(){
    return pushExercises;
    }

    //get Pulls
    public ArrayList<String[]> getPullExercises(){
        return pullExercises;
    }

    //get Legs
    public ArrayList<String[]> getLegsExercises(){
    return legsExercises;
    }

    //get Other
    public ArrayList<String[]> getOtherExercises(){
    return otherExercises;
    }

















// Main Method



 public static void main(String[] args) {
    System.out.println("gui Run");
    // read a article for this code
    benGymGui gui = new benGymGui();

 }}


   
    







