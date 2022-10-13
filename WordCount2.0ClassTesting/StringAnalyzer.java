// package wordcount2.pkg0classtesting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAnalyzer {

    private String string;
    private String result = "";
    private List<String> wordList = new ArrayList();
    private Map<String, Integer> wordMap = new HashMap();

    public StringAnalyzer() {
        string = "";
    }

    public StringAnalyzer(String string) {
        this.string = string;
        this.removePunctuation();
        this.removeCapitals();
        this.stringToList();
        this.listToMap();
        this.mapToString();
    }

    public void setString(String string) {
        this.string = string;
        this.removePunctuation();
        this.removeCapitals();
        this.stringToList();
        this.listToMap();
        this.mapToString();
    }

    public Map<String, Integer> getMap() {
        return wordMap;
    }

    private void removePunctuation() {
        string = string.replaceAll("\\p{Punct}", "");
    }

    private void removeCapitals() {
        string = string.toLowerCase();
    }

    private void stringToList() {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            if (matcher.group() != null) {
                wordList.add(matcher.group().trim());
            }
        }
    }

    private void listToMap() {
        for (String word : wordList) {
            if (wordMap.containsKey(word)) {
                wordMap.put(word, wordMap.get(word) + 1);
            } else {
                wordMap.put(word, 1);
            }
        }
    }

    private void mapToString() {
        Map<String, Integer> temp = wordMap;
        int size = wordMap.size();
        for (int i = 0; i < size; i++) {
            Map.Entry<String, Integer> high = null;
            for (Map.Entry<String, Integer> me : wordMap.entrySet()) {
                if (high == null || me.getValue().compareTo(high.getValue()) > 0) {
                    high = me;
                }
            }
            result = result + " " + high.getKey() + " \"" + high.getValue() + "\"\n";
            temp.remove(high.getKey());
        }
    }

    public String getString() {
        return result;
    }

    public void printList() {
        System.out.println(wordList);
    }

    public void printMap() {
        System.out.println(wordMap);
    }

    public int size() {
        return wordMap.size();
    }

}
