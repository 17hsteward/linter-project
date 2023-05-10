package domain;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldNode;

public class ASMField extends MyField implements UML{
//	private FieldNode fieldNode;
	
	public ASMField(FieldNode mf) {
		this.access=mf.access&(Opcodes.ACC_PUBLIC+Opcodes.ACC_PROTECTED+Opcodes.ACC_PRIVATE);
		this.isStatic=(mf.access&Opcodes.ACC_STATIC)!=0;
		this.isFinal=(mf.access&Opcodes.ACC_FINAL)!=0;
		this.name=mf.name;
		this.desc=mf.desc;//field type in asm format
		this.isList=mf.signature!=null;
		if(this.isList) {
			this.listType=mf.signature.substring(mf.signature.indexOf("<")+1,mf.signature.length()-3);
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
		if(isStatic) {
			s+="{static}";
		}
		if(isFinal) {
			s+="{final}";
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
