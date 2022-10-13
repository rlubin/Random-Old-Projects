// package wordcount;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class PromptFile {
    
    JFrame frame = new JFrame("Word Count");
    JPanel panel = new JPanel();
    GridBagConstraints gbc = new GridBagConstraints();
    JButton chooseFile = new JButton("Choose file");
    JTextField fileLocation = new JTextField("", 35);
    JButton count = new JButton("Count");
    
    public PromptFile() {
        setUpChooseFileButton();
        setUpFileLocationTextArea();
        setUpCountButton();
        setUpPanel();
        setUpFrame();
    }
    
    public PromptFile(String filePath) {
        setUpChooseFileButton();
        setUpFileLocationTextArea(filePath);
        setUpCountButton(filePath);
        setUpPanel();
        setUpFrame();
    }
    
    private void setUpChooseFileButton() {
        //setting up chooseFile button
        chooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseFile chooseFile = new ChooseFile();
                frame.dispose();
            }
        });
    }
    
    private void setUpFileLocationTextArea() {
        //setting up fileLocation textfield
        fileLocation.setEditable(false);
    }
    
    private void setUpFileLocationTextArea(String filePath) {
        //setting up fileLocation textfield
        //change text to filePath
        fileLocation.setText(filePath);
        fileLocation.setEditable(false);
    }
    
    private void setUpCountButton() {
        //count button displays an error message
        count.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane optionpane = new JOptionPane("Error");
                optionpane.showMessageDialog(frame, "Please choose a text file to count.", "File Selection Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private void setUpCountButton(String filePath) {
        //setting up count button
        //checks if file at location exists
        //if file doesn't exist error window
        //if file exists move to the next step
        count.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(filePath);
                if(file.exists() && !file.isDirectory()) {
                    try {
                        CountFile countFile = new CountFile(filePath);
                        frame.dispose();
                    } catch(Exception ex) {
                        JOptionPane optionpane = new JOptionPane("Error");
                        optionpane.showMessageDialog(frame, "Please choose a text file to count.", "File Selection Error", JOptionPane.ERROR_MESSAGE);
                    }  
                } else {
                    JOptionPane optionpane = new JOptionPane("Error");
                    optionpane.showMessageDialog(frame, "Please choose a text file to count.", "File Selection Error", JOptionPane.ERROR_MESSAGE);
                }
                    }
                });
    }
    
    private void setUpPanel() {
        panel.setLayout(new GridBagLayout());
        gbc.insets = new Insets(3, 3, 3, 3);
        panel.add(chooseFile, gbc);
        panel.add(fileLocation, gbc);
        panel.add(count, gbc);
    }
    
    
    private void setUpFrame() {
        frame.setSize(600, 100);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
