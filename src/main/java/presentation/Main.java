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
    	
    	JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
    	int result;
    	String resources="./src/main/resources/";
    	for(File file:files) {
    		//need to check file type is .java here
    		System.out.println("compiling    "+file.getName()+"        at    "+file.getAbsolutePath());
    		
    		//compile java code to class file
            result = javac.run(null, null, null, file.getAbsolutePath());
            if(result!=0) {
            	System.out.println("fail to compile "+file.getName());
            	continue;
            }
            //move destination, maybe in datasource layer later
            String className=file.getAbsolutePath().replace(".java", ".class");
            File from=new File(className);
    		File to=new File(resources+from.getName());
    		try {
        		FileInputStream fi = new FileInputStream(from);
    	    	FileOutputStream fo = new FileOutputStream(to);
    	    	Integer by = 0;
    	    	while((by = fi.read()) != -1) {
    			    fo.write(by);
    		    }
    		    fi.close();
    		    fo.close();
        	}catch(Exception e1) {
        		;
        	}
    		from.delete();
    		
            InputStream in = null;
    		try {
    			in = new FileInputStream(to);
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		

    		ClassReader reader=new ClassReader(in);
    		ClassNode classNode = new ClassNode();
    		reader.accept(classNode, ClassReader.EXPAND_FRAMES);
    		
    		MyClass mc=new MyClass(classNode);
			myClasses.add(mc);
			mc.printClass();//print the class to verify
			
			in.close();
			to.delete();//comment this line to keep the class file in src/main/resources/
    	}
    	//start test here
    	//JFrame frame;
    }

    
}
