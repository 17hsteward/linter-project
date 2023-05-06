package domain;

import java.util.List;
import java.util.LinkedList;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.ParameterNode;

public class MyMethod implements UML{
//	private MethodNode methodNode;
	private String name;
	private int access;
//	List<Attribute> attrs;
	private String desc;
	private List<String> parameters;
	private String returnType;
	//sub method from other classes
	private boolean isStatic;
	private boolean isFinal;
	
	public MyMethod(MethodNode mn) {
		this.access=mn.access&(Opcodes.ACC_PUBLIC+Opcodes.ACC_PROTECTED+Opcodes.ACC_PRIVATE);
		this.isStatic=(mn.access&Opcodes.ACC_STATIC)!=0;
		this.isFinal=(mn.access&Opcodes.ACC_FINAL)!=0;
//		attrs=mn.attrs;
		this.name=mn.name;
		this.desc=mn.desc;
		int i=desc.lastIndexOf(')');
		this.parameters=new LinkedList<>();
		//parse input argument
		
		for(String s:this.desc.substring(1,i).split(";")) {
			if(s.isBlank()) {
				continue;
			}
			
			if(s.contains("/")) {
				s=UML.typeConvert(s);
			}else {
				for(char c:s.toCharArray()) {
					this.parameters.add(String.valueOf(c));
				}
				continue;
			}
			if(s.equals("List<")) {
				s+=">";
			}
			this.parameters.add(s);
		}
		
		//return type
		this.returnType=this.desc.substring(i+1);
	}
	public String toUML() {
		String s;
		
		switch(access) {
			case Opcodes.ACC_PUBLIC:
				s="+";
				break;
			case Opcodes.ACC_PROTECTED:
				s="#";
				break;
			case Opcodes.ACC_PRIVATE:
				s="-";
				break;
			default:
				s="~";
		}
		if(isStatic) {
			s+="{static}";
		}
		if(isFinal) {
			s+="{final}";
		}
		s+=name+"(";
		for(String s2:this.parameters) {
			s+=UML.typeConvert(s2)+",";
		}
		if(s.charAt(s.length()-1)==',') {
			s=s.substring(0, s.length()-1);
		}
		s+="):"+UML.typeConvert(this.returnType);
		return s;
	}
}
