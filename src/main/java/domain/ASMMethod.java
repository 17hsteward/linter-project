package domain;

import java.util.LinkedList;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.ParameterNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class ASMMethod extends MyMethod implements UML{
//	private MethodNode methodNode;
	private InsnList instructions;
	
	public ASMMethod(MethodNode mn) {
		this.access=mn.access&(Opcodes.ACC_PUBLIC+Opcodes.ACC_PROTECTED+Opcodes.ACC_PRIVATE);
		this.isStatic=(mn.access&Opcodes.ACC_STATIC)!=0;
		this.isFinal=(mn.access&Opcodes.ACC_FINAL)!=0;
		this.name=mn.name;
		this.desc=mn.desc;
		int i=desc.lastIndexOf(')');
		this.parameters=new LinkedList<>();
		this.dependent=new LinkedList<>();//for all InstNode, identify type and add to it
		this.instructions=mn.instructions;
		
		for(AbstractInsnNode node:this.instructions) {
			if(node instanceof FieldInsnNode) {
				String m=UML.typeConvert(((FieldInsnNode)node).desc);
				if(!this.dependent.contains(m)) {
					this.dependent.add(m);
				}
			}else if(node instanceof TypeInsnNode) {
				String m=UML.typeConvert(((TypeInsnNode)node).desc);
				if(!this.dependent.contains(m)) {
					this.dependent.add(m);
				}
			}
			//LabelNode					for jump purpose, usually appear before LineNumberNode
			//LineNumberNode			line number of original code
			//VarInsnNode				variable, type
			//InsnNode					instruction, https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-7.html
			//MethodInsnNode			method
			//FieldInsnNode				field, dependency
			//FrameNode
			//IntInsnNode
			//JumpInsnNode
			//TypeInsnNode				dependency
			//IincInsnNode
			//InsnNode
			//InvokeDynamicInsnNode
			//LdcInsnNode
			//LookupSwitchInsnNode
			//MultiANewArrayInsnNode
			//TableSwitchInsnNode
			
		}
		
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
				//add list type
				s+=">";
			}
			this.parameters.add(s);
		}
		//return type
		this.returnType=this.desc.substring(i+1);

//		System.out.println("\n");
//		System.out.println(this.name);
//		System.out.println("desc:  "+mn.desc);
//		System.out.println("signature:  "+mn.signature);
//		System.out.println("parameters:");
//		for(String s:this.parameters) {
//			System.out.print(s+"  ");
//		}
//		System.out.println("\n");
		if(mn.signature!=null) {
//			System.out.println("contains list");
			this.parameters=UML.inputFromSignature(mn.signature);
			this.returnType=UML.returnFromSignature(mn.signature);
//			System.out.println("parameter:  "+parameters);
//			System.out.println("return:  "+returnType);
		}
		
//		for(String s:this.dependent) {
//			System.out.println(s);
//		}
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

	public boolean isGetter(){
		if(instructions.size()!=3){
			return false;
		}
		AbstractInsnNode insn1 = instructions.get(0);
		if((insn1.getOpcode() & Opcodes.ALOAD)==0){
			return false;
		}
		AbstractInsnNode insn2 = instructions.get(1);
		if((insn2.getOpcode() & Opcodes.GETFIELD)==0){
			return false;
		}
		return true;
	}

	public  boolean isSetter(){
		if(instructions.size()!=4){
			return false;
		}
		AbstractInsnNode insn1 = instructions.get(0);
		if((insn1.getOpcode() & Opcodes.ALOAD)==0){
			return false;
		}
		AbstractInsnNode insn3 = instructions.get(2);
		if((insn3.getOpcode() & Opcodes.PUTFIELD)==0){
			return false;
		}
		return true;
	}

	public InsnList getInstructions() {
		return this.instructions;
	}
}
