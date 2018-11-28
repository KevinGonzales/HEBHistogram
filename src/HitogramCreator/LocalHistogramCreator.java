package HitogramCreator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class LocalHistogramCreator implements HistogramCreator{

    private String directory;
    private String fileName;
    private HashMap<String, Integer> words;

    public LocalHistogramCreator(String directory, String fileName, HashMap<String, Integer> words){
        this.directory = directory;
        this.fileName = fileName;
        this.words = words;
    }

    @Override
    public void createHistogram() {
        List<String> lines = makeLines();
        createOutPutFile(lines);
    }

    private List<String> makeLines(){
        List<String> lines = new ArrayList<>();
        for(Map.Entry<String,Integer> word: words.entrySet()){
            StringBuilder line = new StringBuilder();

            int offset = lengthOfLongestWord() - word.getKey().length();
            for (int i = 0; i < offset; i++){
                line.append(" ");
            }

            line.append(word.getKey()+" | ");

            for (int i =0; i< word.getValue(); i++){
                line.append("=");
            }

            line.append(" ("+word.getValue()+")");
            lines.add(line.toString());
        }

        return lines;
    }

    private int lengthOfLongestWord(){
        int longest = 0;
        for(String word: words.keySet()){
            if(word.length() > longest){
                longest = word.length();
            }
        }
        return longest;
    }

    private void createOutPutFile(List<String> lines){
        PrintWriter writer = null;
        try {
            String fullPath = directory+"/"+fileName+".txt";
            writer = new PrintWriter(fullPath, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (String line : lines){
            writer.println(line);
        }
        writer.close();
    }

}
