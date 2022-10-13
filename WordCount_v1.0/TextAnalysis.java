// package wordcount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class TextAnalysis {

    Map<String, Integer> countedWords;
    ArrayList<Map.Entry<String, Integer>> countedWordsList;

    public TextAnalysis(String filePath) {

        // creating a comparator for the tree map
        // comparaing first the count highest
        // comparaing second the word lexically
        /*
         * countedWords = new TreeMap( new Comparator<Map.Entry<String, Integer>>() {
         * 
         * @Override public int compare(Map.Entry<String, Integer> a, Map.Entry<String,
         * Integer> b) { return a.getValue().compareTo(b.getValue()); } });
         */

        countedWords = new HashMap();

        // string = "Your, face. is( full) of peanuts!";

        // use the pattern class to avoid errors

        // break string up into words

        // find a better way to split the string and then add it to the map

        // adjust the strings to be comparable
        // make everything lowercase
        // not working for some reason//////////////////
        // duplicates in my results what am i missing
        ArrayList<String> individWords = new ArrayList();
        ArrayList<String> aList = new ArrayList();
        String line;
        try {
            // try converting file line by line into an arraylist of strings
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                aList.add(line);
            }
        } catch (Exception e) {
            // box says error could not read file and stays at empty countresult page
        }

        // analyze the arraylist of strings
        for (String sentence : aList) {
            // trim the string add it to a string array then add it to the map
            sentence = sentence.toLowerCase();
            String[] words2 = sentence.split("\\s+");
            for (String word2 : words2) {
                individWords.add(word2);
            }
        }

        // if word already exists add 1 to its value
        // for every word added check to see if the word is already on the map, if so
        // update the map
        for (String word : individWords) {
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

        /*
         * String string = ""; string = string.replace("\n", " ");
         * 
         * //collect all of the strings separated by a space in an array of strings
         * String[] words = string.split("\\s+");
         * 
         * for(String word : words) { word = word.trim(); }
         * 
         * //add each word(lowercase) as a key to words with a value of 1 //add to words
         * to counted words
         * 
         * //if word already exists add 1 to its value //for every word added check to
         * see if the word is already on the map, if so update the map for(String word :
         * words) { boolean inMap = false; int valueOfWordInMap = 0;
         * for(Map.Entry<String, Integer> entry : countedWords.entrySet()) { //check if
         * word is an entry if(entry.getKey().equals(word)) { inMap = true;
         * valueOfWordInMap = entry.getValue(); } } if(inMap == false) {
         * countedWords.put(word, 1); } else { countedWords.put(word, valueOfWordInMap +
         * 1); } }
         */

        // sort countedWords, highest number first, lowest last
        // create another hashmap
        // go over the first hash map and find the highest count word
        // remove the highest count word from original list and add to new list
        // continue and the list will be empty
        sortList();
    }

    public ArrayList<Map.Entry<String, Integer>> getList() {
        return countedWordsList;
        // testing gui
        // test logic next
        // to test gui fake logic aka create a map manually

        /*
         * Map<String, Integer> test = new HashMap(); test.put("one", 1);
         * test.put("two", 2); test.put("three", 3); return test;
         */
    }

    private void sortList() {///////////////////////////////////// MUST GET SORT WORKING
        // get highest entry
        // save it as a temp
        // delete it from first list
        // add to second list
        // compare map entries, by value first, if value equal then by lexical

        ArrayList<Map.Entry<String, Integer>> list = new ArrayList();
        countedWordsList = new ArrayList();

        for (Map.Entry<String, Integer> entry : countedWords.entrySet()) {
            list.add(entry);
        }

        while (!list.isEmpty()) {
            // find the highest entry
            // remove it from list
            // add it to countedWordList
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

}
