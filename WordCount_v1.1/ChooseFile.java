// package wordcount;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ChooseFile {

    JFrame frame = new JFrame("Choose file");
    JPanel panel = new JPanel();
    JFileChooser fileChooser = new JFileChooser();

    public ChooseFile() {
        setUpFileChooser();
        setUpPanel();
        setUpFrame();
    }

    private void setUpFileChooser() {
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        String desktopPath = System.getProperty("user.home") + "/Desktop";
        desktopPath.replace("\\", "/");
        File file = new File(desktopPath);
        fileChooser.setCurrentDirectory(file);
        // setting up filechooser open and cancel button actions
        fileChooser.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (JFileChooser.APPROVE_SELECTION.equals(e.getActionCommand())) {
                    File file = fileChooser.getSelectedFile();
                    String path = file.getAbsolutePath();
                    frame.dispose();
                    PromptFile pf = new PromptFile(path);
                } else if (JFileChooser.CANCEL_SELECTION.equals(e.getActionCommand())) {
                    frame.dispose();
                    PromptFile pf = new PromptFile();
                }
            }
        });
    }

    private void setUpPanel() {
        panel.add(fileChooser);
    }

    private void setUpFrame() {
        frame.setSize(525, 375);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
