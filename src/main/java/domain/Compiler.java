package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import datasource.Reader;

import java.util.LinkedList;

public class Compiler {
	JavaCompiler javac;
	Reader reader;
	public Compiler() {
		javac = ToolProvider.getSystemJavaCompiler();
		this.reader=new Reader();
	}
	public List<MyClass> read(File[] files) throws IOException{
		List<MyClass> myClasses=new LinkedList<>();
		for(File f:files) {
			if(f.isDirectory()) {
				File[] sub=new File[f.list().length];
				int i=0;
				for(String s:f.list()) {
					sub[i]=new File(s);
					i++;
				}
				myClasses.addAll(read(sub));
			}
			if(f.getName().endsWith(".java")) {
				if(javac.run(null, null, null, f.getAbsolutePath())==0) {
					String className=f.getAbsolutePath().replace(".java", ".class");
		            
		    		File classFile=new File(className);
		            InputStream in = null;
		    		try {
		    			in = new FileInputStream(classFile);
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
					classFile.delete();//comment this line to keep the class file in src/main/resources/
				}else{
					System.out.println("fail to compile "+f.getName());
				}
			}
		}
		return myClasses;
	}
	
}
