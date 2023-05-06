package presentation;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import domain.MyClass;
import domain.Compiler;
import domain.UMLGenerator;

public class Main {
	//will split main code to different function or file later
    public static void main(String[] args) throws IOException {
    	//these code may move to MainView
    	List<MyClass> myClasses=new LinkedList<>();
    	//choose a java file to compile
//    	JFileChooser chooser=new JFileChooser("./");
//        chooser.setMultiSelectionEnabled(true);
//        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Files", "java");
//        chooser.setFileFilter(filter);
//        chooser.showOpenDialog(null);
//    	File[] files=chooser.getSelectedFiles();
//    	File[] files=new File[]{new File("./src/test/java/domain/Test.java")};//choose specific file to test accessing
    	File[] files=new File[]{new File("./")};
    	
    	Compiler c=new Compiler();
    	myClasses=c.read(files);
    	
    	UMLGenerator uml=new UMLGenerator(myClasses);
    	System.out.println(uml.generateAllUMLCode());
    	//start check here
    }

    
}
