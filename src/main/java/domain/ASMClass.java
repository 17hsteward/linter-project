package domain;

import java.util.List;
import java.util.LinkedList;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class ASMClass extends MyClass {
//	private ClassNode classNode;
//	List<MyField> fields;
//	List<MyMethod> methods;
	
	public ASMClass(ClassNode cn) {
//		this.classNode=cn;
		this.className=cn.name;
		this.isAbstract=(cn.access & Opcodes.ACC_ABSTRACT) != 0;
		this.isInterface=(cn.access & Opcodes.ACC_INTERFACE) != 0;
		this.extend=cn.superName;
		this.implement=cn.interfaces;
		this.dependent=new LinkedList<>();
		this.fields=new LinkedList<>();
		this.methods=new LinkedList<>();
		for(FieldNode f:cn.fields) {
			this.fields.add(new ASMField(f));
		}
		for(MethodNode m:cn.methods) {
			this.methods.add(new ASMMethod(m));
		}
		this.className=this.className.replaceAll("/",".");
		if(this.className.lastIndexOf(".")!=-1) {
			this.packageName=this.className.substring(0,this.className.lastIndexOf("."));
		}else {
			this.packageName="";
		}
		this.className=this.className.substring(this.className.lastIndexOf(".")+1);
	}
	
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
		if(this.isInterface) {
			s+="interface";
		}else if(this.isAbstract){
			s+="abstract class";
		}else {
			s+="class";
		}
		
		s+=" ";
		if(!this.packageName.isBlank()) {
			s+=this.packageName+".";
		}
		s+=this.className;
		s+="{\n";
		for(MyField f:this.fields) {
			s+="    "+f.toUML()+"\n";
		}
		for(MyMethod m:this.methods) {
			s+="    "+m.toUML()+"\n";
		}
		s+="}\n\n";
		return s;
	}
	/**
	 * get the relation to other class in string uml
	 * @return uml code that points to other class
	 */
	public String toRelationUML(List<String> names) {//input list of existing class
		String s="";
		//has a
		//for all fields
		for(MyField mf:fields) {
			if(names.contains(UML.typeConvert(mf.getType()))) {
				s+=this.packageName+"."+this.className+"-->"+UML.typeConvert(mf.getType())+"\n";
			}
		}
		
		//extend
		//java/lang/Object
		if(!this.extend.equals("java/lang/Object")) {
			s+=this.packageName+"."+this.className+"--|>"+this.extend.replaceAll("/",".")+"\n";
		}
		
		//implement
		//[]
		for(String i:this.implement) {
			s+=this.packageName+"."+this.className+"..|>"+i.replaceAll("/",".")+"\n";
		}
		
		//dependent
		//for all methods internal types
		for(String d:this.dependent) {
			s+=this.packageName+"."+this.className+"..>"+d+"\n";
		}

		return s;
	}
	/**
	 * Input all the other classes and find the dependent by going through all methods and match with other classes
	 */
	public void setDependent(List<String> names) {
		for(MyMethod mm:methods) {
			for(String d:mm.getDependent()) {
				if(names.contains(d)&&!this.dependent.contains(d)) {
					this.dependent.add(d);
				}
			}
		}
	}
}
