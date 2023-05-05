package domain;

import java.util.List;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodNode;

public class MyMethod {
//	private MethodNode methodNode;
	private String name;
	private int access;
	List<Attribute> attrs;
	private String desc;
	
	public MyMethod(MethodNode mn) {
		access=mn.access&(Opcodes.ACC_PUBLIC+Opcodes.ACC_PROTECTED+Opcodes.ACC_PRIVATE);
		attrs=mn.attrs;
		this.name=mn.name;
		this.desc=mn.desc;
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
		
		s+=name;
		if(attrs!=null) {
			for(Attribute a:attrs) {
				s+=a.type+":";
				s+=a.toString();
				s+=",";
			}
		}
		int i=desc.lastIndexOf(')')+1;
		s+=desc.substring(0, i)+":"+desc.substring(i);
		
		return s;
	}
}
