import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;

public class benGymGui implements ActionListener {
    JFrame mainFrame;
    JPanel mainPanel;
    JCheckBox[] selectionBoxes;
    JButton displayResultsButton;
    JTextField calorieEntry;
    JTextField stressEntry;
    JTextPane resultText;


public benGymGui(){
    mainFrame = new JFrame("Ben's Gym APP");
    mainPanel = new JPanel();
    selectionBoxes = buildCheckBoxes();
    displayResultsButton = new JButton("Calculate");
    displayResultsButton.addActionListener(this);
    userEntry = new JTextField();
    resultText = new JTextPane();
    resultText.setContentType("text/html");
}}