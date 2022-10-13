// package wordcount;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class SelectFile {

    public SelectFile() {

        JFrame frame = new JFrame("Choose file");
        frame.setSize(525, 375);

        JPanel panel = new JPanel();

        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        String string = System.getProperty("user.home") + "/Desktop";
        string.replace("\\", "/");
        System.out.println(string);
        File file = new File(string);
        filechooser.setCurrentDirectory(file);
        filechooser.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent evt) {
                if (JFileChooser.APPROVE_SELECTION.equals(evt.getActionCommand())) {
                    // Open or Save was clicked
                    File file = filechooser.getSelectedFile();
                    String path = file.getAbsolutePath();
                    // when open is pressed with a valid path
                    // send path to promptfile and change the fextfield to the new path
                    frame.dispose();
                    PromptFile pf = new PromptFile(path);
                } else if (JFileChooser.CANCEL_SELECTION.equals(evt.getActionCommand())) {
                    // Cancel was clicked
                    frame.dispose();
                    PromptFile pf = new PromptFile();
                }
            }
        });
        panel.add(filechooser);

        frame.add(panel);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
