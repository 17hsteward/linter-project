package presentation;

import java.io.File;

import javax.swing.JFileChooser;

public class Main {

    public static void main(String[] args) {
    	JFileChooser chooser=new JFileChooser();
    	chooser.showOpenDialog(chooser);
    	File file=chooser.getSelectedFile();
    	System.out.println(file.getAbsolutePath());
    }
}
