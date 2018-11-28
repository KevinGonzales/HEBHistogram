package FileParser;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileParser {

    public HashMap<String,Integer> getWords(String file){
        List<String> lines = readFileInToList(file);
        return wordsFromList(lines);
    }

    public static List<String> readFileInToList(String fileName)
    {
        List<String> lines = new ArrayList<>();
        try
        {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
        return lines;
    }

    //assuming a string like hey! or hey# is hey and valid words can only be alphabetical so hack4ever would be hackever
    private HashMap<String,Integer> wordsFromList(List<String> lines){
        HashMap<String,Integer> words = new HashMap<>();

        for(String line: lines){
            String[] nonFilteredWords = line.split("\\s+");
            addWordsFromStrings(nonFilteredWords, words);
        }
        return words;
    }

    private void addWordsFromStrings(String[] nonFilteredWords, HashMap<String,Integer> words){
        for(int i = 0; i < nonFilteredWords.length; i++){
            // is are == Are ? assuming yes
            String word = nonFilteredWords[i].replaceAll("[^a-zA-Z]", "").toLowerCase();
            if(word.equals("")){
                continue;
            }
            if(words.containsKey(word)){
                int oldVal = words.get(word);
                words.put(word,oldVal +1);
            }
            else {
                words.put(word, 1);
            }
        }
    }
}
