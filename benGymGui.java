import sun.security.util.ArrayUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Insets;
import java.util.ArrayList;

public class benGymGui implements ActionListener {
    ArrayList<String[]> lastResults = new ArrayList<>();
    benGymApp currentModel = null;
    JFrame mainFrame;
    JPanel mainPanel;
    JCheckBox[] selections;
    JButton displayResultsButton;
    JTextField filePathEntry;
    JTextField calorieEntry;
    JTextField stressEntry;
    JTextPane resultText;
    JButton saveFileButton;

    public benGymGui () {
        mainFrame = new JFrame("Ben's Gym App");
        mainPanel = new JPanel();
        selections = buildCheckBoxes();

        displayResultsButton = new JButton("Find Exercises");
        displayResultsButton.addActionListener(this);
        displayResultsButton.setBackground(new Color(220,224,217));

        displayResultsButton.setOpaque(true);
        displayResultsButton.setBorderPainted(false);


        saveFileButton = new JButton("Save File");
        saveFileButton.addActionListener(this);
        saveFileButton.setBackground(new Color(220,224,217));
        saveFileButton.setOpaque(true);
        saveFileButton.setBorderPainted(false);


        filePathEntry = new JTextField();
        calorieEntry = new JTextField();
        calorieEntry.setBackground(new Color(251,246,239));

        stressEntry = new JTextField();
        stressEntry.setBackground(new Color(251,246,239));


        resultText = new JTextPane();
        resultText.setContentType("text/html");
        resultText.setEditable(false);
        resultText.setBackground(new Color(251,246,239));

        // Layout
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(234,215,195)); // dark gray, change numbers for different color
        int x = 10;
        int y = 10;

        // Checkboxes
        for (JCheckBox checkBox : selections) {
            checkBox.setBounds(x, y, 300, 30);
            checkBox.setBackground(new Color(234,215,195));

            y += 25;
        }




        // Calorie label + field
        JLabel calLabel = new JLabel("Max Calories:");
        calLabel.setBounds(x, y, 420, 35);
        mainPanel.add(calLabel);
        y += 25;
        calorieEntry.setBounds(x, y, 590, 40);
        mainPanel.add(calorieEntry);
        y += 35;

        // Stress label
        JLabel stressLabel = new JLabel("Max Stress Level:");
        stressLabel.setBounds(x, y, 350, 25);
        mainPanel.add(stressLabel);
        y += 25;
        stressEntry.setBounds(x, y, 590, 40);
        mainPanel.add(stressEntry);
        y += 50;

        // Result Text ARea
        JScrollPane scrollPane = new JScrollPane(resultText);
        scrollPane.setBounds(x, y, 590, 450);
        mainPanel.add(scrollPane);
        y += 460;


        // Buttons
        displayResultsButton.setBounds(x, y, 590, 50);
        displayResultsButton.setMargin(new Insets(1, 1, 1, 1));
        mainPanel.add(displayResultsButton);


        saveFileButton.setBounds(x, y+75, 590,50);
        saveFileButton.setMargin(new Insets(1, 1, 1, 1));
        mainPanel.add(saveFileButton);

        // Add checkboxes
        for (JCheckBox checkBox : selections) {
            mainPanel.add(checkBox);
        }

        mainFrame.getContentPane().add(mainPanel);
    }

    private void createAndShowGUI() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(630, 900);
        mainFrame.setVisible(true);
    }

    private JCheckBox[] buildCheckBoxes() {
        String[] options = categoryOptions();
        JCheckBox[] boxes = new JCheckBox[options.length];
        for (int i = 0; i < options.length; i++) {
            boxes[i] = new JCheckBox(options[i]);
        }
        return boxes;
    }

    public String[] categoryOptions() {
        return new String[]{"Push (Chest, Triceps, Shoulders)", "Pull (Back, Biceps, Arms)", "Legs", "Other (Abs)"};
    }

    public String colorText(String text, String color) {
        return "<span style=\"color:" + color + ";\">" + text + "</span>";
    }


    public void saveFileMethod(){
        if (currentModel == null){
            resultText.setText("<b style='color:red;'> Run Something Before Saving.</b>");

        }
        StringBuilder content = new StringBuilder();
        for (String[] row : lastResults) {
            content.append("Category: ").append(row[2]).append(" | Exercise: ").append(row[5]).append(" | Calories: ").append(row[3]).append(" | Stress: ").append(row[0]).append("\n");
        }

        // The line below I didnt make a tostring method instead I used one from the arraylists
        currentModel.writeToFile(content.toString());
    }



    public void displayGymResults() {
        try {
            String filePath = "bensExercises.csv";

            int calories = Integer.parseInt(calorieEntry.getText());
            int stress = Integer.parseInt(stressEntry.getText());
            // My model is from the constructor so It connects the two codes / files together I loved this idea
            currentModel = new benGymApp(filePath, calories, stress);
            currentModel.readDataFromFile();



            ArrayList<String[]> selectedList = new ArrayList<>();
            if (selections[0].isSelected()) selectedList.addAll(currentModel.getPushExercises());
            if (selections[1].isSelected()) selectedList.addAll(currentModel.getPullExercises());
            if (selections[2].isSelected()) selectedList.addAll(currentModel.getLegsExercises());
            if (selections[3].isSelected()) selectedList.addAll(currentModel.getOtherExercises());

            if (selectedList.isEmpty()) {
                resultText.setText("<b style='color:red; font-family: Arial, sans-serif;'>Please select at least one category.</b>");

            }


            ArrayList<String[]> filteredList = currentModel.searchInputs(selectedList);
            lastResults = currentModel.pickRandom(filteredList,6);

            // Display results
            if (lastResults.isEmpty()) {
                resultText.setText("<b style='color:red; font-family: Arial, sans-serif;'>No exercises matched your criteria.</b>");
            } else {
                String html = "<b style='font-family: Arial;'>Matching Exercises:</b><br><br>";
                for (String[] row : lastResults) {
                    html +=
                            " | Category: " + colorText(row[2], "navy") +
                            " | Exercise: " + colorText(row[5], "green") +
                            " | Calories: " + colorText(row[3], "red") +
                            " | Stress: " + colorText(row[0], "purple") +
                            "<br>";
                }
                resultText.setText(html);
            }

        } catch (NumberFormatException e) {
            resultText.setText("<b style='color:red; font-family: Arial, sans-serif;'>Ben, enter valid numbers for Stress Level (1-2) and Calorie</b>");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == displayResultsButton)
            displayGymResults();
        else if (e.getSource() == saveFileButton)
            saveFileMethod();
    }

    public static void main(String[] args) {
        benGymGui gui = new benGymGui();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui.createAndShowGUI();
            }
        });
    }
}