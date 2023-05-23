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
	public Compiler() {
		javac = ToolProvider.getSystemJavaCompiler();
		this.reader=new Reader();
		
	}
	public void setTextArea(JTextArea textArea) {
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
	
	public MyClass readSingleClass(File file) {
		InputStream in = null;
		MyClass c = null;
		try {
			in = new FileInputStream(file);
			ClassReader reader = null;
			reader = new ClassReader(in);
			ClassNode classNode = new ClassNode();
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);

			c=new ASMClass(classNode,file.getAbsolutePath());//speicify for ASMClass
			c.setCode(this.reader.getCode(file));
			in.close();
		} catch (IOException e) {
			if(textArea!=null) {
				textArea.append("fail to compile: ");
			}
		}
		return c;
	}
	
	public List<MyClass> readSub(File[] files){
		List<MyClass> myClasses=new LinkedList<>();
		//get file number
		int javaFileNumber=0;
		
		for(File f:files){
			if(f.getName().endsWith(".java")) {
				javaFileNumber++;
			}
		}
		//get files
		File[] javaFiles = new File[javaFileNumber];
		String[] javaFilePaths = new String[javaFileNumber];
		
		int i = 0;
		for(File f:files) {
			if (f.isDirectory()) {
				myClasses.addAll(this.readSub(f.listFiles()));
			} else if (f.getName().endsWith(".java")) {
				javaFiles[i] = f;
				javaFilePaths[i] = f.getAbsolutePath();
				i++;
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

					MyClass c = new ASMClass(classNode,f.getAbsolutePath());//speicify for ASMClass
					c.setCode(this.reader.getCode(f));
					myClasses.add(c);
					in.close();
					for(File file:javaFiles){
						this.reader.deleteClassFromJava(file.getAbsolutePath());//remove class file
					}
				} catch (IOException e) {
					if(textArea!=null) {
						textArea.append("fail to compile: ");
					}
				}
				if(textArea!=null) {
					textArea.append(f.getName()+"                "+f.getAbsolutePath()+"\n");
					textArea.update(textArea.getGraphics());
				}
			}
		}
		

		
		return myClasses;
	}
	
	public List<MyClass> readClass(File[] files){
		List<MyClass> myClasses=new LinkedList<>();
		int classFileNumber=0;
		for(File f:files){
			if(f.getName().endsWith(".class")) {
				classFileNumber++;
			}
		}
		File[] classFiles = new File[classFileNumber];
		String[] classFilePaths = new String[classFileNumber];
		int i = 0;
		for(File f:files) {
			if (f.isDirectory()) {
				myClasses.addAll(this.readClass(f.listFiles()));
			} else if (f.getName().endsWith(".class")) {
				classFiles[i] = f;
				classFilePaths[i] = f.getAbsolutePath();
				i++;
			}
		}
		
			for(File classFile:classFiles) {
			InputStream in = null;
			try {
				in = new FileInputStream(classFile);
				ClassReader reader = null;
				reader = new ClassReader(in);
				ClassNode classNode = new ClassNode();
				reader.accept(classNode, ClassReader.EXPAND_FRAMES);
	
				MyClass c = new ASMClass(classNode,classFile.getAbsolutePath());//speicify for ASMClass
				c.setCode(this.reader.getCode(classFile));
				myClasses.add(c);
				in.close();
			} catch (IOException e) {
				if(textArea!=null) {
					textArea.append("fail to compile: ");
				}
			}
			if(textArea!=null) {
				textArea.append(classFile.getName()+"                "+classFile.getAbsolutePath()+"\n");
				textArea.update(textArea.getGraphics());
			}
		}
		
		return myClasses;
	}

}