package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.JTextArea;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import data.Reader;

import java.util.LinkedList;

public class Compiler {
	JavaCompiler javac;
	Reader reader;
	JTextArea textArea;
	public Compiler(JTextArea textArea) {
		javac = ToolProvider.getSystemJavaCompiler();
		this.reader=new Reader();
		this.textArea=textArea;
	}
	public List<MyClass> read(File[] files){
		List<MyClass> myClasses=new LinkedList<>();
		int length = 0;
		for(File f:files){
			if(f.getName().endsWith(".java"))
			length++;
		}
		File[] trueFiles = new File[length];
		String[] filePaths = new String[length];
		int i = 0;
		for(File f:files) {
			if (f.isDirectory()) {
				myClasses.addAll(this.read(f.listFiles()));
			} else if (f.getName().endsWith(".java")) {
				trueFiles[i] = f;
				filePaths[i] = f.getAbsolutePath();
				i++;
			}
		}
		for(File f: trueFiles){
				if(javac.run(null, null, null, filePaths)==0) {
					File classFile = this.reader.getClassFromJava(f.getAbsolutePath());
					InputStream in = null;
					try {
						in = new FileInputStream(classFile);
						ClassReader reader = null;
						reader = new ClassReader(in);
						ClassNode classNode = new ClassNode();
						reader.accept(classNode, ClassReader.EXPAND_FRAMES);

						MyClass c = new ASMClass(classNode);//speicify for ASMClass
						c.setPath(f.getAbsolutePath());
						c.setCode(this.reader.getCode(f));
						myClasses.add(c);
						//mc.printClass();//print the class to verify
						in.close();
						for(File file:trueFiles){
							this.reader.getClassFromJava(file.getAbsolutePath()).delete();
						}//comment this line to keep the class file with their java file
					} catch (IOException e) {
						System.out.println("fail to compile " + f.getName());
						textArea.append("fail to compile: ");
					}
					textArea.append(f.getName()+"                "+f.getAbsolutePath()+"\n");
					textArea.update(textArea.getGraphics());
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
