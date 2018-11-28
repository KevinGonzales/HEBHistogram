import FileParser.FileParser;
import FileRetriever.FileManager;
import FileRetriever.FinderFileManager;
import HitogramCreator.HistogramCreator;
import HitogramCreator.LocalHistogramCreator;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        FileManager fileManager = new FinderFileManager();
        String inputFile = fileManager.getInputFilePath();
        String outputDir = fileManager.getOutputPath();
        String outputFileName = fileManager.outputFileName();
        FileParser fileParser = new FileParser();
        HashMap<String,Integer> words = fileParser.getWords(inputFile);
        HistogramCreator histogramCreator = new LocalHistogramCreator(outputDir,outputFileName,words);
        histogramCreator.createHistogram();
    }
}
