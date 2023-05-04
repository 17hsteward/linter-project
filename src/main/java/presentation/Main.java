package presentation;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Files", "java");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);

        File[] files = chooser.getSelectedFiles();


    }

}
