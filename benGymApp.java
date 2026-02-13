import java.util.Scanner; //for reading keyboard input and Files
import java.io.File;
import java.io.PrintWriter; //for printing to a file
import java.io.IOException; //Exception that can be thrown by File and Scanner
import java.util.*;

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

public static void addCategories(ArrayList<String[]> exerciseData, ArrayList<String[]> pushExercises, ArrayList<String[]> pullExercises, ArrayList<String[]> legsExercises, ArrayList<String[]> otherExercises){
    for (String[] row : exerciseData){
        String exerciseNum = row[2];
        if (exerciseNum.equals("chest"))
            pushExercises.add(row);
        if (exerciseNum.equals("triceps"))
            pushExercises.add(row);
        if (exerciseNum.equals("shoulders"))
            pushExercises.add(row);
        if (exerciseNum.equals("back"))
            pullExercises.add(row);
        if (exerciseNum.equals("biceps"))
            pullExercises.add(row);
        if (exerciseNum.equals("arms"))
            pullExercises.add(row);
        if (exerciseNum.equals("legs"))
            legsExercises.add(row);
        if (exerciseNum.equals("abs"))
            otherExercises.add(row);

    }}

    public static ArrayList<String[]> searchInputs(
        ArrayList<String[]> gen,
        String userCat,
        int userCal,
        int userStress) {
        ArrayList<String[]> generals = new ArrayList<>();

        for (String[] row : gen) {
            if (Integer.parseInt(row[0]) <= userStress) {

            if (Integer.parseInt(row[3]) <= userCal) {

                    generals.add(row);
                }
            }
        }

        return generals;
}

    


















// Main Method

/* public static void main(String[] args){
    Scanner keyboard = new Scanner(System.in);
    String inputfile = keyboard.nextLine();
    ArrayList<String[]> exerciseData  = readDataFromFile(inputfile);
    ArrayList<String[]> pushExercises = new ArrayList<>();
    ArrayList<String[]> pullExercises = new ArrayList<>();
    ArrayList<String[]> legsExercises = new ArrayList<>();
    ArrayList<String[]> otherExercises = new ArrayList<>();
    addCategories(exerciseData, pushExercises, pullExercises, legsExercises, otherExercises);
    String userCategory = keyboard.nextLine();
    System.out.println("Calories: ");
    
    String calories = keyboard.nextLine();
    System.out.println("StressLVL");
    String stressLevel = keyboard.nextLine();
    ArrayList<String[]> results =
    searchInputs(pullExercises, userCategory,
                 Integer.parseInt(calories),
                 Integer.parseInt(stressLevel));

if (results.size() == 0) {
    System.out.println("No exercises matched your criteria.");
} else {
    for (String[] row : results) {
        for (String item : row) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
} */


 public static void main(String[] args) {

    Scanner keyboard = new Scanner(System.in);

    // Read file
    System.out.print("Enter filename: ");
    String inputfile = keyboard.nextLine();

    ArrayList<String[]> exerciseData = readDataFromFile(inputfile);

    // Create category lists
    ArrayList<String[]> pushExercises = new ArrayList<>();
    ArrayList<String[]> pullExercises = new ArrayList<>();
    ArrayList<String[]> legsExercises = new ArrayList<>();
    ArrayList<String[]> otherExercises = new ArrayList<>();

    // Categorize exercises
    addCategories(exerciseData, pushExercises, pullExercises, legsExercises, otherExercises);

    // User inputs
    System.out.print("Category (push / pull / legs / other): ");
    String userCategory = keyboard.nextLine().toLowerCase();

    System.out.print("Calories: ");
    int calories = Integer.parseInt(keyboard.nextLine());

    System.out.print("Stress level: ");
    int stressLevel = Integer.parseInt(keyboard.nextLine());

    // Choose correct category list
    ArrayList<String[]> selectedList = new ArrayList<>();

    if (userCategory.equals("push")) {
        selectedList = pushExercises;
    } else if (userCategory.equals("pull")) {
        selectedList = pullExercises;
    } else if (userCategory.equals("legs")) {
        selectedList = legsExercises;
    } else {
        selectedList = otherExercises;
    }

    // Search
    ArrayList<String[]> results =
            searchInputs(selectedList, userCategory, calories, stressLevel);

    // Output
    if (results.size() == 0) {
        System.out.println("No exercises matched your criteria.");
    } else {
        System.out.println("\nMatching Exercises:");
        for (String[] row : results){
            for (itm : row){

            }
        }
    }
}
   
    







