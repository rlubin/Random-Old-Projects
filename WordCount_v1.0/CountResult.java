// package wordcount;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class CountResult {

    public CountResult(String filePath) throws FileNotFoundException {

        JFrame frame = new JFrame("Word Count");
        frame.setSize(1000, 800);

        JPanel panel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);

        // add a title for each column
        // word and count
        JLabel word = new JLabel("Word");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(word, gbc);

        JLabel count = new JLabel("Count");
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(count, gbc);

        // open file at filePath
        // convert file contents to string
        // String text = new Scanner(new File(filePath)).useDelimiter("\\A").next();

        TextAnalysis textanalysis = new TextAnalysis(filePath);
        ArrayList<Map.Entry<String, Integer>> list = textanalysis.getList();// will come out sorted

        // creating panel for words
        JPanel panel2 = new JPanel();
        GridLayout glsp = new GridLayout(0, 2);
        panel2.setLayout(glsp);

        // for each map string create 2 uneditable textlabels
        // 1 for the word and the other for the count
        // highest to lowest
        for (Map.Entry<String, Integer> me : list) {// or test
            // adjust size and positioning of the jlabel
            // fixed width and height
            // added at the bottom of the components
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

        // add scrollpane and then put the words and numbers output in
        // make word and count outside of the scollpane so you can always see them
        JScrollPane sp = new JScrollPane(panel2);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setPreferredSize(new Dimension(950, 650));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 10;
        gbc.gridheight = 6;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(sp, gbc);

        // if contents are not strings open window that says no text in file
        // hitting okay on this window opens a new promptfile window

        JButton button = new JButton("New Count");
        gbc.gridx = 8;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(button, gbc);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                PromptFile pf = new PromptFile();
            }

        });

        frame.add(panel);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
