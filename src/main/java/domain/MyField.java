package domain;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldNode;

public class MyField implements UML{
//	private FieldNode fieldNode;
	private String name;
	private String desc;
	private int access;
	
	public MyField(FieldNode mf) {
		access=mf.access&(Opcodes.ACC_PUBLIC+Opcodes.ACC_PROTECTED+Opcodes.ACC_PRIVATE);
		this.name=mf.name;
		this.desc=mf.desc;
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
		return s+this.name+":"+UML.typeConvert(this.desc);
	}
}
