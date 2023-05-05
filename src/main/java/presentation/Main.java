package presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import domain.MyClass;
import domain.Compiler;

public class Main {
	//will split main code to different function or file later
    public static void main(String[] args) throws IOException {
    	List<MyClass> myClasses=new LinkedList<>();
    	//choose a java file to compile
//    	JFileChooser chooser=new JFileChooser();
//        chooser.setMultiSelectionEnabled(true);
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Files", "java");
//        chooser.setFileFilter(filter);
//        chooser.showOpenDialog(null);
//    	File[] files=chooser.getSelectedFiles();
    	File[] files=new File[]{new File("./src/test/java/domain/Test.java")};//choose specific file to test accessing
    	Compiler c=new Compiler();
    	myClasses=c.read(files);
    	
    	//start test here
    	//JFrame frame;
    }

    
}
