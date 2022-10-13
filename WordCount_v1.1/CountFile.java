// package wordcount;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class CountFile {

    JFrame frame = new JFrame("Word Count");
    JPanel panel = new JPanel();
    GridBagConstraints gbc = new GridBagConstraints();
    JLabel word = new JLabel("Word");
    JLabel count = new JLabel("Count");
    AnalyzeFile textFile;
    ArrayList<Map.Entry<String, Integer>> wordListCount;
    JButton newCount = new JButton("New Count");

    public CountFile(String filePath) {
        setUpAnalyzeFile(filePath);
        setUpPanel();
        setUpFrame();
    }

    private void setUpAnalyzeFile(String filePath) {
        textFile = new AnalyzeFile(filePath);
        wordListCount = textFile.getList();

    }

    private void setUpPanel() {
        panel.setLayout(new GridBagLayout());
        gbc.insets = new Insets(3, 3, 3, 3);
        // placing word button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(word, gbc);
        // placing count button
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(count, gbc);
        // setting up area to display text words and word count
        JPanel panel2 = new JPanel();
        GridLayout glsp = new GridLayout(0, 2);
        panel2.setLayout(glsp);
        // creating the labels for the list of word-count pairs
        for (Map.Entry<String, Integer> me : wordListCount) {
            JLabel jl0 = new JLabel(me.getKey());
            Integer i = me.getValue();
            String s = i.toString();
            JLabel jl1 = new JLabel(s);
            // set jlabel to have a default size
            jl0.setPreferredSize(new Dimension(450, 650 / 35));
            jl1.setPreferredSize(new Dimension(450, 650 / 35));
            // should be added to the scrollpane
            panel2.add(jl0);
            panel2.add(jl1);
        }
        // set up scroll pane for word count
        JScrollPane sp = new JScrollPane(panel2);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setPreferredSize(new Dimension(950, 650));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 10;
        gbc.gridheight = 6;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(sp, gbc);
        // placing new count button
        gbc.gridx = 8;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(newCount, gbc);
        newCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                PromptFile promptFile = new PromptFile();
            }

        });
    }

    private void setUpFrame() {
        frame.setSize(1000, 800);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
