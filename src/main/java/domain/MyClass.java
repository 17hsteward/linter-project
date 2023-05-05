package domain;

import java.util.List;
import java.util.LinkedList;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class MyClass {
//	private ClassNode classNode;
	private String className;
	private String extend;
	private boolean p;
	private List<String> implement;
	private List<MyField> fields;
	private List<MyMethod> methods;
	//innerclass
	
	public MyClass(ClassNode cn) {
//		this.classNode=cn;
		this.className=cn.name;
		this.p=(cn.access & Opcodes.ACC_PUBLIC) != 0;
		this.extend=cn.superName;
		this.implement=cn.interfaces;
//		System.out.println("extend "+this.extend);
//		System.out.println("implement "+this.implement);
		this.fields=new LinkedList<>();
		this.methods=new LinkedList<>();
		for(FieldNode f:cn.fields) {
			this.fields.add(new MyField(f));
		}
		for(MethodNode f:cn.methods) {
			this.methods.add(new MyMethod(f));
		}
	}
	
	//build some getters, and might have uml getter for type conversion
	
	/**
	 * print class UML content
	 */
	public void printClass() {
		System.out.println(this.toClassUML());
	}
	
	/**
	 * generate UML code for internal field and method
	 * Types might need to be processed from asm type
	 * @return UML code for class
	 */
	public String toClassUML() {
		String s="";
		s+="class "+className.replaceAll("/",".")+"{\n";
		for(MyField f:this.fields) {
			s+="    "+f.toUML()+"\n";
		}
		for(MyMethod m:this.methods) {
			s+="    "+m.toUML()+"\n";
		}
		s+="}\n";
		return s;
	}
	/**
	 * get the relation to other class in string uml
	 * @return uml code that points to other class
	 */
	public String toRelationUML() {//input list of existing class
		String name=this.className.replaceAll("/",".");
		String s="";
		//has a
		//for all fields
		
		//extend
		//java/lang/Object
		if(!this.extend.equals("java/lang/Object")) {
			s+=name+"-|>"+this.extend.replaceAll("/",".")+"\n";
		}
		//implement
		//[]
		for(String i:this.implement) {
			s+=name+"..|>"+i.replaceAll("/",".")+"\n";
		}
		
		//dependent
		//for all methods internal types
		
		return s;
	}
}
