package domain;

import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class MyClass {
	//modify the access modifier and add getters later
	ClassNode classNode;
	String className;
	String extend;
	boolean p;
	List<String> implement;
	List<FieldNode> fields;
	List<MethodNode> methods;
	
	public MyClass(ClassNode cn) {
		this.classNode=cn;
		this.className=cn.name;
		this.p=(classNode.access & Opcodes.ACC_PUBLIC) != 0;
		this.extend=cn.superName;
		this.implement=cn.interfaces;
		this.fields=cn.fields;
		this.methods=cn.methods;
	}
	
	public String toUML() {
		String s="";
		s+="class "+className+"{\n";
		for(FieldNode field:fields) {
			;
		}
		for(MethodNode method:methods) {
			;
		}
		s+="}";
		return s;
	}
}
