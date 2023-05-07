package domain;

import java.util.List;
import java.util.LinkedList;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ASMMethod extends MyMethod implements UML{
//	private MethodNode methodNode;
	private String name;
	private int access;
	private String desc;
	private List<String> parameters;
	private String returnType;
	private boolean isStatic;
	private boolean isFinal;
	private InsnList instructions;
	private LinkedList<String> dependent;
	//other classes be used
	
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
		
//		System.out.println("\n"+this.name);
//		System.out.println(this.instructions.size());
		for(AbstractInsnNode node:this.instructions) {
//			System.out.println(node.getClass());
			if(node instanceof LabelNode) {
//				System.out.println(((LabelNode)node).getLabel());
			}else if(node instanceof LineNumberNode) {
//				System.out.println("line "+((LineNumberNode)node).line);
			}else if(node instanceof VarInsnNode) {
//				System.out.println("	"+((VarInsnNode)node).var);
			}else if(node instanceof InsnNode) {
//				System.out.println("	"+((InsnNode)node).getType());
			}else if(node instanceof MethodInsnNode) {
//				System.out.println("	"+((MethodInsnNode)node).name);
			}else if(node instanceof FieldInsnNode) {
//				System.out.println("	"+((FieldInsnNode)node).name);
//				System.out.println("	"+((FieldInsnNode)node).owner);
				String m=UML.typeConvert(((FieldInsnNode)node).desc);
				if(!this.dependent.contains(m)) {
					this.dependent.add(m);
				}
			}else if(node instanceof FrameNode) {
//				System.out.println("	"+((FrameNode)node));
			}else if(node instanceof IntInsnNode) {
//				System.out.println("	"+((IntInsnNode)node));
			}else if(node instanceof JumpInsnNode) {
//				System.out.println("	"+((JumpInsnNode)node));
			}else if(node instanceof TypeInsnNode) {
//				System.out.println("	"+((TypeInsnNode)node).desc);
				String m=UML.typeConvert(((TypeInsnNode)node).desc);
				if(!this.dependent.contains(m)) {
					this.dependent.add(m);
				}
			}else {
//				System.out.println("unrecorded node type");
			}
			//LabelNode
			//LineNumberNode
			//VarInsnNode
			//InsnNode
			//MethodInsnNode
			//FieldInsnNode
			//FrameNode
			//IntInsnNode
			//JumpInsnNode
			//TypeInsnNode
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
				s+=">";
			}
			this.parameters.add(s);
		}
		
		//return type
		this.returnType=this.desc.substring(i+1);
	}
	
	public List<String> getDependent(){
		return this.dependent;
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
