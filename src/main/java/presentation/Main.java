package presentation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class Main {

    public static void main(String[] args) throws IOException {
    	JFileChooser chooser=new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Files", "java");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
    	File file=chooser.getSelectedFile();

    	System.out.println(file.getAbsolutePath());


    	JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
        int result = javac.run(null, null, null, file.getAbsolutePath());
        if(result!=0) {
        	return;
        }

        InputStream in = null;
		try {
			String className=file.getAbsolutePath();
			className=className.replace(".java", ".class");
			in = new FileInputStream(new File(className));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		FileInputStream in=new FileInputStream(file);
		ClassReader reader=new ClassReader(in);

		// Step 2. ClassNode is just a data container for the parsed class
		ClassNode classNode = new ClassNode();

		reader.accept(classNode, ClassReader.EXPAND_FRAMES);

		printClass(classNode);
    }

    private static void printClass(ClassNode classNode) {
		System.out.println("Class's Internal JVM name: " + classNode.name);
		System.out.println("User-friendly name: "
				+ Type.getObjectType(classNode.name).getClassName());
		System.out.println("public? "
				+ ((classNode.access & Opcodes.ACC_PUBLIC) != 0));
		System.out.println("Extends: " + classNode.superName);
		System.out.println("Implements: " + classNode.interfaces);
		// TODO: how do I write a lint check to tell if this class has a bad name?
	}
}
