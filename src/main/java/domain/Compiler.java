package domain;

import java.io.File;
import java.io.FileInputStream;
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
	public List<MyClass> read(File[] files){
		List<MyClass> myClasses=new LinkedList<>();
		for(File f:files) {
			if(f.isDirectory()) {
				myClasses.addAll(this.read(f.listFiles()));
			}else if(f.getName().endsWith(".java")) {
				if(javac.run(null, null, null, f.getAbsolutePath())==0) {
		    		File classFile=this.reader.getClassFromJava(f.getAbsolutePath());
		            InputStream in = null;
					try {
						in = new FileInputStream(classFile);
						ClassReader reader= null;
						reader = new ClassReader(in);
						ClassNode classNode = new ClassNode();
						reader.accept(classNode, ClassReader.EXPAND_FRAMES);

						MyClass c=new ASMClass(classNode);//speicify for ASMClass
						c.setPath(f.getAbsolutePath());
						myClasses.add(c);
						//mc.printClass();//print the class to verify

						in.close();
						classFile.delete();//comment this line to keep the class file with their java file
					} catch (IOException e) {
							System.out.println("fail to compile "+f.getName());
					}
				}
			}
		}
		List<String> classNames=new LinkedList<>();
		for(MyClass c:myClasses) {
			classNames.add(c.getName());
		}
		for(MyClass c:myClasses) {
			c.setDependent(classNames);
		}
		return myClasses;
	}
	
}
