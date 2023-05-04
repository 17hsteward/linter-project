package domain;

import java.util.List;

import org.objectweb.asm.Attribute;
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
	public void printClass() {
		System.out.println(this.toClassUML());
	}
	
	public String toClassUML() {
		String s="";
		s+="class "+className+"{\n";
		for(FieldNode field:fields) {
			switch(field.access&(Opcodes.ACC_PUBLIC+Opcodes.ACC_PROTECTED+Opcodes.ACC_PRIVATE)) {
				case Opcodes.ACC_PUBLIC:
					s+="+";
					break;
				case Opcodes.ACC_PROTECTED:
					s+="#";
					break;
				case Opcodes.ACC_PRIVATE:
					s+="-";
					break;
				default:
					s+="~";
			}
			//final,static
			s+=field.name+":"+field.desc;
			
			s+="\n";
		}
		for(MethodNode method:methods) {
			switch(method.access&(Opcodes.ACC_PUBLIC+Opcodes.ACC_PROTECTED+Opcodes.ACC_PRIVATE)) {
				case Opcodes.ACC_PUBLIC:
					s+="+";
					break;
				case Opcodes.ACC_PROTECTED:
					s+="#";
					break;
				case Opcodes.ACC_PRIVATE:
					s+="-";
					break;
				default:
					s+="~";
			}
			s+=method.name;
			
			//input arguments
			if(method.attrs!=null) {
				for(Attribute a:method.attrs) {
					s+=a.type+":";
					s+=a.toString();
					s+=",";
				}
			}
//			System.out.println(s);
			int i=method.desc.lastIndexOf(')')+1;
			s+=method.desc.substring(0, i)+":"+method.desc.substring(i);
			s+="\n";
		}
		s+="}";
//		System.out.println(s);
		return s;
	}
	/**
	 * get the relation to other class in string uml
	 * @return uml code that points to other class
	 */
	public String toRelation() {
		String s="";
		return s;
	}
}
