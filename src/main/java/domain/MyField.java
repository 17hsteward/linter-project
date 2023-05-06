package domain;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldNode;

public class MyField implements UML{
//	private FieldNode fieldNode;
	private String name;
	private String desc;
	private int access;
	private String signature;
	private boolean isList;
	private String listType;
	
	public MyField(FieldNode mf) {
		access=mf.access&(Opcodes.ACC_PUBLIC+Opcodes.ACC_PROTECTED+Opcodes.ACC_PRIVATE);
		this.name=mf.name;
		this.desc=mf.desc;
		this.signature=mf.signature;
		this.isList=this.signature!=null;
		if(this.isList) {
			this.listType=this.signature.substring(this.signature.indexOf("<")+1,this.signature.length()-2);
		}
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
		if(isList) {
			//list
			s+=this.name+":List<"+UML.typeConvert(this.listType)+">";
		}else {
			//not list
			s+=this.name+":"+UML.typeConvert(this.desc);
		}
		return s;
	}
}
