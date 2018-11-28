package FileRetriever;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URLEncoder;


public class FinderFileManager implements FileManager {

    @Override
    public String getInputFilePath() {
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setTitle("Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String path = dialog.getDirectory() + dialog.getFile();
        if(path == null || !isTextFile(path)){
            invalidInputErrorAlert("You must chose a text file to continue");
        }
        return path;
    }

    @Override
    public String getOutputPath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Please chose where you would like to save the file");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = chooser.showSaveDialog(null);
        String path = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            path = chooser.getCurrentDirectory().getPath();
        } else if (result == JFileChooser.CANCEL_OPTION || path == null) {
            invalidInputErrorAlert("You must chose a directory to continue");
        }
        return path;
    }

    @Override
    public String outputFileName() {
        String fileName = JOptionPane.showInputDialog("What would you like to name the file?");
        if(!isValidTextFileName(fileName)){
            invalidInputErrorAlert("Invalid text file name chosen");
        }
        return fileName;
    }

    private Boolean isValidTextFileName(String fileName){
        if(fileName == null || fileName == ""){
            return false;
        }
        try {
            URLEncoder.encode( fileName , "UTF-8");
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Boolean isTextFile(String fileName){
        return fileName.toLowerCase().endsWith(".txt");
    }

    private void invalidInputErrorAlert(String msg){
        //since instructions didn't specify what to do if wrong choice is made I exit and alert the user.
        JOptionPane.showMessageDialog(new JFrame(), msg, "Dialog",
                JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }
}
