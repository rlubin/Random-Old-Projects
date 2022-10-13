// package wordcount;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class PromptFile {

    // remove the second constructor
    // make it so the window stays open after clicking choose file
    // make a function that will change the textfield of the window after selectfile
    // closers
    // more instance constants, windows buttons, layout
    // elgant code is achieving the same reult with fewer code that is easy to
    // follow

    public PromptFile() {

        JFrame frame = new JFrame("Word Count");
        frame.setSize(600, 100);

        // needs to be intialized before button1 is created
        JTextField textfield = new JTextField("", 35);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);

        JButton button1 = new JButton("Choose file");
        panel.add(button1, gbc);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectFile sf = new SelectFile();
                frame.dispose();
            }
        });

        // make the default name of the button the path to the computers desktop
        // make textfield say the path to the file selected from selectfile after file
        // is selected
        textfield.setEditable(false);
        panel.add(textfield, gbc);
        // how to make the textfield change to whatever was selected in the sf
        // SelectFile object//////////////////////////////////////////////////////////

        JButton button2 = new JButton("Count");
        panel.add(button2, gbc);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // make word only if the absolute path inside the textfield is a .txt, .doc(x)
                // file
                // if pressed and has correct file make a countresult window
                // else message pop up invalid file selection
                String path = textfield.getText();
                File file = new File(path);

                // if(file.exists() /*&& !file.isDirectory()*/) {//check it file path is to text
                // file//////////////////////////////////////////////

                // create count result window

                // throwing error when trying to find a large texdt file
                // SOME .txt ARE NOT CONSIDERED TXT FILES WHAT?

                // if path doesn't lead to a file
                try {
                    CountResult cr = new CountResult(path);// give it the path to the selected file
                    frame.dispose();
                } catch (Exception exception) {
                    JOptionPane optionpane = new JOptionPane("Error");
                    optionpane.showMessageDialog(frame, "Please choose a text file.", "File Selection Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
            /*
             * else { //create message pop up JOptionPane optionpane = new
             * JOptionPane("Error"); optionpane.showMessageDialog(frame,
             * "Please choose a text file.", "File Selection Error",
             * JOptionPane.ERROR_MESSAGE); }
             * 
             * }
             */

        });

        frame.add(panel);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public PromptFile(String path) {

        JFrame frame = new JFrame("Word Count");
        frame.setSize(600, 100);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);

        JButton button1 = new JButton("Choose file");
        panel.add(button1, gbc);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectFile sf = new SelectFile();
            }
        });

        // make the default name of the button the path to the computers desktop
        // make textfield say the path to the file selected from selectfile after file
        // is selected
        JTextField textfield = new JTextField(path, 35);
        textfield.setEditable(false);
        panel.add(textfield, gbc);
        // how to make the textfield change to whatever was selected in the sf
        // SelectFile object//////////////////////////////////////////////////////////

        JButton button2 = new JButton("Count");
        panel.add(button2, gbc);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // make word only if the absolute path inside the textfield is a .txt, .doc(x)
                // file
                // if pressed and has correct file make a countresult window
                // else message pop up invalid file selection
                String path = textfield.getText();
                File file = new File(path);
                if (file.exists() /* && !file.isDirectory() */) {// check it file path is to text file
                    // create count result window

                    // if path doesn't lead to a file
                    try {
                        CountResult cr = new CountResult(path);// give it the path to the selected file
                        frame.dispose();
                    } catch (Exception exception) {
                        JOptionPane optionpane = new JOptionPane("Error");
                        optionpane.showMessageDialog(frame, "Please choose a text file.", "File Selection Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    // create message pop up
                    JOptionPane optionpane = new JOptionPane("Error");
                    optionpane.showMessageDialog(frame, "Please choose a text file.", "File Selection Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        frame.add(panel);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
