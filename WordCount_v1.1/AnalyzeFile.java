// package wordcount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class AnalyzeFile {

    Map<String, Integer> countedWords = new HashMap();
    ArrayList<Map.Entry<String, Integer>> countedWordsList = new ArrayList();
    ArrayList<String> words = new ArrayList();
    ArrayList<String> sentences = new ArrayList();

    public AnalyzeFile(String filePath) {
        fileToString(filePath);
        stringRemovePunctuation();
        stringToArray();
        arrayToMap();
        mapToSortedList();
    }

    private void fileToString(String filePath) {
        String line;
        try {
            // try converting file line by line into an arraylist of strings
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                sentences.add(line);
            }
        } catch (Exception e) {
            // box says error could not read file and stays at empty countresult
            // page////////////////////////////////////////////////////////////////
        }
    }

    /*
     * CREATE A FUNCTION TO TAKE OUT UNNESSECARY GRAMMAR SUCH AS
     * """, ",", ".", ":", ";", "\", "|", etc ONLY KEEP LETTERS
     * 
     * LEARN TO USE REGEX CLASSES
     * 
     */
    private void stringRemovePunctuation() {////////////////////////////////////////////////////////////////////////////////////////////////////////
        for (String sentence : sentences) {
            sentence = sentence.replaceAll("\\p{Punct}", "");// not working correctly
        }
    }

    private void stringToArray() {
        // analyze the arraylist of strings
        for (String sentence : sentences) {
            // trim the string add it to a string array then add it to the map
            sentence = sentence.toLowerCase();
            String[] words2 = sentence.split("\\s+");
            for (String word2 : words2) {
                words.add(word2);
            }
        }
    }

    private void arrayToMap() {
        // if word already exists add 1 to its value
        // for every word added check to see if the word is already on the map, if so
        // update the map
        for (String word : words) {
            boolean inMap = false;
            int valueOfWordInMap = 0;
            for (Map.Entry<String, Integer> entry : countedWords.entrySet()) {
                // check if word is an entry
                if (entry.getKey().equals(word)) {
                    inMap = true;
                    valueOfWordInMap = entry.getValue();
                }
            }
            if (inMap == false) {
                countedWords.put(word, 1);
            } else {
                countedWords.put(word, valueOfWordInMap + 1);
            }
        }
    }

    private void mapToSortedList() {
        // highest count first
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList();
        countedWordsList = new ArrayList();
        for (Map.Entry<String, Integer> entry : countedWords.entrySet()) {
            list.add(entry);
        }
        while (!list.isEmpty()) {
            Map.Entry<String, Integer> biggest = list.get(0);
            for (Map.Entry<String, Integer> me : list) {
                if (me.getValue() > biggest.getValue()) {
                    biggest = me;
                }
            }
            countedWordsList.add(biggest);
            list.remove(biggest);
        }
    }

    public ArrayList<Map.Entry<String, Integer>> getList() {
        return countedWordsList;
    }

}
