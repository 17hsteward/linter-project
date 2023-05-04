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
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import domain.MyClass;

public class Main {

    public static void main(String[] args) throws IOException {
    	List<MyClass> myClasses=new LinkedList<>();
    	//choose a java file to compile
//    	JFileChooser chooser=new JFileChooser();
//        chooser.setMultiSelectionEnabled(true);
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Files", "java");
//        chooser.setFileFilter(filter);
//        chooser.showOpenDialog(null);
//    	File[] files=chooser.getSelectedFiles();
    	File[] files= new File[]{new File("./src/main/java/Test.java")};
    	
    	for(File file:files) {
    		System.out.println(file.getAbsolutePath());
    		//compile java code to class file
    		JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            int result = javac.run(null, null, null, file.getAbsolutePath());
            if(result!=0) {
            	return;
            }
            //move destination, maybe in datasource layer later
            String className=file.getAbsolutePath().replace(".java", ".class");
            File from=new File(className);
    		File to=new File("./src/main/resources/"+from.getName());
    		System.out.println("create class file in "+to.getAbsolutePath());
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
			mc.printClass();
    	}
    	//start test here
    	//JFrame frame;
    }

    
}
