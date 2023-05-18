package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		List<MyClass> myClasses=this.readSub(files);
		List<String> classNames=new LinkedList<>();
		for(MyClass c:myClasses) {
			classNames.add(c.getName());
		}
		for(MyClass c:myClasses) {
			c.setDependent(classNames);
		}
		return myClasses;
	}
	
	public List<MyClass> readSub(File[] files){
		List<MyClass> myClasses=new LinkedList<>();
		//get file number
		int javaFileNumber=0;
		int classFileNumber=0;
		for(File f:files){
			if(f.getName().endsWith(".java")) {
				javaFileNumber++;
			}else if(f.getName().endsWith(".class")) {
				classFileNumber++;
			}
		}
		//get files
		File[] javaFiles = new File[javaFileNumber];
		String[] javaFilePaths = new String[javaFileNumber];
		File[] classFiles = new File[classFileNumber];
		String[] classFilePaths = new String[classFileNumber];
		
		int i = 0;
		int j = 0;
		for(File f:files) {
			if (f.isDirectory()) {
				myClasses.addAll(this.readSub(f.listFiles()));
			} else if (f.getName().endsWith(".java")) {
				javaFiles[i] = f;
				javaFilePaths[i] = f.getAbsolutePath();
				i++;
			} else if (f.getName().endsWith(".class")) {
				classFiles[j] = f;
				classFilePaths[j] = f.getAbsolutePath();
				j++;
			}
		}
		
		//compile files
		for(File f: javaFiles){
			if(javac.run(null, null, null, javaFilePaths)==0) {
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
					in.close();
					for(File file:javaFiles){
						this.reader.deleteClassFromJava(file.getAbsolutePath());//remove class file
					}
				} catch (IOException e) {
					textArea.append("fail to compile: ");
				}
				textArea.append(f.getName()+"                "+f.getAbsolutePath()+"\n");
				textArea.update(textArea.getGraphics());
			}
		}
		
//		for(File classFile:classFiles) {
//			InputStream in = null;
//			try {
//				in = new FileInputStream(classFile);
//				ClassReader reader = null;
//				reader = new ClassReader(in);
//				ClassNode classNode = new ClassNode();
//				reader.accept(classNode, ClassReader.EXPAND_FRAMES);
//
//				MyClass c = new ASMClass(classNode);//speicify for ASMClass
//				c.setPath(classFile.getAbsolutePath());
//				c.setCode(this.reader.getCode(classFile));
//				myClasses.add(c);
//				in.close();
//			} catch (IOException e) {
//				textArea.append("fail to compile: ");
//			}
//			textArea.append(classFile.getName()+"                "+classFile.getAbsolutePath()+"\n");
//			textArea.update(textArea.getGraphics());
//		}
		
		return myClasses;
	}

}
